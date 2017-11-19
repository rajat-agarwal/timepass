package mi;

/**
 * Created by rajat.agarwal on 25/06/17.
 */
public class All_Permutations_Of_A_String {
    public static void main(String[] args) {
        recursivePermutations("", "abc");
    }

    private static void recursivePermutations(String prefix, String input) {
        if (input.length() == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            String newInput = input.substring(0, i) + input.substring(i + 1);
            recursivePermutations(prefix + input.charAt(i), newInput);
        }
    }
}
