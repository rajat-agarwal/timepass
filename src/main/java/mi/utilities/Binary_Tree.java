package mi.utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Binary_Tree {
    TreeNode root;
    public Binary_Tree(List<Integer> in){
        Queue<Integer> input = new LinkedList<>(in);
        if(root == null)
            root = new TreeNode(input.remove());
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (input.size() > 1){
            TreeNode n = queue.remove();
            n.left = new TreeNode(input.remove());
            n.right = new TreeNode(input.remove());
            queue.add(n.left);
            queue.add(n.right);
        }
    }
    public TreeNode getRoot(){
        return root;
    }

    public void print(){
        BTreePrinter.printNode(root);
    }

    public static void main(String[] args) {
        List<Integer> queue = new ArrayList<>(20);
        for (int i=1;i<=20;i++)
            queue.add(i);
        Binary_Tree bt = new Binary_Tree(queue);
        bt.print();

    }
}
