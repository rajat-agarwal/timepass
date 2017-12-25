package mi;

import mi.utilities.TreeNode;

public class Two_Nodes_Of_BST_Swapped {
    public static void main(String[] args) {

    }

    TreeNode first, second, prev;

    private void swapElements() {
        int d = first.data;
        first.data = second.data;
        second.data = d;
    }

    public void recoverTree(TreeNode root) {
        findElements(root);
        swapElements();
    }

    private void findElements(TreeNode root) {
        if (root == null)
            return;
        findElements(root.left);
        if (prev != null && prev.data > root.data) {
            first = first == null ? prev : first;
            second = root;
        }
        prev = root;
        findElements(root.right);
    }
}
