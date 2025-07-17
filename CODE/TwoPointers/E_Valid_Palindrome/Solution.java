package CODE.TwoPointers.E_Valid_Palindrome;

public class Solution {
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) { 
        // Ignores spaces, punctuation, and symbols

            // Skips all non-alphanumeric characters from the left side
            while (l < r && !alphaNum(s.charAt(l))) {
            // 1. s.charAt(l): Gets the character at index l from string s
            // 2. alphaNum(...): Calls a helper function to check if the character is alphanumeric (letter or digit)
                l++; 
            }

            // Skips all non-alphanumeric characters from the right side.
            while (r > l && !alphaNum(s.charAt(r))) {
                r--; 
            }

            // After the two inner loops, both l and r point only to valid alphanumeric characters
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
            // Compares only these valid characters (case-insensitive)
                return false; 
            }

            l++; 
            r--; 
        }

        return true; 
        // If all characters matched, it is a palindrome
    }

    public boolean alphaNum(char c) {      // Helper function to check if a character is alphanumeric
        return (c >= 'A' && c <= 'Z' ||    // Check if uppercase letter
                c >= 'a' && c <= 'z' ||    // Check if lowercase letter
                c >= '0' && c <= '9');     // Check if digit
    }
}
