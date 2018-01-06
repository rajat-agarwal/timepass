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
    private static final int rows = 3;
    private static final int cols = 3;
    private static final boolean[][] visited = new boolean[rows][cols]; //need only in case of more than 2 directions to prevent endless loop

    private static void setInputOutput() {
        directions = new HashMap<>(2);
        directions.put('R', new int[]{0, 1});
        directions.put('D', new int[]{1, 0});
        directions.put('L', new int[]{0, -1});
        directions.put('U', new int[]{-1, 0});

        int startVal = 1;
        input = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                input[i][j] = startVal++;
            }
        }
    }

    private static boolean canMove(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private static void addYourselfToDownstreamPath(List<String> retVal, List<String> from, char dir) {
        for (String s : from) {
            retVal.add(dir + s);
        }
    }

    //Not that good in runtime. Use getPathsRecursive() instead.
    private static List<String> getPathsRecursiveWithMemorisation(int r, int c) {
        if (memorisationMap.containsKey(r * MULTIPLIER + c)) {
            return memorisationMap.get(r * MULTIPLIER + c);
        }
        List<String> retVal = new ArrayList<>();
        for (Map.Entry<Character, int[]> e : directions.entrySet()) {
            int newr = r + e.getValue()[0];
            int newc = c + e.getValue()[1];
            if (newr == rows - 1 && newc == cols - 1) {
                retVal.add(e.getKey().toString());
            } else if (canMove(newr, newc) && input[newr][newc] != 0) {
                addYourselfToDownstreamPath(retVal, getPathsRecursiveWithMemorisation(newr, newc), e.getKey());
            }
        }
        memorisationMap.put(r * MULTIPLIER + c, retVal);
        return retVal;
    }

    private static void getPathsRecursive(int r, int c) {
        if (r == rows - 1 && c == cols - 1) {
            validPaths.add(sb.toString());
            return;
        }
        visited[r][c] = true;
        for (Map.Entry<Character, int[]> e : directions.entrySet()) {
            int newr = r + e.getValue()[0];
            int newc = c + e.getValue()[1];

            if (canMove(newr, newc) && !visited[newr][newc] && input[newr][newc] != 0) {
                sb.append(e.getKey());
                getPathsRecursive(newr, newc);
                sb.setLength(sb.length() - 1);
            }
        }
        visited[r][c] = false;
    }

    private static void testMemorisationRuntime() {
        memorisationMap = new HashMap<>(rows * cols * 2);
        System.gc();
        System.gc();
        long t1 = System.currentTimeMillis();
        getPathsRecursiveWithMemorisation(0, 0);
        System.out.println(System.currentTimeMillis() - t1);
//        System.out.println(memorisationMap.get(0));
    }

    private static void testRecursive() {
        validPaths = new ArrayList<>();
        sb = new StringBuilder(rows + cols);

//        System.gc();
//        System.gc();
//        long t1 = System.currentTimeMillis();
        getPathsRecursive(0, 0);
//        System.out.println(System.currentTimeMillis() - t1);
        System.out.println(validPaths);
    }

    public static void main(String[] args) {
        setInputOutput();
//        testMemorisationRuntime();
        testRecursive();
    }
}
