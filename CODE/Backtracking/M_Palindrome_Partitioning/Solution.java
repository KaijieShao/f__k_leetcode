package CODE.Backtracking.M_Palindrome_Partitioning;

// Split the input string 's' into parts so that every part is a palindrome (reads the same forward and backward).
// Find all possible ways to split 's' this way.

import java.util.*;

public class Solution {

    // Backtracking - II
    
    public List<List<String>> partition(String s) {
    // Returns a list of list of strings, representing all possible palindrome partitions

        List<List<String>> res = new ArrayList<>();   // All valid palindrome partitions
        List<String> part = new ArrayList<>();        // Current partition being built
        dfs(0, s, part, res);                       // Start DFS from index 0
        return res;
    }

    private void dfs(int i, String s, List<String> part, List<List<String>> res) {
        if (i >= s.length()) {
            res.add(new ArrayList<>(part));
            return;
        }
        // Base Case: If current index 'i' has reached the end of the string, add a copy to 'res' and END this recursive path

        for (int j = i; j < s.length(); j++) {
        // j = i guarantees that no possible starting position or substring is skipped, so all partition paths are explored

            if (isPali(s, i, j)) {
            // Check if the substring from 'i' to 'j' is a palindrome:

                part.add(s.substring(i, j + 1));
                // If so, add this substring to the current partition
                // i.e., If s = "apple", then s.substring(1, 4) returns "ppl" (characters at index 1, 2, 3)

                dfs(j + 1, s, part, res);
                // Recursively call dfs for the next starting index after 'j'

                part.remove(part.size() - 1);
                // Backtrack: remove the last substring added to explore other possible partitions
            }
        }
    }

    private boolean isPali(String s, int l, int r) {
    // Checks if substring from index 'l' to 'r' is a palindrome
    
        while (l < r) {
        // Continue checking while 'l' is less than 'r'

            if (s.charAt(l) != s.charAt(r)) {
                return false;
            } // If the characters at 'l' and 'r' don't match
            
            l++;
            r--;
        }
        return true;
        // If all pairs match, substring is a palindrome
    }
}


