package mi;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rajat.agarwal on 20/07/17.
 */
public class Trie {
    @AllArgsConstructor
    public class Trie_Node {
        Trie_Node[] children;
        boolean isLeaf;
        boolean isVisited; //used for cases where unique paths are required while the trie can be traversed multiple times.

        public Trie_Node() {
            children = new Trie_Node[26];
            isLeaf = false;
        }
    }

    Trie_Node root;

    public Trie() {
        root = new Trie_Node();
    }

    public void add(String word) {
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

    public void add_recursive(String word) {
        add(word.toLowerCase(), 0, root);
    }

    public void add(String word, int idx, Trie_Node n) {
        if (word == null || idx == word.length()) {
            n.isLeaf = true;
            return;
        }
        if (n.children[word.charAt(idx) - 'a'] == null) {
            n.children[word.charAt(idx) - 'a'] = new Trie_Node();
        }
        add(word, idx + 1, n.children[word.charAt(idx) - 'a']);
    }

    public boolean contains_recursive(String word) {
        return contains(word.toLowerCase(), 0, root);
    }

    public boolean contains(String word, int idx, Trie_Node n) {
        if (word == null || n == null) {
            return false;
        }

        if (idx == word.length()) {
            return n.isLeaf;
        }

        return contains(word, idx + 1, n.children[word.charAt(idx) - 'a']);
    }

    private void print_suffix_tree(Trie_Node n, String ret, List<String> suffix) {
        if (n.isLeaf) {
            suffix.add(ret);
        }
        Trie_Node[] children = n.children;
        for (int i = 0; i < children.length; i++) {
            if (children[i] != null) {
                print_suffix_tree(children[i], ret + String.valueOf((char) ('a' + i)), suffix);
            }
        }
    }

    public String[] show_suggestions(String prefix) {
        Trie_Node suffix_root = get_suffix_root(prefix.toLowerCase());
        List<String> suggestions = new ArrayList<>();
        print_suffix_tree(suffix_root, prefix, suggestions);
        System.out.println(suggestions);
        return suggestions.toArray(new String[0]);
    }

    public Trie_Node get_suffix_root(String prefix) {
        Trie_Node n = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (n.children[prefix.charAt(i) - 'a'] == null) {
                return null;
            }
            n = n.children[prefix.charAt(i) - 'a'];
        }
        return n;
    }

    public boolean contains(String word) {
        Trie_Node n = root;
        for (Character c : word.toLowerCase().toCharArray()) {
            if (n.children[c - 'a'] == null) {
                return false;
            }
            n = n.children[c - 'a'];
        }
        return n.isLeaf;
    }

    public int find_num_words_matching_input_missing_one_char(String word) {
        return find_num_words_matching_input_missing_one_char(word.toLowerCase(), 0, root, 1);
    }

    public int find_num_words_matching_input_missing_one_char(String word, int idx, Trie_Node n, int missing_characters) {
        if (n == null) {
            return 0;
        }
        if (idx >= word.length()) {
            return n.isLeaf ? 1 : 0;
        }
        int count = 0;

        if (n.children[word.charAt(idx) - 'a'] != null) {
            count = find_num_words_matching_input_missing_one_char(word, idx + 1, n.children[word.charAt(idx) - 'a'], missing_characters);
        }
        if (missing_characters > 0) {
            for (int i = 0; i < n.children.length; i++) {
                count += find_num_words_matching_input_missing_one_char(word, idx, n.children[i], missing_characters - 1);
            }
        }
        return count;
    }

    private static void test_iterative() {
        Trie trie = new Trie();
        trie.add("apple");
        trie.add("app");
        System.out.println(trie.contains("ap"));
        System.out.println(trie.contains("app"));
        System.out.println(trie.contains("appl"));
        System.out.println(trie.contains("apple"));
    }

    private static void test_recursive() {
        Trie trie = new Trie();
        trie.add_recursive("apple");
        trie.add_recursive("app");
        System.out.println(trie.contains_recursive("ap"));
        System.out.println(trie.contains_recursive("app"));
        System.out.println(trie.contains_recursive("appl"));
        System.out.println(trie.contains_recursive("apple"));
    }

    private static void test_find() {
        Trie trie = new Trie();
        trie.add("tree");
        trie.add("trie");
        trie.add("algo");
        trie.add("assoc");
        trie.add("all");
        trie.add("also");
        System.out.println(trie.find_num_words_matching_input_missing_one_char("tre"));
        System.out.println(trie.find_num_words_matching_input_missing_one_char("tree"));
        System.out.println(trie.find_num_words_matching_input_missing_one_char("t"));
        System.out.println(trie.find_num_words_matching_input_missing_one_char("ll"));
    }

    public static void main(String[] args) {
        test_iterative();
        System.out.println("---Iterative Done-------");
        test_recursive();
        System.out.println("---Recursive Done-------");
        test_find();
    }
}
