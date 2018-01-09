package mi;

/**
 * Given a 2D array, find the maximum sum subarray in it
 * https://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
 */
public class Sub_Rectangle_With_Max_Sum {
    public static void main(String[] args) throws java.lang.Exception {
        findMaxSubMatrix(new int[][]{
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        });
    }

    /**
     * To find maxSum in 1d array
     * <p>
     * return {maxSum, left, right}
     */
    public static int[] kadane(int[] a) {
        //result[0] == maxSum, result[1] == start, result[2] == end;
        int[] result = new int[]{Integer.MIN_VALUE, 0, -1};
        int currentSum = 0;
        int localStart = 0;

        for (int i = 0; i < a.length; i++) {
            currentSum += a[i];
            if (currentSum > result[0]) {
                result[0] = currentSum;
                result[1] = localStart;
                result[2] = i;
            }
            if (currentSum < 0) {
                currentSum = 0;
                localStart = i + 1;
            }
        }

        return result;
    }

    /**
     * To find and print maxSum, (left, top),(right, bottom)
     */
    public static void findMaxSubMatrix(int[][] input) {
        int[] currentResult;
        int maxSum = Integer.MIN_VALUE;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        for (int leftCol = 0; leftCol < input[0].length; leftCol++) {
            int[] tmp = new int[input.length];

            for (int rightCol = leftCol; rightCol < input[0].length; rightCol++) {

                for (int i = 0; i < input.length; i++) {
                    tmp[i] += input[i][rightCol];
                }
                currentResult = kadane(tmp);
                if (currentResult[0] > maxSum) {
                    maxSum = currentResult[0];
                    left = leftCol;
                    top = currentResult[1];
                    right = rightCol;
                    bottom = currentResult[2];
                }
            }
        }
        System.out.println("MaxSum: " + maxSum +
                ", range: [(" + left + ", " + top +
                ")(" + right + ", " + bottom + ")]");
    }
}