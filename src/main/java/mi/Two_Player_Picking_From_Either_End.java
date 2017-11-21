package mi;

public class Two_Player_Picking_From_Either_End {
    private static int playGameMemorisation(int[] coins, int s, int e, int[][] dp) {
        if (e < s) {
            return 0;
        }
        if (dp[s][e] == 0) {
            int score1 = coins[s] + Math.min(playGameMemorisation(coins, s + 2, e, dp), playGameMemorisation(coins, s + 1, e - 1, dp));
            int score2 = coins[e] + Math.min(playGameMemorisation(coins, s + 1, e - 1, dp), playGameMemorisation(coins, s, e - 2, dp));
            dp[s][e] = Math.max(score1, score2);
        }
        return dp[s][e];
    }

    private static int playGameDP(int[] coins) {
        int[][] dp = new int[coins.length][coins.length];
        for (int r = 0; r < coins.length; r++) {
            dp[r][r] = coins[r];
        }
        for (int r = 0; r < coins.length - 1; r++) {
            dp[r][r + 1] = Math.max(coins[r], coins[r + 1]);
        }
        for (int l = 2; l < coins.length; l++) {
            for (int r = 0; r + l < coins.length; r++) {
                int rightScore = coins[r] + Math.min(dp[r + 2][r + l], dp[r + 1][r + l - 1]);
                int leftScore = coins[r + l] + Math.min(dp[r][r + l - 2], dp[r + 1][r + l - 1]);
                dp[r][r + l] = Math.max(rightScore, leftScore);
            }
        }
        return dp[0][coins.length - 1];
    }

    public static void main(String[] args) {
        int[] input = {1, 5, 233, 7};
        int[][] dp = new int[input.length][input.length];
        System.out.println(playGameDP(input));
        System.out.println(playGameMemorisation(input, 0, input.length - 1, dp));
    }
}
