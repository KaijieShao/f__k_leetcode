package CODE.Trie.M_Implement_Trie;

// You need to build a Trie (prefix tree) class that supports:
// 1. PrefixTree() – initialize the empty trie.
// 2. void insert(String word) – add word into the trie.
// 3. boolean search(String word) – return true if exactly word was inserted before.
// 4. boolean startsWith(String prefix) – return true if any inserted word begins with prefix.

import java.util.HashMap;

public class PrefixTree {

    class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean endOfWord = false;
    }

    private TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
        }
        cur.endOfWord = true;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return cur.endOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            if (!cur.children.containsKey(c)) {
                return false;
            }
            cur = cur.children.get(c);
        }
        return true;
    }
}
