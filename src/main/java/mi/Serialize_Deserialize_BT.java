package mi;

import lombok.AllArgsConstructor;
import mi.utilities.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Serialize_Deserialize_BT {
    private static TreeNode deserializeLevelOrder(String data) {
        String[] tok = data.split(",", -1);
        if (tok[0].equals("")) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(tok[0]));
        queue.add(root);
        boolean isLeft = true;
        for (int i = 1; i < tok.length; i++) {
            TreeNode n = tok[i].equals("") ? null : new TreeNode(Integer.parseInt(tok[i]));
            if (queue.peek() != null) {
                if (isLeft)
                    queue.peek().left = n;
                else
                    queue.peek().right = n;
            }
            if (!isLeft) {
                queue.remove();
            }
            isLeft = !isLeft;
            queue.add(n);
        }
        return root;
    }

    private static String serializeLevelOrder(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int nullNOdes = 0;
        while (!queue.isEmpty() && queue.size() != nullNOdes) {
            TreeNode t = queue.remove();
            if (t != null) {
                queue.add(t.left);
                queue.add(t.right);
                nullNOdes += t.left == null ? 1 : 0;
                nullNOdes += t.right == null ? 1 : 0;
                sb.append(t.data);
            } else {
                queue.add(null);
                queue.add(null);
                nullNOdes++; //2 additions and one removal
            }
            sb.append(',');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private static TreeNode prepareTestData1() {
        TreeNode root = new TreeNode(5);
        root.addLeftChild(4);
        root.left.insertInOrder(3);
        root.left.left.insertInOrder(-1);
        root.addRightChild(7);
        root.right.addLeftChild(2);
        root.right.left.addLeftChild(9);
        root.print();
        return root;
    }

    private static TreeNode prepareTestData2() {
        TreeNode root = new TreeNode(1);
        root.addRightChild(2);
        root.right.addRightChild(3);
        root.right.right.addRightChild(4);
        root.right.right.right.addRightChild(5);
        root.right.right.right.right.addRightChild(6);
        root.right.right.right.right.right.addRightChild(7);
        root.right.right.right.right.right.right.addRightChild(8);
        root.print();
        return root;
    }

    private static void inorderTraversal(TreeNode node, StringBuilder sb) {
        if (node != null) {
            inorderTraversal(node.left, sb);
            sb.append(node.data + ",");
            inorderTraversal(node.right, sb);
        }
    }

    private static void preorderTraversal(TreeNode node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data + ",");
            preorderTraversal(node.left, sb);
            preorderTraversal(node.right, sb);
        }
    }

    private static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        inorderTraversal(root, sb);
        sb.setLength(sb.length() - 1);
        sb.append(":"); //splitter b/w inorderTraversal and preorderTraversal strings
        preorderTraversal(root, sb);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private static class Index {
        int idx;

        public Index(int i) {
            this.idx = i;
        }
    }

    private static TreeNode constructTree(List<Integer> inorder, int isIdx, int ieIdx, List<Integer> preorder, Index psIdx) {
        if (ieIdx < isIdx)
            return null;

        TreeNode root = new TreeNode(preorder.get(psIdx.idx++));
        int inorderIndex = inorder.indexOf(root.data);

        root.left = constructTree(inorder, isIdx, inorderIndex - 1, preorder, psIdx);
        root.right = constructTree(inorder, inorderIndex + 1, ieIdx, preorder, psIdx);
        return root;
    }

    private static List<Integer> toList(String[] sarr) {
        List<Integer> ret = new ArrayList<>(sarr.length);
        for (String s : sarr) {
            ret.add(Integer.parseInt(s));
        }
        return ret;
    }

    private static TreeNode deserialize(String data) {
        if (data.equals(""))
            return null;

        String[] traversals = data.split(":");
        List<Integer> inorder = toList(traversals[0].split(","));
        List<Integer> preorder = toList(traversals[1].split(","));
        return constructTree(inorder, 0, inorder.size() - 1, preorder, new Index(0));
    }

    private static void test_using_inorder_preorder_serialize_deserialize() {
        TreeNode root = prepareTestData1();
        String serialized = serialize(null);
        System.out.println(serialized);
        TreeNode node = deserialize(serialized);
        if (node != null) node.print();
    }

    private static void test_using_level_order_serialize_deserialize() {
//        TreeNode root = prepareTestData1();
        TreeNode root = prepareTestData2();
        String serializedString = serializeLevelOrder(root);
        System.out.println(serializedString);
        TreeNode res = deserializeLevelOrder(serializedString);
        res.print();
    }

    public static void main(String[] args) {
//        test_using_level_order_serialize_deserialize();
        test_using_inorder_preorder_serialize_deserialize();
    }
}
