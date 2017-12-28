package mi;

/*
* Given an intitial position of a knight on a chessboard.
 * Find the probability of the knight to remain on the chessboard after taking k steps.
*/
public class Probability_Of_A_Knight_To_Remain_On_Chessboard_After_K_Steps {
    private static boolean validMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private static double findProbability(int x, int y, int k, double[][][] dp) {
        if (k == 0)
            return 1;
        if (dp[x][y][k] > 0)
            return dp[x][y][k];
        double res = 0;
        for (int[] dir : directions) {
            int newx = x + dir[0];
            int newy = y + dir[1];
            if (validMove(newx, newy)) {
                res += (1.0 / 8 * findProbability(newx, newy, k - 1, dp));
            }
        }
        dp[x][y][k] = res;
        return dp[x][y][k];
    }

    private static int[][] directions = {
            {-2, 1},
            {-2, -1},
            {-1, 2},
            {-1, -2},
            {1, 2},
            {1, -2},
            {2, 1},
            {2, -1}
    };

    public static void main(String[] args) {
        int[] initialPosition = {0, 0};
        int k = 3;
        double[][][] dp = new double[8][8][k+1];
        System.out.println(findProbability(initialPosition[0], initialPosition[1], k, dp));
    }
}
