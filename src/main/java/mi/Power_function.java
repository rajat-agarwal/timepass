package mi;
/**
 * Created by rajat.agarwal on 26/06/17.
 */
public class Power_function {
    public static void main(String[] args) {
        iterative(2, 4);
        System.out.println(recursive(2, 4));
    }

    private static void iterative(int a, int b) {
        int ret = 1;
        for (int i = 0; i < b; i++) {
            ret *= a;
        }
        System.out.println(ret);
    }

    private static int recursive(int a, int b) {
        if (b == 0) {
            return 1;
        }

        int ret = recursive(a, b / 2);
        if (b % 2 == 0) {
            return ret * ret;
        } else {
            return ret * ret * a;
        }
    }
}
