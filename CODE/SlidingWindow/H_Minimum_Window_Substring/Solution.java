package CODE.SlidingWindow.H_Minimum_Window_Substring;

// Find the shortest substring in string s that contains all characters from string t (including duplicates).
// If no such substring exists, return an empty string.

import java.util.Map;
import java.util.HashMap;

public class Solution {
    public String minWindow(String s, String t) {
        if (t.isEmpty()) return ""; // The question asked to return "" if substring does not exist

        Map<Character, Integer> countT = new HashMap<>(); // Target character counts
        Map<Character, Integer> window = new HashMap<>(); // Current window character counts for matching

        // 1st: Prepares target character counts
        for (char c : t.toCharArray()) { // Converts the string t into an array of characters (char[])
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        int have = 0, need = countT.size();
        // have: number of unique characters in the current window that meet the required count.
        // need: total unique characters required (from t).

        int[] res = {-1, -1}; 
        // Stores the start and end indices of the smallest valid window found.
        // If no window is found, these values stay as -1, -1 and are used to return an empty string at the end.

        int resLen = Integer.MAX_VALUE; 
        // Tracks the length of the smallest valid window.
        // Start with the largest possible value -> as you find smaller valid windows, you update resLen downward.

        int l = 0; // Left pointer

        // 2nd: Finds the minimum window in s containing all characters of t.
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r); 
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (countT.containsKey(c) && window.get(c).equals(countT.get(c))) {
                have++;
            }
            // Increases the count of unique required characters that are fully matched

            // Inner: Current window contains all required unique characters with the correct counts.
            while (have == need) {
                if ((r - l + 1) < resLen) {
                    resLen = r - l + 1;
                    res[0] = l; // start index
                    res[1] = r; // end index
                }
                // Calculate the length of the current window and update start & end indices for the new smallest window found

                // Now, let's implement the left shift of the sliding window!

                char leftChar = s.charAt(l); 
                // Gets the character at the current left boundary of the window.

                window.put(leftChar, window.get(leftChar) - 1);
                // Decrements the count of leftChar in the window map (since the window will move right).

                if (countT.containsKey(leftChar) && window.get(leftChar) < countT.get(leftChar)) {
                    have--;
                }
                // If leftChar is required and its count in the window drops below the required count, decrement have.

                l++;
                // Move the left pointer right to shrink the window.
            }
        }

        return resLen == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1] + 1);
        // If resLen is still Integer.MAX_VALUE, no valid window was found—return an empty string.
        // Otherwise, return s from res[0] to res[1] (inclusive), which is the min window contain all characters of t
    }
}

