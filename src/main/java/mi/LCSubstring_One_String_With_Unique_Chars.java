package mi;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings, one string with only unique chars. Find LCSubstring in O(nlogn)
https://www.careercup.com/question?id=5683639910137856
 */
public class LCSubstring_One_String_With_Unique_Chars {
    private static int LCSubstring(char[] s, char[] uniqueCharStr) {
        if (s.length == 0 || uniqueCharStr.length == 0)
            return 0;
        Map<Character, Integer> map = new HashMap<>(uniqueCharStr.length);
        for (int i = 0; i < uniqueCharStr.length; i++)
            map.put(uniqueCharStr[i], i);

        int resStart = 0, resEnd = 0;
        int start = 0;
        int end = 0;
        for (; end < s.length; end++) {
            if (!map.containsKey(s[end]) || (start < end && map.get(s[end]) != map.get(s[end - 1]) + 1)) {
                start = end + 1;
            }
            if (resEnd - resStart < end - start) {
                resEnd = end;
                resStart = start;
            }
        }
        System.out.println("start= " + resStart + " end=" + resEnd);
        return (resStart == s.length) ? 0 : ((resEnd - resStart) + 1);
    }

    public static void main(String[] args) {
        String s = "appleg";
        String uniqueCharStr = "apleg";
        System.out.println(LCSubstring(s.toCharArray(), uniqueCharStr.toCharArray()));

    }
}
