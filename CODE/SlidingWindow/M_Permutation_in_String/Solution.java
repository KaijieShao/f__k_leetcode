package CODE.SlidingWindow.M_Permutation_in_String;

// Permutation（排列）：
// 把一组东西的顺序随便换，每种顺序都算一种 permutation。
// 比如 "abc" 的 permutation 有 "abc"、"acb"、"bac"、"bca"、"cab"、"cba"。只要顺序不一样，就是不同的 permutation。

// 这道题在问什么？
// 给你两个字符串 s1 和 s2。
// 问：s2 里有没有一段 “连续” 的子字符串，它的字母和 s1 完全一样（顺序可以不一样），只要字母和数量对得上就行。

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) { // A permutation of s1 cannot exist if s2 is shorter than s1.
            return false;
        }

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        // Creates two frequency arrays for the 26 lowercase English letters.

        // 1st: Lock the initial sliding window
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++; // Convert the character at index i in each string to a number from 0 to 25
            s2Count[s2.charAt(i) - 'a']++; // i.e., 'a' -> 0, 'b' -> 1, 'c' -> 2, ... 'z' -> 25
        }

        // 2nd: Counts how many characters (a-z) have the same frequency in both arrays within that window
        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] == s2Count[i]) { // Order does not matter because: both have the same number of the i-th character
                matches++;                  // The positions of the characters can be different
            }
        }

        // When you find a window with exact frequency matches, s2 contains a permutation of s1 at that position.

        int l = 0; // left pointer of the sliding window

        for (int r = s1.length(); r < s2.length(); r++) {
        // 1. Starts at s1.length() (right after the initial window)
        // 2. Stops at s2.length()
        // 3. Checks every substring window in s2 of length equal to s1

            if (matches == 26) { 
                return true;
            }
            // For a window to be a permutation of s1, the frequency of every letter 'a-z' must match (including zeros)

            // Handles adding new character at right end of sliding window in s2 as you move window forward by one position.
            int index = s2.charAt(r) - 'a';           // Get the index of the new character entering the window
            s2Count[index]++;                         // Increment its count in s2Count
            if (s1Count[index] == s2Count[index]) {
                matches++;                            // If counts become equal, there's a new match
            } else if (s1Count[index] + 1 == s2Count[index]) {
                matches--;                            // If counts were equal before this increment, we lose a match
            }
            // Only triggers when you break an actual match (from equal to not equal):
            // Suppose for a character 'a':
            // 1. Before: s1Count['a'] = 2, s2Count['a'] = 2 (match)
            // 2. Add 'a' to window: s2Count['a'] becomes 3
            // 3. Now: s1Count['a'] + 1 == s2Count['a'] (2 + 1 == 3)
            // 4. So, you lost a match for 'a', so matches--

            // If there was never a match for a character, you should not change matches++ or matches-- for it.

            // --- Remove the character at the left of the window ---
            index = s2.charAt(l) - 'a';              // Get the index of the character leaving the window
            s2Count[index]--;                        // Decrement its count 
            if (s1Count[index] == s2Count[index]) {
                matches++;                           // Counts are now equal, so you gain a match
            } else if (s1Count[index] - 1 == s2Count[index]) {
                matches--;                           // Counts were equal before, now you lost a match
            }

            l++; // Slide the window forward.
        }
        return matches == 26; 
        // Return true if all 26 character counts match in the last window checked.
        // Return false if not.
    }
}

