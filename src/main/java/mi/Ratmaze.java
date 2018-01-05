package mi;

import java.util.*;

public class Ratmaze {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tcs = sc.nextInt();
        for (int tc = 0; tc < tcs; tc++) {
            int n = sc.nextInt();
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            ArrayList<String> res = printPath(arr, n);
            for (String x : res) {
                System.out.println(x);
            }
        }
    }


    public static ArrayList<String> printPath(int[][] m, int n) {
        final Map<Character, int[]> directions = new HashMap<>();
        directions.put('R', new int[]{0, 1});
        directions.put('D', new int[]{1, 0});

        ArrayList<ArrayList<String>> dp1 = new ArrayList(){{
            new ArrayList<String>();
            new ArrayList<String>();
            new ArrayList<String>();
            new ArrayList<String>();
            new ArrayList<String>();
        }};

        for (int i = m.length - 1; i >= 0; i--) {

            ArrayList<ArrayList<String>> dp2 = dp1;
            dp1 = new ArrayList<>(m.length);

            for (int j = m[0].length - 1; j >= 0; j--) {
                boolean isDestination = (i == m.length - 1 && j == m[0].length - 1);
                if (isDestination) {
                    dp1.add(j, new ArrayList<String>(Arrays.asList("")));
                } else {
                    dp1.add(j, new ArrayList<>());
                    for (Map.Entry<Character, int[]> entrySet : directions.entrySet()) {
                        if (withinLimitAndPathAvailable(m, i + entrySet.getValue()[0], j + entrySet.getValue()[1])) {
                            ArrayList<String> list = dp2.get(j + entrySet.getValue()[1]);
                            if (!list.isEmpty()) {
                                for (String x : list) {
                                    dp1.get(j).add(x + entrySet.getKey());
                                }
                            }

                        }

                    }

                }

            }

        }
        return dp1.get(0);
    }

    public static boolean withinLimitAndPathAvailable(int[][] m, int i, int j) {
        if (i < m.length && j < m[0].length && m[i][j] == 1) {
            return true;
        } else {
            return false;
        }
    }

}
