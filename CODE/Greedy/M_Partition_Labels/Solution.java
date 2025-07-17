package CODE.Greedy.M_Partition_Labels;

// Split the input string 's' into the most parts possible so that no letter appears in more than one part.
// For each part, all occurrences of its letters are inside that part and nowhere else.
// Return a list of the lengths of these parts, in order.
// Example:
// Input: "xyxxyxzbzbbisl"
// Output: [5, 5, 1, 1, 1] (splits: "xyxxy", "zbzbb", "i", "s", "l")

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Solution {

    // Two Pointers (Greedy)

    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        // Key:   Each character in the string.
        // Value: The last index (position) where that character appears in the string.

        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }
        // For each character, update its last occurrence position
        
        List<Integer> res = new ArrayList<>();
        // Prepare result list and two pointers for partitioning

        int size = 0; // Length of current partition
        int end = 0;  // Right boundary where current partition must stop (to include every repeat)

        for (int i = 0; i < s.length(); i++) {
            size++;
            // Increment current partition size

            end = Math.max(end, lastIndex.get(s.charAt(i)));
            // end is updated to the maximum of:
            // - The current value of 'end'
            // - The last occurrence index of the current character (s.charAt(i))

            // This ensures: 
            // If you see a char whose last appearance is farther than current end, you extend partition boundary to include it
            // AND you always cover all repeats of any character seen so far.

            if (i == end) {
            // You reached the rightmost index needed to include all repeats of every character seen so far in current partition
            // All characters from the start of this partition up to index 'i' will NOT appear again later in the string
            // So, this spot is the earliest you can split to guarantee no letter from the current part leaks into the next part

                res.add(size); // Store partition size in result
                size = 0;      // Reset size for next partition
            }
        }
        return res;
        // Return list of partition sizes
    }
}


