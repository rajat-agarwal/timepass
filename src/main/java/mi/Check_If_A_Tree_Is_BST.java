package mi;

import mi.utilities.TreeNode;

/**
 * Created by rajat.agarwal on 06/08/17.
 */
public class Check_If_A_Tree_Is_BST {
    private static boolean checkIfBST(TreeNode root) {
        return checkRecursive(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean checkRecursive(TreeNode root, int min, int max) {
        if (root == null)
            return true;

        if (root.data > max || root.data < min)
            return false;

        return checkRecursive(root.left, Integer.MIN_VALUE, root.data)
                & checkRecursive(root.right, root.data + 1, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        BST_Traversal bst = new BST_Traversal();
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(2);
        bst.add(7);
        bst.add(12);
        bst.add(20);

        System.out.println(checkIfBST(bst.root));
    }
}
