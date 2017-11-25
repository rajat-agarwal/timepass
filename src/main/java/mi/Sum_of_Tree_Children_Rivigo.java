package mi;

import java.util.*;

public class Sum_of_Tree_Children_Rivigo {
    private static class Node {
        int id;
        int score;
        int level;
        int levelSum;

        public Node(int id, int score, int level) {
            this.id = id;
            this.score = score;
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            Node other = (Node) o;
            return id == other.id;
        }

        @Override
        public int hashCode() {
            return this.id;
        }
    }

    private static void markLevels(Map<Node, List<Node>> graph, Node parent) {
        if (graph.containsKey(parent)) {
            for (Node n : graph.get(parent)) {
                n.level = parent.level + 1;
                markLevels(graph, n);
            }
        }
    }

    private static int getSum(Node u, Map<Node, List<Node>> graph, int l) {
        int sum = u.levelSum;
        if (l > 1) {
            for (Node n : graph.get(u)) {
                sum += getSum(n, graph, l - 1);
            }
        }
        return sum;
    }

    private static int printSum(Node u, int l, Map<Node, List<Node>> graph) {
        if (l < 0 || !graph.containsKey(u) || u.level > l)
            return 0;

        return u.level == l ? u.score : getSum(u, graph, l - u.level);
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int q = sc.nextInt();

            Node[] nodes = new Node[n + 1];
            for (int i = 1; i <= n; i++)
                nodes[i] = new Node(i, sc.nextInt(), 0);

            Map<Node, List<Node>> graph = new HashMap<>(n);
            for (int i = 1; i < n; i++) {
                Node u = nodes[sc.nextInt()];
                Node v = nodes[sc.nextInt()];
                if (!graph.containsKey(u))
                    graph.put(u, new ArrayList<Node>());

                graph.get(u).add(v);
                u.levelSum += v.score;
            }

            markLevels(graph, nodes[1]);

            for (int i = 0; i < q; i++) {
                Node u = nodes[sc.nextInt()];
                int l = sc.nextInt() - 1;
                System.out.println(printSum(u, l, graph));
            }
        }
    }
}
