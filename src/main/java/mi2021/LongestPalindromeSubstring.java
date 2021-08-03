package mi2021;

public class LongestPalindromeSubstring {
    public static void main(String[] args) {
        //String s = "forgeeksskeegfor";
        String s = "aaa";
//        String s = "forgeekskeegfor";
        String longestPalindrome = findLongestPalindromeSubstring(s.toCharArray());
        System.out.println(longestPalindrome);
    }

    private static String findLongestPalindromeSubstring(char[] input) {
        int idx[] = new int[]{0, 0};
        for (int i = 0; i < input.length - 1; i++) {
            match(input, i, i, idx); // match odd aba
            match(input, i, i + 1, idx); // match even abba
        }
        return String.valueOf(input).substring(idx[0], idx[1] + 1);
    }

    private static void match(char[] input, int lIdx, int rIndx, int[] ret) {
        for (; lIdx >= 0 && rIndx < input.length; lIdx--, rIndx++) {
            if (input[lIdx] != input[rIndx]) {
                break;
            }
        }
        //reset index to the current found subarray
        rIndx = rIndx - 1;
        lIdx = lIdx + 1;
        if (ret[1] - ret[0] < rIndx - lIdx) {
            ret[0] = lIdx;
            ret[1] = rIndx;
        }
    }
}
