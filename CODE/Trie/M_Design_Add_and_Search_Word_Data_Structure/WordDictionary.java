package CODE.Trie.M_Design_Add_and_Search_Word_Data_Structure;

// Implement a class WordDictionary with two APIs:
// 1. void addWord(String word) – store word.
// 2. boolean search(String word) – return true if any stored word matches word, where ‘.’ in word can match any single letter.


public class WordDictionary {  

    // Depth First Search (Trie)
    
    class TrieNode {  
        boolean word;  
        TrieNode[] children;          
        // array of 26 children pointers, one per lowercase letter

        public TrieNode() {  
            children = new TrieNode[26];  
            word = false;  
            // initially no word ends here
        }  
    }

    private TrieNode root;  
    // root of the Trie, serves as entry point for all operations

    public WordDictionary() {  
        root = new TrieNode();  
    }


    public void addWord(String word) {  
        TrieNode cur = root;  
        // start from the root for insertion

        for (char c : word.toCharArray()) {  
            if (cur.children[c - 'a'] == null) {  
            // if path for this character doesn't exist

                cur.children[c - 'a'] = new TrieNode();  
                // create a new node for this character
            }  
            cur = cur.children[c - 'a'];  
            // move to the child node corresponding to c
        }  
        cur.word = true;  
        // mark the final node as a word endpoint
    }


    public boolean search(String word) {  
        return dfs(word, 0, root);  
        // trigger depth‐first search from index 0 at root
    }

    // Path-based DFS (somewhat like PreOrder)
    private boolean dfs(String word, int j, TrieNode root) { 
    // word: full query (including wildcards: '.' matches exactly one letter at that position)
    // j:    current character index in 'word'
    // root: the trie node at which this call starts searching

        TrieNode cur = root;  
        // start this DFS from given root node

        for (int i = j; i < word.length(); i++) {  
        // iterate characters from position j to end
        // 1. j marks how many chars of word you’ve already matched up to this call.
        // 2. Starting the loop at i = j means “skip the first j indices—they’re done—now match from index j onward.”
        // 3. If you always started at 0, you’d reprocess already-matched characters and break the logic.

            char c = word.charAt(i);  
            // current character to match

            if (c == '.') {  
            // wildcard: can match any one letter

                for (TrieNode child : cur.children) {  
                // try every non-null branch

                    if (child != null && dfs(word, i + 1, child)) {  
                    // child != null guards against a missing branch (avoids NPE)
                    // && short-circuits: you only recurse when child exists
                    // dfs(word, i+1, child) recursively checks if rest of pattern (from i+1 onward) matches down child node

                        return true;  
                        // propagate success
                    }  
                }  
                return false;  
                // no branch matched the wildcard, fail

            } else {  
                if (cur.children[c - 'a'] == null) {  
                    // no matching letter branch

                    return false;  
                    // fail immediately
                }  
                cur = cur.children[c - 'a'];  
                // move to matching child node
            }  
        }  
        return cur.word;  
        // after all chars, return true if at end‐of‐word flag
    }  
}

