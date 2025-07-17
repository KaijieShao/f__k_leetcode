package CODE.Arrays.E_Valid_Anagram;

import java.util.HashMap;

// Do the two strings have exactly the same characters, with the same counts, but possibly in a different order?

public class Solution {

    // Hash Map

    public boolean isAnagram(String s, String t) {
        
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> countS = new HashMap<>();
        HashMap<Character, Integer> countT = new HashMap<>();
        // Key: a character from the string
        // Value: the count (number of times that character appears in the string)

        for (int i = 0; i < s.length(); i++) {
            countS.put(s.charAt(i), countS.getOrDefault(s.charAt(i), 0) + 1);              
            countT.put(t.charAt(i), countT.getOrDefault(t.charAt(i), 0) + 1);
        }
        
        return countS.equals(countT);
    }
}






