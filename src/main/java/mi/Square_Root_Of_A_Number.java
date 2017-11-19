package mi;
/**
 * Created by rajat.agarwal on 26/06/17.
 */
public class Square_Root_Of_A_Number {
    public static void main(String[] args) {
        iterative_linear(225);
        iterative_binary(225);
        recursive_binary(225, 0, 225 / 2);
    }

    private static void iterative_linear(int N) {
        for (int i = 0; i <= N / 2; i++) {
            if (i * i == N) {
                System.out.println(i);
                return;
            }
        }

        System.out.println("No proper square root");
    }

    private static void iterative_binary(int N) {
        int start = 0;
        int end = N / 2;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (mid * mid == N) {
                System.out.println(mid);
                return;
            } else if (mid * mid > N) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println("No proper square root");
    }

    private static void recursive_binary(int N, int start, int end) {
        if (start > end) {
            System.out.println("No proper square root");
            return;
        }
        int mid = (start + end) / 2;
        if (mid * mid == N) {
            System.out.println(mid);
        } else if (mid * mid > N) {
            recursive_binary(N, start, mid - 1);
        } else {
            recursive_binary(N, mid + 1, end);
        }
    }
}
