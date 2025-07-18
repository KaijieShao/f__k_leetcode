package DSA.DPs;

// Q: Given a string S, return the length of the longest palindromic substring within S

public class Palindrome {
    
    // Time: O(n^2), Space: O(n)
    public static int longest(String s) {
        int length = 0;
        // Stores the length of the longest palindrome found so far

        for (int i = 0; i < s.length(); i++) {
        // Iterate through each character in the string as a possible center of a palindrome

            // Odd Length
            int l = i, r = i;
            // Both pointers start at i (center)

            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            // l >= 0: left pointer is within the left bound of the string
            // r < s.length(): right pointer is within the right bound of the string
            // s.charAt(l) == s.charAt(r): characters at the left and right pointers match

                if (r - l + 1 > length) {
                    length = r - l + 1;
                }
                // r - l + 1 calculates the current palindrome substring's length (from index l to r).
                // length stores the longest palindrome length found so far.
                // If the current palindrome is longer (r - l + 1 > length), update length.

                l--;   // Move left pointer left
                r++;   // Move right pointer right
            }

            // Even Length
            l = i; 
            r = i + 1;
            // Even-length palindromes (like "abba") have their center between two characters, not on a single character.
            // If you only started both pointers at i, you would miss all even-length palindromes.
            // So, r = i + 1 is required for proper detection of every even-length palindrome.

            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > length) {
                    length = r - l + 1;
                }
                l--;
                r++;
            }
        }
        return length;
        // Return the length of the longest palindrome found
    }


    // Same solution, without duplicate code.
    // Time: O(n^2), Space: O(n)
    public static int longest2(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
        // Try every index as palindrome center

            length = Math.max(length, helper(s, i, i));
            // Odd length: center at i (single character center)
            
            length = Math.max(length, helper(s, i, i + 1)); 
            // Even length: center between i and i+1 (double character center)
        }
        return length;
    }

    public static int helper(String s, int l, int r) {
    // Expands around center l, r to get the length of longest palindrome at that center

        int maxLength = 0;
        // Tracks max palindrome length for this center

        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            if (r - l + 1 > maxLength) {
                maxLength = r - l + 1;
                // Update length if current palindrome is longer
            }
            l--;  // Move left pointer left
            r++;  // Move right pointer right
        }
        return maxLength;
        // Return max found for this center
    } 
}



