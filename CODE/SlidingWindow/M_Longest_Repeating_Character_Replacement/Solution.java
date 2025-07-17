package CODE.SlidingWindow.M_Longest_Repeating_Character_Replacement;

// 1. You are given a string s (all uppercase letters) and an integer k.
// 2. You can change up to k characters in s to any other uppercase letter.
// 3. After make at most k changes, find the length of the longest possible substring where all characters are the same.

import java.util.HashMap;

public class Solution {
    public int characterReplacement(String s, int k) {
        HashMap<Character, Integer> count = new HashMap<>();
        // A HashMap to store the frequency of each character within our current window.

        int res = 0;
        // 'res' will store the maximum length of a valid window found so far.

        int l = 0, maxf = 0;
        // 'l' is the left pointer of our window. 
        // 'maxf' tracks the frequency of the most frequent character in the current window.

        // Outer: Expands the sliding window to the right by moving the right pointer (r) forward one character at a time
        for (int r = 0; r < s.length(); r++) {
            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
            // The updated count is always the previous value plus 1 (or just 1 if it wasn't present before)

            maxf = Math.max(maxf, count.get(s.charAt(r)));
            // Updates 'maxf' to ensure it always holds the count of the most frequent character in the current window.

            // maxf only tracks the highest frequency of a character within the current window, not globally for entire string.

            // Inner: Shrinks the window from the left (l) if the number of replacements needed exceeds k
            while ((r - l + 1) - maxf > k) {
            // If this value is greater than k, it means you would need more than k replacements
            // So you don't have enough allowance to make the window valid. The window must be shrunk from the left.

                count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
                // Decreases the count of the character at the left pointer (l) in the map

                l++;
                // After this, l++ moves the left pointer right, effectively shrinking the window from the left
            }

            // After the inner while, the window satisfies (window size) − (maxf) ≤ k.
            // This means: the substring in the current window can be made all the same character with at most k replacements.

            res = Math.max(res, r - l + 1);
            // Updates res to be the maximum of its current value and the current window size (r - l + 1)
        }

        return res;
        // After the loop, it returns the maximum valid length found.
    }
}

// 19:19
