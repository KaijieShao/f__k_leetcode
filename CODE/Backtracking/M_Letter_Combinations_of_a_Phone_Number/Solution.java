package CODE.Backtracking.M_Letter_Combinations_of_a_Phone_Number;

// Given a string of digits (from 2 to 9), each digit maps to certain letters (like the letters on a phone keypad)
// You need to return all possible combinations of those letters, where each digit is replaced by one of its possible letters

import java.util.*;

public class Solution {

    // Backtracking 

    private List<String> res = new ArrayList<>();
    // Declares a private list to store the final combinations

    private String[] digitToChar = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "qprs", "tuv", "wxyz"
    };
    // Maps each digit to its corresponding string of letters on the phone keypad.
    // Index 2 = "abc", Index 3 = "def", etc.
    // Indexes 0 and 1 are unused.

    public List<String> letterCombinations(String digits) {
    // Main function called with input digits

        if (digits.isEmpty()) return res;
        // If input is empty, return empty result list

        backtrack(0, "", digits);
        // Start backtracking from position 0, with empty current string

        return res;
    }

    private void backtrack(int i, String curStr, String digits) {
    // int i:         Tracks the current position in the input digits string—shows which digit you are building letters for.
    // String curStr: Holds the letter combination built so far—accumulates one letter per digit as you recurse.
    // String digits: The original input digits—needed every time to know which digit/letters to process.

        if (curStr.length() == digits.length()) {
            res.add(curStr);
            return;
        }
        // If current string's length equals input length, add current string to result list

        String chars = digitToChar[digits.charAt(i) - '0'];
        // digits.charAt(i) gets the character at position i in the input string (if digits = "34" and i = 0, it gets '3').
        // '3' - '0' converts the character '3' to the integer 3 (In Java, characters are stored as ASCII values)
        // digitToChar[3] uses that integer as an index and looks up the string of letters for digit 3, which is "def".
        // The result is: chars is set to "def", which are the possible letters for the current digit.

        for (char c : chars.toCharArray()) {
        // Loop over each possible letter

            backtrack(i + 1, curStr + c, digits);
            // Recursively build combinations by appending letter and moving to next digit
        }
    }
}


