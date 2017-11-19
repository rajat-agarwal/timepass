package mi;
/**
 * Created by rajat.agarwal on 26/06/17.
 */
public class Powers_Of_2_From_1_To_N {
    public static void main(String[] args) {
        print_power(1025);
        print_recursive(1025);
    }

    private static void print_power(int N) {
        for (int i = 1; i <= N; i = i * 2) {
            System.out.println(i);
        }
    }

    private static int print_recursive(int N) {
        if (N == 1) {
            System.out.println(N);
            return 1;
        }
        int ret = print_recursive(N / 2) * 2;
        System.out.println(ret);

        return ret;
    }
}
