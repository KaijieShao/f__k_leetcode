package DSA.Trees;


import java.util.Map;
import java.util.HashMap;

public class Trie {

    // Scenario:
    // Trie is designed to solve the problem of efficiently storing and searching for strings, especially prefixes
    // 1. Fast insertion, search, and prefix-matching for words in a dictionary
    // 2. Quickly answers if a word exists, or if any word starts with a given prefix
    // 3. Commonly used for autocomplete, spell checking, word games, and IP routing

    class TrieNode {
        boolean word;                                         // True if the 'word' is COMPLETE i.e., 'car'
        Map<Character, TrieNode> children = new HashMap<>();  // Key: Next character, Value: TrieNode object
    }
    

    TrieNode root;                                            // Dummy node like a pointer and acts as a starter
    public Trie() {
        root = new TrieNode();                              
    }


    public void insert(String word) {                         // 2nd word only created when two words are diverged
        TrieNode curr = this.root;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {              // curr.children holds mapings from real characters
                curr.children.put(c, new TrieNode());         // Adds a new entry (character 'c' and 'TrieNode')
            }
            curr = curr.children.get(c);                      // Follow path for each character, 1 node at a time
        }
        curr.word = true;                                     // word is complete
    }


    public boolean search(String word) {                      // Checks for a full word match
        TrieNode curr = this.root;
        for (char c : word.toCharArray()) {
            if (!curr.children.containsKey(c)) {              
                return false;
            }
            curr = curr.children.get(c);                      
        }
        return curr.word;                                     // 'word' must match the whole string to be true
    }


    public boolean startsWith(String prefix) {                // Checks for a prefix path only
        TrieNode curr = this.root;
        for (char c : prefix.toCharArray()) {
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;                                          // 'prefix' can be any length ≤ word
    }
}


