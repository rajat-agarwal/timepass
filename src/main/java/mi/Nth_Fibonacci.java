package mi;
/**
 * Created by rajat.agarwal on 25/06/17.
 */
public class Nth_Fibonacci {
    public static void main(String[] args) {
        System.out.println(iterative(50));
        System.out.println(recursive(50));
        System.out.println(recursive_dp(50));
    }

    private static long iterative(int in) {
        long first = 1;
        long second = first;
        for (int i = 2; i <= in; i++) {
            long new_num = first + second;
            first = second;
            second = new_num;
        }
        return second;
    }

    private static long recursive(int in) {
        return recursive(in, 1L, 1L, 1);
    }

    private static long recursive(int in, Long first, Long second, int iter) {
        if (in == iter) {
            return second;
        }
        return recursive(in, second, first + second, iter + 1);
    }

    private static long recursive_dp(int in) {
        long[] dp = new long[in + 1];
        dp[0] = dp[1] = 1L;
        return recursive_dp(in, dp);
    }

    private static long recursive_dp(int in, long[] dp) {
        if (dp[in] != 0) {
            return dp[in];
        }
        dp[in] = recursive_dp(in - 1, dp) + recursive_dp(in - 2, dp);
        return dp[in];
    }
}
