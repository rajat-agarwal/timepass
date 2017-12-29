package mi;

public class Minimum_Squares_From_A_Rectangle {
    private static int calculate(int small, int big, int[][] dp) {
        if (small <= 0 || big <= 0)
            return 0;

        if (dp[small][big] != 0)
            return dp[small][big];

        if (small == big) {
            dp[small][big] = 1;
            return 1;
        }
        if (big % small == 0) {
            dp[small][big] = big / small;
            return big / small;
        }

        int minCuts = Integer.MAX_VALUE;
        for (int i = 1; i <= small / 2; i++)
            minCuts = Math.min(minCuts, calculate(small - i, big, dp) + calculate(i, big, dp));

        for (int i = 1; i <= big / 2; i++)
            minCuts = Math.min(minCuts, calculate(small, big - i, dp) + calculate(small, i, dp));

        dp[small][big] = minCuts;
        return dp[small][big];
    }

    private static int calculateMinSquares(int n, int m) {
        int small = n < m ? n : m;
        int big = n < m ? m : n;
        int[][] dp = new int[small + 1][big + 1];
        return calculate(small, big, dp);
    }

    public static void main(String[] args) {
        int n = 30;
        int m = 35;
        System.out.println(calculateMinSquares(n, m));
    }
}
