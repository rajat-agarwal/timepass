package mi;

import mi.utilities.BTreePrinter;
import mi.utilities.Binary_Tree;
import mi.utilities.TreeNode;

import java.util.*;

public class K_Distant_Nodes_To_A_Given_Node_In_BT_Without_Parent_Pointer {
    private static void printBasic(Binary_Tree bt) {
        bt.print();
        BTreePrinter.printLevelOrder(bt.getRoot());
        List<TreeNode> inorder = BTreePrinter.inorder(bt.getRoot());
        Collections.sort(inorder);
        System.out.println(inorder);
        System.out.println("========================================================");
    }

    private static void validateFindNearestNode(List<Integer> input, Binary_Tree bt) {
        printBasic(bt);
        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            int key = random.nextInt(100);
            System.out.print(" key:" + key + " (" + input.contains(key) + ")");
            TreeNode matchr = findNearestNode(bt.getRoot(), key);
            System.out.println(matchr.data);
        }
    }

    private static void validateFindKDistantNodes(List<Integer> input, Binary_Tree bt) {
        printBasic(bt);
        Random random = new Random();
        System.out.println("Running test cases now ......");
        for (int i = 1; i <= 20; i++) {
            int key = random.nextInt(100);
            System.out.print("key=" + key + " (" + input.contains(key) + ")");
            TreeNode target = findNearestNode(bt.getRoot(), key);
            System.out.print(" Best match Node=" + target.data + " ");
            int k = random.nextInt(5);
            System.out.println(" k=" + k);
            List<TreeNode> retR = new ArrayList<>();
            findKDistantNodes(bt.getRoot(), target, k, retR);
            Collections.sort(retR);
            System.out.println("rajat=" + retR);
            System.out.println("---------------");
        }
    }

    public static void main(String[] args) {
        List<Integer> queue = new ArrayList<>(20);
        Random random = new Random();
        for (int i = 1; i <= 16; i++) {
            int r = random.nextInt(100);
            if (!queue.contains(r))
                queue.add(r);
        }
        Binary_Tree bt = new Binary_Tree(queue);

        validateFindNearestNode(queue, bt);
        validateFindKDistantNodes(queue, bt);
    }

    private static TreeNode bestNode(TreeNode left, TreeNode right, TreeNode root, int key) {
        TreeNode ret = root;
        if (left != null && Math.abs(left.data - key) < Math.abs(ret.data - key))
            ret = left;
        if (right != null && Math.abs(right.data - key) < Math.abs(ret.data - key))
            ret = right;
        return ret;
    }

    public static TreeNode findNearestNode(TreeNode node, int key) {
        if (node == null || node.data == key)
            return node;

        return bestNode(findNearestNode(node.left, key),
                findNearestNode(node.right, key),
                node,
                key);
    }

    private static List<TreeNode> findKDistantNodesDown(TreeNode node, int k) {
        if (node == null || k < 0)
            return new ArrayList<TreeNode>(0);
        if (k == 0)
            return new ArrayList<TreeNode>(Arrays.asList(node));
        List<TreeNode> result = findKDistantNodesDown(node.left, k - 1);
        result.addAll(findKDistantNodesDown(node.right, k - 1));
        return result;
    }

    // find all nodes at a distance of k nodes from a given node in a binary tree. Assume target node is always available. Which is ensured by findNearestNode
    public static int findKDistantNodes(TreeNode node, TreeNode target, int k, List<TreeNode> ret) {
        if (node == null)
            return 0;
        if (node == target) {
            List<TreeNode> v = findKDistantNodesDown(node, k);
            ret.addAll(v);
            return 1;
        }

        int level = findKDistantNodes(node.left, target, k, ret);
        if (level > 0) {
            if (k == level)
                ret.add(node);
            else {
                ret.addAll(findKDistantNodesDown(node.right, k - level - 1));
            }
            return level + 1;
        }
        //similar code for right if left tree does not have the target node.
        level = findKDistantNodes(node.right, target, k, ret);
        if (level > 0) {
            if (k == level)
                ret.add(node);
            else {
                ret.addAll(findKDistantNodesDown(node.left, k - level - 1));
            }
            return level + 1;
        }
        return level;
    }
}