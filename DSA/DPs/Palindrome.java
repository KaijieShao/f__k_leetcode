package DSA.DPs;


public class Palindrome {
    
    // Scenario:
    // Given a string, find the longest palindromic substring within it
    // A palindrome reads the same forward and backward

    // Time: O(n^2), Space: O(n)
    public static int longest(String s) {
        int length = 0;

        for (int i = 0; i < s.length(); i++) {
            // odd length
            int l = i, r = i;                              
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > length) {   // Checker: if the current palindrome is the longest
                    length = r - l + 1;                    
                }
                l--;
                r++;
            }

            // even length
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > length) {
                    length = r - l + 1;
                }
                l--;
                r++;
            }
        }

        return length;
    }



    // Time: O(n^2), Space: O(n) -> Same solution, without duplicate code.
    public static int longest2(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            // odd length
            length = Math.max(length, helper(s, i, i));      // i, i → odd-length (centered at one character)
            
            // even length
            length = Math.max(length, helper(s, i, i + 1));  // i, i+1 → even-length (centered between 2 character)
        }
        return length;
    }

    public static int helper(String s, int l, int r) {       // Helper: confirm and measure the palindrome
        int maxLength = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            if (r - l + 1 > maxLength) {
                maxLength = r - l + 1;
            }
            l--;
            r++;
        }
        return maxLength;
    } 
}

