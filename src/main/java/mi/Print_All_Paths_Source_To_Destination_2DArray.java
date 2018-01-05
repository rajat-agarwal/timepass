package mi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a 2D matrix, print all paths from 0,0 to n,n.
Two methods are possible
1. recursive go in all possible directions and keep appending current direction.
 Once reach the target add the string from Stringbuilder to result list.
 If you see in this approach you will keep re computing the subproblems again and again so common perception
 would be that this will be solved better using DP but actually not.
2. Using DP/memorisation. Recursive go down in all possible directions, once reach the target, return a list with only
target value inside. In the returned call stack append the direction in all of the strings returned from the recursion
and return to calling stack. and this goes on till the source. If you see there are lot many new strings/list creation will
happen here and as we move up the size and combinations of the String/List will increase exponentially.
This will take far far more time than the pure recursion one.
 */
public class Print_All_Paths_Source_To_Destination_2DArray {
    private static Map<Character, int[]> directions;
    private static int[][] input;
    private static List<String> validPaths;
    private static StringBuilder sb;
    private static Map<Integer, List<String>> memorisationMap;
    private static final int MULTIPLIER = 100;

    private static void setInputOutput() {
        directions = new HashMap<>(2);
        directions.put('R', new int[]{0, 1});
        directions.put('D', new int[]{1, 0});

        validPaths = new ArrayList<>();

        int r = 13, c = 13, startVal = 1;
        input = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                input[i][j] = startVal++;
            }
        }

        sb = new StringBuilder(r + c);
        memorisationMap = new HashMap<>(r * c * 2);
    }

    private static boolean canMove(int r, int c) {
        return r >= 0 && r < input.length && c >= 0 && c < input[0].length;
    }

    private static void addYourselfToDownstreamPath(List<String> retVal, List<String> from, char dir) {
        for (String s : from) {
            retVal.add(dir + s);
        }
    }

    private static List<String> getPathsRecursiveWithMemorisation(int r, int c) {
        if (memorisationMap.containsKey(r * MULTIPLIER + c)) {
            return memorisationMap.get(r * MULTIPLIER + c);
        }
        List<String> retVal = new ArrayList<>();
        for (Map.Entry<Character, int[]> e : directions.entrySet()) {
            int newr = r + e.getValue()[0];
            int newc = c + e.getValue()[1];
            if (newr == input.length - 1 && newc == input[0].length - 1) {
                retVal.add(e.getKey().toString());
            } else if (canMove(newr, newc) && input[newr][newc] != 0) {
                addYourselfToDownstreamPath(retVal, getPathsRecursiveWithMemorisation(newr, newc), e.getKey());
            }
        }
        memorisationMap.put(r * MULTIPLIER + c, retVal);
        return retVal;
    }

    private static void getPathsRecursive(int r, int c) {
        if (r == input.length - 1 && c == input[0].length - 1) {
            validPaths.add(sb.toString());
            return;
        }

        for (Map.Entry<Character, int[]> e : directions.entrySet()) {
            int newr = r + e.getValue()[0];
            int newc = c + e.getValue()[1];
            if (canMove(newr, newc) && input[newr][newc] != 0) {
                sb.append(e.getKey());
                getPathsRecursive(newr, newc);
                sb.setLength(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        setInputOutput();
        System.gc();
        System.gc();
        long t1 = System.currentTimeMillis();
        getPathsRecursive(0, 0);
        System.out.println(System.currentTimeMillis() - t1);
//        System.out.println(validPaths);


        System.gc();
        System.gc();
        t1 = System.currentTimeMillis();
        getPathsRecursiveWithMemorisation(0, 0);
        System.out.println(System.currentTimeMillis() - t1);
//        System.out.println(memorisationMap.get(0));
    }
}
