package mi;

import java.util.*;

/*
Given an array with frequency of each index.
Queries of the form "l, r, x" will be given. Print state after all queries.
l, r, x in the query means printing number of elements between index l and r (both inclusive) with frequency = x.
 */
public class Algorythma_Range_Query {
    private static int getResult(int l, int r, List<Integer> indexRange) {
        if (indexRange == null) {
            return 0;
        }

        int i = 0;
        int count = 0;
        while (i < indexRange.size() && indexRange.get(i) < l)
            i++;
        while (i < indexRange.size() && indexRange.get(i) <= r) {
            i++;
            count++;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Map<Integer, List<Integer>> input = new HashMap<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            if (!input.containsKey(v)) {
                input.put(v, new ArrayList<>());
            }
            input.get(v).add(i + 1);
        }
        int q = sc.nextInt();
        while (q-- > 0) {
            System.out.println(getResult(sc.nextInt(), sc.nextInt(), input.get(sc.nextInt())));
        }
    }
}
