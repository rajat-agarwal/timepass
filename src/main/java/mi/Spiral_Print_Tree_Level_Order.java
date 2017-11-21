package mi;
import mi.utilities.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by rajat.agarwal on 22/07/17.
 */
public class Spiral_Print_Tree_Level_Order {
    TreeNode root;

    private void add(int v) {
        if (root == null) {
            root = new TreeNode(v);
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode n = queue.remove();
            if (n.left == null) {
                n.left = new TreeNode(v);
                return;
            } else if (n.right == null) {
                n.right = new TreeNode(v);
                return;
            } else {
                queue.add(n.left);
                queue.add(n.right);
            }
        }
    }

    private void printSpiral() {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                TreeNode n = stack1.pop();
                System.out.print(n.data + " ");
                if (n.right != null)
                    stack2.push(n.right);
                if (n.left != null)
                    stack2.push(n.left);
            }
            System.out.println();

            while (!stack2.isEmpty()) {
                TreeNode n = stack2.pop();
                System.out.print(n.data + " ");
                if (n.left != null)
                    stack1.push(n.left);
                if (n.right != null)
                    stack1.push(n.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Spiral_Print_Tree_Level_Order obj = new Spiral_Print_Tree_Level_Order();
        for (int i = 0; i < 100; i++) {
            obj.add(i);
        }
        obj.printSpiral();
    }
}
