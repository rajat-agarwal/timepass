package mi;

import apple.laf.JRSUIUtils;
import mi.utilities.Binary_Tree;
import mi.utilities.ListNode;
import mi.utilities.TreeNode;

import java.util.Arrays;
import java.util.List;

/*
https://practice.geeksforgeeks.org/problems/leaves-to-dll/1
Given a binary tree. Modify tree such that all original leaf nodes are removed and are linked together via DLL. Return head of DLL.
 */
public class Connect_BT_Leaves_To_DLL {
    private static TreeNode head = null;

    private static boolean modifyTreeWithPrevPointer(TreeNode root) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null) {
            if (head != null) {
                root.left = head.left;
                root.right = head;
                head.left.right = root;
            } else {
                head = root;
            }
            head.left = root;
            head.left.right = root;
            return true;
        }

        if (modifyTreeWithPrevPointer(root.left))
            root.left = null;
        if (modifyTreeWithPrevPointer(root.right))
            root.right = null;

        return false;
    }

    private static boolean isLeafNode(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private static TreeNode adToList(TreeNode head, TreeNode node) {
        if (head == null) {
            node.left = node.right = node;
            return node;
        }
        node.left = head.left;
        node.right = head;
        head.left.right = node;
        head.left = node;
        return head;
    }

    private static TreeNode modifyTree(TreeNode root, TreeNode head) {
        if (root == null)
            return null;
        if (root.left != null) {
            if (isLeafNode(root.left)) {
                head = adToList(head, root.left);
                root.left = null;
            } else {
                head = modifyTree(root.left, head);
            }
        }
        if (root.right != null) {
            if (isLeafNode(root.right)) {
                head = adToList(head, root.right);
                root.right = null;
            } else {
                head = modifyTree(root.right, head);
            }
        }
        return head;
    }

    private static void testMethod1(List<Integer> input) {
        Binary_Tree bt = new Binary_Tree(input);
        bt.print();

        modifyTreeWithPrevPointer(bt.getRoot());
        head.left.right = null;
        head.left = null;
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.right;
        }
        System.out.println("Test method 1 done");
        bt.print();
    }

    private static void testMethod2(List<Integer> input) {
        Binary_Tree bt = new Binary_Tree(input);
        bt.print();

        TreeNode ret = modifyTree(bt.getRoot(), null);
        ret.left.right = null;
        ret.left = null;
        while (ret != null) {
            System.out.print(ret.data + " ");
            ret = ret.right;
        }
        System.out.println("test Method 2 done");
        bt.print();
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        testMethod1(input);
        testMethod2(input);
    }
}
