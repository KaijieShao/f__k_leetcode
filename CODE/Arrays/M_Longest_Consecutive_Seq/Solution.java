package CODE.Arrays.M_Longest_Consecutive_Seq;

import java.util.Map; 
import java.util.HashMap;

public class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        // Key: the current integer from `nums` array
        // Value: represents the length of the consecutive sequence that this integer is part of

        int res = 0; 
        // Hold the maximum sequence length found so far while processing the array.

        for (int num : nums) {
            if (!mp.containsKey(num)) {
            // Help avoid duplicate work and is important for finding only unique, consecutive sequences

                int left = mp.getOrDefault(num - 1, 0);
                int right = mp.getOrDefault(num + 1, 0);       
                // Determine if 'num' can be joined to an existing sequence on the left, right, or both to get new length
                
                int sum = left + right + 1;                
                // Left sequence (if any) + right sequence (if any) + current number itself (+1)

                // Now, we get the sequence length for each number (sum)

                mp.put(num, sum); 
                // Then, we update the map

                mp.put(num - left, sum);
                mp.put(num + right, sum);
                // We update both the leftmost and rightmost boundry of the sequence
                // "interior" don't need to have the correct value in map, because the algorithm never queries their value

                res = Math.max(res, sum);
                // Update the result if the new sequence is longer than the current max
            }
        }
        return res;
    }
}
