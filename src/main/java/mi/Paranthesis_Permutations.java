package mi;

import java.util.ArrayList;
import java.util.List;

public class Paranthesis_Permutations {
    private static List<String> print(int open, int close, StringBuilder sb) {
        List<String> ret = new ArrayList<>();
        if (close > open) {
            sb.append(')');
            ret.addAll(print(open, close - 1, sb));
        }
        if (open > 0) {
            sb.append('(');
            ret.addAll(print(open - 1, close, sb));
        }
        if (close == 0) {
            ret.add(sb.toString());
        }
        if (sb.length() > 0)
            sb.setLength(sb.length() - 1);
        return ret;
    }

    private static List<String> printValidPermutations(int n) {
        return print(n, n, new StringBuilder(2 * n));
    }

    public static void main(String[] args) {
        System.out.println(printValidPermutations(3));
    }
}
