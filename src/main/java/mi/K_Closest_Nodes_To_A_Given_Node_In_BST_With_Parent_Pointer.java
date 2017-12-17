package mi;

import mi.utilities.TreeNode;

import java.util.ArrayList;
import java.util.List;

//https://csjobinterview.wordpress.com/2012/06/29/find-k-nodes-in-bst-that-are-closest-to-a-value-key/
public class K_Closest_Nodes_To_A_Given_Node_In_BST_With_Parent_Pointer {
    public static void main(String[] args) {

    }

    public static List<TreeNode> findKClosestNodeInBST(TreeNode root, int key, int k) throws Exception {
        List<TreeNode> list = new ArrayList<>();
        TreeNode closestNode = findTreeWithKeyNearestToTheKey(root, key);
        k--;
        list.add(closestNode);
        TreeNode nextlarger = nextLargerNodeInBST(closestNode);
        TreeNode nextSmaller = nextSmallerNodeInBST(closestNode);
        while (k > 0) {
            if (nextlarger == null && nextSmaller == null)
                throw new Exception();
            else if (nextlarger != null && nextSmaller != null) {
                if (Math.abs(nextlarger.data - key) >= Math.abs(nextSmaller.data - key)) {
                    list.add(nextSmaller);
                    k--;
                    nextSmaller = nextSmallerNodeInBST(nextSmaller);
                } else {
                    list.add(nextlarger);
                    k--;
                    nextlarger = nextLargerNodeInBST(nextlarger);
                }
            } else if (nextlarger != null) {
                list.add(nextlarger);
                k--;
                nextlarger = nextLargerNodeInBST(nextlarger);
            } else {
                list.add(nextSmaller);
                k--;
                nextSmaller = nextSmallerNodeInBST(nextSmaller);
            }
        }
        return list;
    }

    //find out the node that is closed to the key, step 1)
    public static TreeNode findTreeWithKeyNearestToTheKey(TreeNode root, int key) {
        TreeNode desiredRoot = root;
        int diff = Math.abs(root.data - key);
        while (root != null) {
            if (diff > Math.abs(root.data - key)) {
                diff = Math.abs(root.data - key);
                desiredRoot = root;
            }
            if (root.data > key)
                root = root.left;
            else if (root.data < key)
                root = root.right;
            else
                return root;
        }
        return desiredRoot;
    }

    //step 2) find its next larger node in BST
    public static TreeNode nextLargerNodeInBST(TreeNode current) {
        if (current.right != null) {
            TreeNode nextTree = current.right;
            while (nextTree.left != null)
                nextTree = nextTree.left;
            return nextTree;
        } else {
            while (current.parent != null) {
                if (current != current.parent.right)
                    return current.parent;
                else {
                    while (current.parent != null && current == current.parent.right)
                        current = current.parent;
                    return current.parent;
                }
            }
            return null;
        }
    }

    //step 2) find its next smaller node in BST
    public static TreeNode nextSmallerNodeInBST(TreeNode current) {
        if (current.left != null) {
            TreeNode nextTree = current.left;
            while (nextTree.right != null)
                nextTree = nextTree.right;
            return nextTree;
        } else {
            while (current.parent != null) {
                if (current == current.parent.right)
                    return current.parent;
                else {
                    while (current.parent != null && current == current.parent.left)
                        current = current.parent;
                    return current.parent;
                }
            }
            return null;
        }
    }
}
