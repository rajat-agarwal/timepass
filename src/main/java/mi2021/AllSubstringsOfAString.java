package mi2021;

import java.util.ArrayList;
import java.util.List;

public class AllSubstringsOfAString {
    public static void main(String[] args) {
        //char[] input = new char[] {'a','b','c','d','e','f','g'};
        char[] input = new char[]{'a', 'b', 'c', 'd'};
        allSubstringBruteForce(input);
        System.out.println("-------------Printing optimally now ---------------");
        allSubstringsOptimal(input);
    }

    private static void allSubstringBruteForce(char[] input) {
        List<List<Character>> output = new ArrayList<>(input.length);
        for (char c : input) {
            output.add(new ArrayList<>(input.length));
            for (List l : output) {
                l.add(c);
                System.out.println(l);
            }
        }
    }

    private static void allSubstringsOptimal(char[] input) {
        String in = String.valueOf(input);
        for (int end = 0; end < in.length(); end++) {
            for (int start = 0; start <= end; start++){
                System.out.println(in.substring(start, end+1));
            }
        }
    }
}
