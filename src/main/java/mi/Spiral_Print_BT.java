package mi;

import mi.utilities.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Spiral_Print_BT {
    private static void printSpiral(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (root != null) {
            stack1.push(root);
        }
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                TreeNode n = stack1.pop();
                System.out.print(n.data + " ");
                if (n.right != null) {
                    stack2.add(n.right);
                }
                if (n.left != null) {
                    stack2.add(n.left);
                }
            }
            while (!stack2.isEmpty()) {
                TreeNode n = stack2.pop();
                System.out.print(n.data + " ");
                if (n.left != null) {
                    stack1.add(n.left);
                }
                if (n.right != null) {
                    stack1.add(n.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.addRightChild(3);
        treeNode.addLeftChild(2);
        treeNode.left.addLeftChild(4);
        treeNode.left.addRightChild(5);
        treeNode.right.addLeftChild(6);
        treeNode.right.addRightChild(7);
        treeNode.left.left.addLeftChild(8);
        treeNode.left.left.addRightChild(9);
        treeNode.left.right.addLeftChild(10);
        treeNode.left.right.addRightChild(11);
        treeNode.right.left.addLeftChild(12);
        treeNode.right.left.addRightChild(13);
        treeNode.right.right.addLeftChild(14);
        treeNode.right.right.addRightChild(15);
        treeNode.print();
        printSpiral(treeNode);
    }
}
