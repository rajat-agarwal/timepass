package mi;

/*
find minimum cost to multiply chain of matrix.
https://www.geeksforgeeks.org/?p=15553
 */
public class Matrix_Chain_Multiplication {
    private static int[] input;

    private static int findMinCostDP() {
        int[][] dp = new int[input.length][input.length]; // wasting one row and one col memory for code simplicity as the actual matrix starts from index 1 in input

        for (int l = 2; l < input.length; l++) {
            for (int i = l, j = i - l + 1; i < input.length; i++,j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = 0; k < l - 1; k++) {
                    minCost = Math.min(minCost,
                            dp[j][j + k] +
                                    dp[j + k + 1][i] +
                                    input[j-1] * input[j + k] * input[i]);
                }
                dp[i][j] = dp[j][i] = minCost;
            }
        }
        return dp[1][dp.length - 1];
    }

    static int solveRecursive(int i, int j) {
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;

        // place parenthesis at different places between first
        // and last matrix, recursively calculate count of
        // multiplications for each parenthesis placement and
        // return the minimum count
        for (int k = i; k < j; k++) {
            int count = solveRecursive(i, k) +
                    solveRecursive(k + 1, j) +
                    input[i - 1] * input[k] * input[j];

            if (count < min)
                min = count;
        }

        // Return minimum count
        return min;
    }

    public static void main(String[] args) {
        input = new int[]{40, 20, 30, 10, 30}; // ans is 26000
//        input = new int[]{10, 20, 30, 40, 30}; //ans is 30000
//        input = new int[]{10, 20, 30}; //ans is 6000
        System.out.println(findMinCostDP());
        System.out.println(solveRecursive(1, input.length - 1));
    }
}
