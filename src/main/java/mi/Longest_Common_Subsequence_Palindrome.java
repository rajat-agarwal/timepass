package mi;

public class Longest_Common_Subsequence_Palindrome {
    private static int longestPalindrome(int l, String s1) {
        StringBuilder sb = new StringBuilder(s1);
        String s2 = sb.reverse().toString();
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = s1.charAt(i - 1) == s2.charAt(j - 1) ? 1 + dp[i - 1][j - 1] : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        String input = "bandana";
        System.out.println(longestPalindrome(input.length(), input));
    }
}
