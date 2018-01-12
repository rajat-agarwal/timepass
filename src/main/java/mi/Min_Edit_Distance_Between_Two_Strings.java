package mi;

public class Min_Edit_Distance_Between_Two_Strings {
    public static void main(String[] args) {
        String s1 = "apple";
        String s2 = "ape";
        System.out.println(findEditDistance(s1.toCharArray(), s2.toCharArray()));
    }

    private static int findEditDistance(char[] a, char[] b) {
        int[][] dp = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++)
            dp[i][0] = i;
        for (int i = 1; i <= b.length; i++)
            dp[0][i] = i;

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                dp[i][j] = a[i - 1] == b[j - 1] ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                dp[i][j] = Math.min(dp[i][j], Math.min(1 + dp[i - 1][j - 1], Math.min(1 + dp[i][j - 1], 1 + dp[i - 1][j])));
            }
        }
        return dp[a.length][b.length];
    }
}
