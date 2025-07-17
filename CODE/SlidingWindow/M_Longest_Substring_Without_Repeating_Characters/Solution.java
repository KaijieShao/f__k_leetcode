package CODE.SlidingWindow.M_Longest_Substring_Without_Repeating_Characters;

// The question is asking you to find the longest part of a given string (a substring) where no character repeats.

import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> mp = new HashMap<>();
        // A HashMap to store characters we've seen and their most recent index.

        int l = 0, res = 0;
        // 'l' is the left pointer of our sliding window. 'res' stores the maximum length found so far.
        
        for (int r = 0; r < s.length(); r++) {
            if (mp.containsKey(s.charAt(r))) {
             // Checks if the character at position r has already appeared in the substring being considered.

                l = Math.max(mp.get(s.charAt(r)) + 1, l);
                // Moves the left pointer l to just after the last occurrence of the duplicate character
                // But, if your current l is already ahead, you do NOT move it back
            }
            mp.put(s.charAt(r), r);
            // Adds or updates the character at position r in the HashMap.

            res = Math.max(res, r - l + 1);
            // Calculates the current window's length and updates 'res' if it's the new maximum.
        }
        return res;
        // After the loop, it returns the maximum length found.
    }
}


