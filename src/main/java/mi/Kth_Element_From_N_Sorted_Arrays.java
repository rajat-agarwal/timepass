package mi;

import java.util.*;

public class Kth_Element_From_N_Sorted_Arrays {
    private static class Node implements Comparable<Node> {
        int listNo;
        int offset;
        int val;

        Node(int listNo, int offset, int val) {
            this.listNo = listNo;
            this.offset = offset;
            this.val = val;
        }

        @Override
        public int compareTo(Node other) {
            return this.val - other.val;
        }
    }

    private static int find_kth_min_element(List<List<Integer>> input, int k) {
        Queue<Node> minHeap = new PriorityQueue<>(input.size());
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).size() > 0)
                minHeap.add(new Node(i, 0, input.get(i).get(0)));
        }

        int kthElement = -1;
        int i = 0;
        for (; i < k && !minHeap.isEmpty(); i++) {
            Node n = minHeap.remove();
            kthElement = n.val;
            if (input.get(n.listNo).size() > (n.offset + 1)) {
                n.offset++;
                n.val = input.get(n.listNo).get(n.offset);
                minHeap.add(n);
            }
        }
        return i == k ? kthElement : -1;
    }

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(3, 5, 7, 9));
        input.add(Arrays.asList(2, 6, 8));
        input.add(Arrays.asList(1, 4, 7, 8));
        int k = 3;
        for (int i = 0; i < 15; i++)
            System.out.println(find_kth_min_element(input, i));
    }
}
