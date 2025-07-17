package CODE.Arrays.E_Contains_Duplicate;

import java.util.HashSet;
import java.util.Set;

// Does the array have any duplicate values?

public class Solution {

    // Hash Set

    public boolean hasDuplicate(int[] nums) {

        Set<Integer> seen = new HashSet<>();
        // HashSet: Only stores unique values; no duplicates; no key-value pairs
        // HashMap: Stores unique keys, each with an associated value (key-value pairs). Keys must be unique; values can repeat

        for (int num : nums) {
            if (seen.contains(num)) {
                return true; 
            }
            seen.add(num); 
        }
        return false;
        // If the loop finishes without returning, no duplicates were found; return false
    }
}
