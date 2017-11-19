package mi;

import java.util.*;

public class Algorythma_Test_Case_Dependencies {
    private static void mark(boolean[] tstates, Map<Integer, Set<Integer>> dependencies, Map<Integer, Integer> nodeIncoming) {
        Queue<Integer> rootNodes = new LinkedList<>();
        for (Map.Entry<Integer, Integer> e : nodeIncoming.entrySet()) {
            if (e.getValue() == 0) {
                rootNodes.add(e.getKey());
            }
        }
        while (!rootNodes.isEmpty()) {
            int n = rootNodes.remove();
            if (dependencies.containsKey(n)) {
                for (int i : dependencies.get(n)) {
                    tstates[i] &= tstates[n];
                    int v = decrement(nodeIncoming, i);
                    if (v == i) {
                        rootNodes.add(v);
                    }
                }
            }
        }
    }

    private static int decrement(Map<Integer, Integer> map, int k) {
        if (map.containsKey(k)) {
            int v = map.get(k) - 1;
            if (v == 0) {
                map.remove(k);
                return k;
            } else {
                map.put(k, v);
            }
        }
        return -1;
    }

    private static void increment(Map<Integer, Integer> map, int k, int v) {
        if (map.containsKey(k))
            map.put(k, map.get(k) + v);
        else
            map.put(k, v);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        boolean[] tstates = new boolean[n];
        for (int i = 0; i < n; i++) {
            tstates[i] = sc.nextInt() == 1 ? true : false;
        }
        Map<Integer, Set<Integer>> dependencies = new HashMap<>(n);
        Map<Integer, Integer> nodeIncoming = new HashMap<>();
        for (int i = 0; i < q; i++) {
            int v = sc.nextInt() - 1; //align to array index
            int u = sc.nextInt() - 1; //align to array index
            if (!dependencies.containsKey(u)) {
                dependencies.put(u, new HashSet<>());
            }
            dependencies.get(u).add(v);
            increment(nodeIncoming, u, 0);
            increment(nodeIncoming, v, 1);
        }

        mark(tstates, dependencies, nodeIncoming);
        for (boolean b : tstates)
            System.out.println(b ? "YES" : "NO");
    }
}
