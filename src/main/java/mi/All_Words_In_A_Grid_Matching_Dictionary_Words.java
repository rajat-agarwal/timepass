package mi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* Given a grid of characters and dictionary. Print all the words that can be formed traversing in the grid and
* are availalbe in the dictionary. You can move to any of the 8 directions from any cell.
* You should not select a cell again if already selected in a word.
* You should only print unique words.
* You can also move circular in all the directions for eg. from row 0 to last row.
* */
public class All_Words_In_A_Grid_Matching_Dictionary_Words {
    private static class Trie_Node {
        Trie_Node[] children;
        boolean isLeaf;
        boolean isVisited;

        public Trie_Node() {
            children = new Trie_Node[26];
            isLeaf = false;
            isVisited = false;
        }
    }

    private static Trie_Node root = new Trie_Node();

    private static void add(String word) {
        Trie_Node n = root;
        word = word.toLowerCase();
        int i = 0;
        for (; i < word.length(); i++) {
            if (n.children[word.charAt(i) - 'a'] == null) {
                n.children[word.charAt(i) - 'a'] = new Trie_Node();
            }
            n = n.children[word.charAt(i) - 'a'];
        }
        if (i == word.length()) {
            n.isLeaf = true;
        }
    }

    private static void makeDictionaryTrie(String[] words) {
        for (String w : words)
            add(w);
    }

    private static final int[][] directions = {
            {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}
    };

    private static void printList(List<String> strings) {
        Collections.sort(strings);
        for (String s : strings)
            System.out.print(s + " ");
        System.out.println();
    }

    private static void recurse(char[][] grid, int x, int y, Trie_Node root, StringBuilder sb, boolean[][] visited, List<String> ret) {
        if (root.isLeaf) {
            if (!root.isVisited) {
//                System.out.print(sb.toString() + " ");
                root.isVisited = true;
                ret.add(sb.toString());
            }
            return;
        }
        for (int[] dir : directions) {
            int newx = (x + dir[0] + grid.length) % grid.length; //for circular movement
            int newy = (y + dir[1] + grid[0].length) % grid[0].length; //for circular movement
            // the new coordinates will always be valid hence no need to write check function
            if (!visited[newx][newy] &&
                    root.children[grid[newx][newy] - 'a'] != null) {
                sb.append(grid[newx][newy]);
                visited[newx][newy] = true;
                recurse(grid, newx, newy, root.children[grid[newx][newy] - 'a'], sb, visited, ret);
                sb.setLength(sb.length() - 1);
                visited[newx][newy] = false;
            }
        }
    }

    private static void printAllValidWords(char[][] grid, Trie_Node root) {
        StringBuilder sb = new StringBuilder();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        List<String> ret = new ArrayList<>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (root.children[grid[r][c] - 'a'] != null) {
                    sb.append(grid[r][c]);
                    visited[r][c] = true;
                    recurse(grid, r, c, root.children[grid[r][c] - 'a'], sb, visited, ret);
                    sb.setLength(sb.length() - 1);
                    visited[r][c] = false;
                }
            }
        }
        printList(ret);
    }

    public static void main(String[] args) {
//        char[][] grid = {
//                {'g', 'i', 'z'},
//                {'u', 'e', 'k'},
//                {'q', 's', 'e'}
//        };
//        String[] words = {"geeks", "for", "quiz", "go"};
//        char[][] grid = {
//                {'d', 'd', 'b', 'f', 'e'},
//                {'c', 'b', 'c', 'd', 'c'}
//        };
//        String[] words = {"db", "bcd"};
        char[][] grid = {
                {'f', 'd'},
                {'b', 'c'},
                {'e', 'f'},
                {'d', 'c'},
                {'c', 'f'}
        };
        String[] words = {"b", "b", "dff", "b", "ec", "efe"};
        makeDictionaryTrie(words);
        printAllValidWords(grid, root);
    }
}
