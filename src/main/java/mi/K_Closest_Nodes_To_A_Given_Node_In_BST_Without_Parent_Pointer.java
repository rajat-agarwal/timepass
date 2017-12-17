package mi;

import mi.utilities.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class K_Closest_Nodes_To_A_Given_Node_In_BST_Without_Parent_Pointer {
    public static void main(String[] args) {

    }

    //main function, for step 3)
    public static List findKClosestNodeInBSTIterative(TreeNode root, int key, int k) throws Exception {
        List<TreeNode> list = new ArrayList();
        Stack<TreeNode> stack = findTreeWithKeyNearestToTheKeyIterative(root, key);
        TreeNode closestNode = stack.pop();
        k--;
        list.add(closestNode);
        Stack<TreeNode> stackForSmaller = new Stack<>(), stackForGreater = new Stack<>(), temp = new Stack<>();
        //copy the current stack to two stacks
        //so they can be used in findnext functions.
        while (stack.size() > 0)
            temp.push(stack.pop());
        while (temp.size() > 0) {
            TreeNode tempTree = temp.pop();
            stackForSmaller.push(tempTree);
            stackForGreater.push(tempTree);
        }
        TreeNode nextlarger = nextLargerNodeInBSTIterative(closestNode, stackForGreater);
        TreeNode nextSmaller = nextSmallerNodeInBSTIterative(closestNode, stackForSmaller);
        while (k > 0) {
            if (nextlarger == null && nextSmaller == null)
                throw new Exception();
            else if (nextlarger != null && nextSmaller != null) {
                if (Math.abs(nextlarger.data - key) >= Math.abs(nextSmaller.data - key)) {
                    list.add(nextSmaller);
                    k--;
                    nextSmaller = nextSmallerNodeInBSTIterative(nextSmaller, stackForSmaller);
                } else {
                    list.add(nextlarger);
                    k--;
                    nextlarger = nextLargerNodeInBSTIterative(nextlarger, stackForGreater);
                }
            } else if (nextlarger != null) {
                list.add(nextlarger);
                k--;
                nextlarger = nextLargerNodeInBSTIterative(nextlarger, stackForGreater);
            } else {
                list.add(nextSmaller);
                k--;
                nextSmaller = nextSmallerNodeInBSTIterative(nextSmaller, stackForSmaller);
            }
        }
        return list;
    }

    public static Stack findTreeWithKeyNearestToTheKeyIterative(TreeNode root, int key) {
        Stack stack = new Stack();
        TreeNode desiredRoot = root;
        int diff = Math.abs(root.data - key);
        while (root != null) {
            stack.push(root);
            if (diff > Math.abs(root.data - key)) {
                diff = Math.abs(root.data - key);
                desiredRoot = root;
            }
            if (root.data > key)
                root = root.left;
            else if (root.data < key)
                root = root.right;
            else
                return stack;
        }
        while (stack.peek() != desiredRoot)
            stack.pop();
        return stack;
    }

    //step 2) find its next larger node
    public static TreeNode nextLargerNodeInBSTIterative(TreeNode current, Stack<TreeNode> stack) {
        if (current.right != null) {
            TreeNode nextTree = current.right;
            while (nextTree.left != null) {
                stack.push(nextTree);
                nextTree = nextTree.left;
            }
            return nextTree;
        } else {
            if (stack.size() > 0) {
                TreeNode tempTree = stack.pop();
                while (tempTree != null) {
                    if (tempTree.data > current.data)
                        break;
                    else
                        tempTree = stack.size() > 0 ? stack.pop() : null;
                }
                return tempTree;
            } else
                return null;
        }
    }

    //step 2) find its next smaller node
    public static TreeNode nextSmallerNodeInBSTIterative(TreeNode current, Stack<TreeNode> stack) {
        if (current.left != null) {
            TreeNode nextTree = current.left;
            while (nextTree.right != null) {
                stack.push(nextTree);
                nextTree = nextTree.right;
            }
            return nextTree;
        } else {
            if (stack.size() > 0) {
                TreeNode tempTree = stack.pop();
                while (tempTree != null) {
                    if (tempTree.data < current.data)
                        break;
                    else
                        tempTree = stack.size() > 0 ? stack.pop() : null;
                }
                return tempTree;
            } else
                return null;
        }
    }
}
