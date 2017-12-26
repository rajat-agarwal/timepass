package mi;

import mi.utilities.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class K_Closest_Nodes_To_A_Given_Node_In_BST_Without_Parent_Pointer {
    static boolean isParentPointerAvailable = false;
    static TreeNode root;

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
        root = bst.root;
        Random random = new Random();
        for (int i = 1; i < 21; i++) {
            TreeNode nearest = findBestMatched(bst.root, new TreeNode(i), bst.root);
            int k = random.nextInt(5);
            System.out.println("nearest " + k + " nodes from " + nearest.data);
            List<TreeNode> nearestNodes = findKNearest(bst.root, nearest, k);
            System.out.println(nearestNodes);
        }
    }

    public static TreeNode findBestMatched(TreeNode node, TreeNode target, TreeNode prev) {
        if (node == null)
            return prev;
        if (node == target || node.data == target.data)
            return node;

        if (Math.abs(node.data - target.data) < Math.abs(prev.data - target.data))
            prev = node;
        TreeNode res;
        if (node.data < target.data)
            return findBestMatched(node.right, target, prev);
        else
            return findBestMatched(node.left, target, prev);
    }

    public static List<TreeNode> findKNearest(TreeNode node, TreeNode target, int k) {
        List<TreeNode> result = new ArrayList<>(k);
        result.add(target);
        TreeNode nextLarger = findNextLarger(target);
        TreeNode nextSmaller = findNextSmaller(target);
        while (--k > 0) {
            if (nextLarger == null) {
                result.add(nextSmaller);
                nextSmaller = findNextSmaller(nextSmaller);
            } else if (nextSmaller == null) {
                result.add(nextLarger);
                nextLarger = findNextLarger(nextLarger);
            } else if (Math.abs(nextLarger.data - target.data) < Math.abs(nextSmaller.data - target.data)) {
                result.add(nextLarger);
                nextLarger = findNextLarger(nextLarger);
            } else {
                result.add(nextSmaller);
                nextSmaller = findNextSmaller(nextSmaller);
            }
        }
        return result;
    }

    private static TreeNode findNextSmaller(TreeNode node) {
        if (node == null)
            return node;
        if (node.left != null)
            return nextInLeft(node);
        else return nextSmallFromParent(node);
    }

    private static TreeNode smallerFromParentPointer(TreeNode node) {
        if (node == null)
            return node;
        TreeNode parent = node.parent;
        while (parent != null && parent.left == node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private static TreeNode inorderPredessor(TreeNode root, TreeNode target, TreeNode prev) {
        if (root == null)
            return prev;
        TreeNode predessesor = inorderPredessor(root.left, target, prev);
        if (root == target || root.data == target.data)
            return predessesor;
        return inorderPredessor(root.right, target, root);
    }

    private static TreeNode nextSmallFromParent(TreeNode node) {
        return isParentPointerAvailable ? smallerFromParentPointer(node) : inorderPredessor(root, node, null);
    }

    private static TreeNode nextInLeft(TreeNode node) {
        node = node.left;
        while (node != null && node.right != null)
            node = node.right;
        return node;
    }

    private static TreeNode findNextLarger(TreeNode node) {
        if (node == null)
            return node;
        if (node.right != null)
            return nextInRight(node);
        else return nextLargerFromParent(node);
    }

    private static TreeNode nextLargerFromParentWithParentPointer(TreeNode node) {
        TreeNode parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private static TreeNode inorderSuccessor(TreeNode root, TreeNode target) {
        TreeNode successor = null;
        while (root != null) {
            if (root.data > target.data) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return successor;
    }

    private static TreeNode nextLargerFromParent(TreeNode node) {
        return isParentPointerAvailable ? nextLargerFromParentWithParentPointer(node) : inorderSuccessor(root, node);
    }

    private static TreeNode nextInRight(TreeNode node) {
        node = node.right;
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }


//    public static void main(String[] args) {
//
//    }
//
//    //main function, for step 3)
//    public static List findKClosestNodeInBSTIterative(TreeNode root, int key, int k) throws Exception {
//        List<TreeNode> list = new ArrayList();
//        Stack<TreeNode> stack = findTreeWithKeyNearestToTheKeyIterative(root, key);
//        TreeNode closestNode = stack.pop();
//        k--;
//        list.add(closestNode);
//        Stack<TreeNode> stackForSmaller = new Stack<>(), stackForGreater = new Stack<>(), temp = new Stack<>();
//        //copy the current stack to two stacks
//        //so they can be used in findnext functions.
//        while (stack.size() > 0)
//            temp.push(stack.pop());
//        while (temp.size() > 0) {
//            TreeNode tempTree = temp.pop();
//            stackForSmaller.push(tempTree);
//            stackForGreater.push(tempTree);
//        }
//        TreeNode nextlarger = nextLargerNodeInBSTIterative(closestNode, stackForGreater);
//        TreeNode nextSmaller = nextSmallerNodeInBSTIterative(closestNode, stackForSmaller);
//        while (k > 0) {
//            if (nextlarger == null && nextSmaller == null)
//                throw new Exception();
//            else if (nextlarger != null && nextSmaller != null) {
//                if (Math.abs(nextlarger.data - key) >= Math.abs(nextSmaller.data - key)) {
//                    list.add(nextSmaller);
//                    k--;
//                    nextSmaller = nextSmallerNodeInBSTIterative(nextSmaller, stackForSmaller);
//                } else {
//                    list.add(nextlarger);
//                    k--;
//                    nextlarger = nextLargerNodeInBSTIterative(nextlarger, stackForGreater);
//                }
//            } else if (nextlarger != null) {
//                list.add(nextlarger);
//                k--;
//                nextlarger = nextLargerNodeInBSTIterative(nextlarger, stackForGreater);
//            } else {
//                list.add(nextSmaller);
//                k--;
//                nextSmaller = nextSmallerNodeInBSTIterative(nextSmaller, stackForSmaller);
//            }
//        }
//        return list;
//    }
//
//    public static Stack findTreeWithKeyNearestToTheKeyIterative(TreeNode root, int key) {
//        Stack stack = new Stack();
//        TreeNode desiredRoot = root;
//        int diff = Math.abs(root.data - key);
//        while (root != null) {
//            stack.push(root);
//            if (diff > Math.abs(root.data - key)) {
//                diff = Math.abs(root.data - key);
//                desiredRoot = root;
//            }
//            if (root.data > key)
//                root = root.left;
//            else if (root.data < key)
//                root = root.right;
//            else
//                return stack;
//        }
//        while (stack.peek() != desiredRoot)
//            stack.pop();
//        return stack;
//    }
//
//    //step 2) find its next larger node
//    public static TreeNode nextLargerNodeInBSTIterative(TreeNode current, Stack<TreeNode> stack) {
//        if (current.right != null) {
//            TreeNode nextTree = current.right;
//            while (nextTree.left != null) {
//                stack.push(nextTree);
//                nextTree = nextTree.left;
//            }
//            return nextTree;
//        } else {
//            if (stack.size() > 0) {
//                TreeNode tempTree = stack.pop();
//                while (tempTree != null) {
//                    if (tempTree.data > current.data)
//                        break;
//                    else
//                        tempTree = stack.size() > 0 ? stack.pop() : null;
//                }
//                return tempTree;
//            } else
//                return null;
//        }
//    }
//
//    //step 2) find its next smaller node
//    public static TreeNode nextSmallerNodeInBSTIterative(TreeNode current, Stack<TreeNode> stack) {
//        if (current.left != null) {
//            TreeNode nextTree = current.left;
//            while (nextTree.right != null) {
//                stack.push(nextTree);
//                nextTree = nextTree.right;
//            }
//            return nextTree;
//        } else {
//            if (stack.size() > 0) {
//                TreeNode tempTree = stack.pop();
//                while (tempTree != null) {
//                    if (tempTree.data < current.data)
//                        break;
//                    else
//                        tempTree = stack.size() > 0 ? stack.pop() : null;
//                }
//                return tempTree;
//            } else
//                return null;
//        }
//    }
}
