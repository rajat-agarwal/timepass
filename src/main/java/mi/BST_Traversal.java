package mi;
import mi.utilities.TreeNode;

import java.util.Stack;

/**
 * Created by rajat.agarwal on 02/02/17.
 */
public class BST_Traversal {
    TreeNode root;

    public static void main(String[] args) {
        BST_Traversal bst = new BST_Traversal();
        bst.add(10);
        bst.add(5);
        bst.add(15);
        bst.add(2);
        bst.add(7);
        bst.add(12);
        bst.add(20);
        bst.inorder();
        bst.preorder();
        bst.postorder();
    }

    private TreeNode add(int data, TreeNode node) {
        if (node == null) {
            return new TreeNode(data);
        }

        if (data < node.data) {
            node.left = add(data, node.left);
        } else {
            node.right = add(data, node.right);
        }

        return node;
    }

    public void add(int data) {
        root = add(data, root);
    }

    private void inorderRecursive(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderRecursive(node.left);
        System.out.print(node.data + "\t");
        inorderRecursive(node.right);
    }

//    private boolean checkBst(TreeNode node) {
//        if (node == null) {
//            return true;
//        }
//        inorderRecursive(node.left);
//        System.out.print(node.data + "\t");
//        inorderRecursive(node.right);
//    }

    private void inorderIterative(TreeNode node) {
        Stack<TreeNode> stack = new Stack();
        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            node = stack.pop();
            System.out.print(node.data + "\t");
            node = node.right;
        }
    }

    private void preorderRecursive(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + "\t");
        preorderRecursive(node.left);
        preorderRecursive(node.right);
    }

    private void preorderIterative(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            while (node != null) {
                System.out.print(node.data + "\t");
                stack.push(node);
                node = node.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            node = stack.pop();
            node = node.right;
        }
    }

    private void postorderIterative(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            if (stack.isEmpty()) {
                break;
            }

            node = stack.peek();

            if (node.right == null && node.left == null) {
                System.out.print(node.data + "\t");
            }

            node = node.right;
        }
    }

    private void postorderRecursive(TreeNode node) {
        if (node == null) {
            return;
        }
        postorderRecursive(node.left);
        postorderRecursive(node.right);
        System.out.print(node.data + "\t");
    }

    public void inorder() {
        System.out.println("Recursive inorder traversal");
        inorderRecursive(root);

        System.out.println("\n\nIterative inorder traversal");
        inorderIterative(root);

        System.out.println();
        System.out.println();
    }

    private void preorder() {
        System.out.println("Recursive preorder traversal");
        preorderRecursive(root);

        System.out.println("\n\nIterative preorder traversal");
        preorderIterative(root);

        System.out.println();
        System.out.println();
    }

    private void postorder() {
        System.out.println("Recursive postorder traversal");
        postorderRecursive(root);

        System.out.println("\n\nIterative postorder traversal");
//        postorderIterative(root);

        System.out.println();
        System.out.println();
    }
}
