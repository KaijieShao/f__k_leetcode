package CODE.Arrays.M_Group_Anagrams;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// Group words that are anagrams of each other into separate sublists

public class Solution {

    // Hash Table

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> res = new HashMap<>();
        // Key:   String representation of the 26-letter count array (e.g., "[1, 0, 0, ...]" for letter frequencies)
        // Value: List of strings that are anagrams (grouped by that letter frequency signature).

        for (String s : strs) {            
            int[] count = new int[26];
            // Every word gets its own 26-element count array to track letter frequencies            

            for (char c : s.toCharArray()) {
                count[c - 'a']++; 
                // Increases the count of the letter 'c' in the word 's'

                // 1. 'a' to 'z' have consecutive ASCII values: 'a' = 97, 'b' = 98, ..., 'z' = 122
                // 2. For each character, 'c' - 'a' calculates the index from 0 to 25
                //    i.e., if 'c' = 'b', then 'c' - 'a' = 98 - 97 = 1
                //          This means count[1] is incremented for the letter 'b'
            }

            String key = Arrays.toString(count);
            // i.e., [1, 2, 3] -> "[1, 2, 3]"

            res.putIfAbsent(key, new ArrayList<>());
            // If key is not in the map, add it with an empty ArrayList as its value
            // If key already exists, do nothing (existing list is kept)

            res.get(key).add(s);
            // Gets the list for this key and adds the word s to it
        }

        return new ArrayList<>(res.values());
        // Each inner list is a group of anagrams
    }
}


