package mi;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rajat.agarwal on 25/06/17.
 */
public class All_Permutations_Of_A_String {
    public static void main(String[] args) {
        String input = "abcdefgh";
        Set<String> recursiveSet = new HashSet<>((int)Math.pow(2,input.length()));
        recursivePermutations("", input, recursiveSet);
//        System.out.println(recursiveSet);

        Set<String> optimisedSet = new HashSet<>((int)Math.pow(2,input.length()));
        optimised(input.toCharArray(), 0, input.length() - 1, optimisedSet);
//        System.out.println(optimisedSet);

        System.out.println(recursiveSet.equals(optimisedSet));
    }

    private static void swap(char[] data, int i, int j) {
        char c = data[i];
        data[i] = data[j];
        data[j] = c;
    }

    private static void optimised(char[] data, int l, int r, Set<String> set) {
        if (l == r) {
            set.add(String.valueOf(data));
            return;
        }

        for (int i = l; i <= r; i++) {
            swap(data, l, i);
            optimised(data, l + 1, r, set);
            swap(data, i, l);
        }
    }

    private static void recursivePermutations(String prefix, String input, Set<String> set) {
        if (input.length() == 0) {
            set.add(prefix);
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            String newInput = input.substring(0, i) + input.substring(i + 1);
            recursivePermutations(prefix + input.charAt(i), newInput, set);
        }
    }
}
