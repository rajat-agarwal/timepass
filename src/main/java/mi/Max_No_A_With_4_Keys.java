package mi;

/*
https://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/

Given a special keyboard with 4 keys
    1. 'A' -> Print character A
    2. 'Ctrl A' -> Select everything on the screen
    3. 'Ctrl C' -> Copy selected into memory buffer
    4. 'Ctrl V' -> paste everything in buffer to screen (in append mode)
Given number of keystrokes, print length of string that can be formed with maximum number of 'A's
 */
public class Max_No_A_With_4_Keys {
    private static int computeMaxALen(int n) {
        if (n < 7)
            return n;
        int[] dp = new int[n + 1];
        for (int i = 0; i < 7; i++)
            dp[i] = i;

        for (int i = 7; i <= n; i++) {
            for (int j = i - 3; j > 1; j--) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        for (int i = 1; i < 20; i++)
            System.out.println(computeMaxALen(i));
    }
}


/*
Below are few important points to note.
a) For N < 7, the output is N itself. b) Ctrl V can be used multiple times to print current buffer .
The idea is to compute the optimal string length for N keystrokes by using a simple insight.
 The sequence of N keystrokes which produces an optimal string length will end with a suffix of
 Ctrl-A, a Ctrl-C, followed by only Ctrl-V's (For N > 6).
The task is to find out the break=point after which we get the above suffix of keystrokes.
Definition of a breakpoint is that instance after which we need to only press Ctrl-A, Ctrl-C once
 and the only Ctrl-V’s afterwards to generate the optimal length.
 If we loop from N-3 to 1 and choose each of these values for the break-point,
 and compute that optimal string they would produce. Once the loop ends, we will have the maximum
 of the optimal lengths for various breakpoints, thereby giving us the optimal length for N keystrokes.
 */
