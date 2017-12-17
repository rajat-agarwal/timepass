package mi;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pattern_Matcher_Bag_Packing {
    private static void checkIPPattern() {
        String ipPattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){4}";
        String ipPattern2 =
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

        String[] test_String = {
                "1.1.1.1"
        };
        Pattern p = Pattern.compile(ipPattern2);
        for (String s : test_String) {
            Matcher m = p.matcher(s);
            System.out.println(s + " " + m.find());
        }
    }

    public static void main(String[] args) {
        String pattern = "(.*[kst]$)|(p.*e$)|(screw.*r$)";

//        checkIPPattern();

        String[] test_String = {
                "matches",
                "jacket",
                "blanket",
                "compass",
                "pot",
                "hat",
                "screwdriver",
                "fork",
                "book",
                "penknife"
        };

        Pattern p = Pattern.compile(pattern);
        for (String s : test_String) {
            Matcher m = p.matcher(s);
            System.out.println(s + "--->" + m.find());
        }
    }
}