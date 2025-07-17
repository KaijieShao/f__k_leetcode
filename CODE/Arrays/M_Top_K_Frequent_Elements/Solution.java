package CODE.Arrays.M_Top_K_Frequent_Elements;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// Find the k most frequent numbers in the array nums.
// Example: If nums = [1,1,1,2,2,3] and k = 2, return [1,2] (since 1 appears 3 times, 2 appears 2 times).

public class Solution {

    // Bucket Sort

    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer, Integer> count = new HashMap<>();
        // Key:   each number
        // Value: its frequency 

        List<Integer>[] freq = new List[nums.length + 1];
        // Bucket Sort
        // In traditional BucketSort, buckets are indexed by value; but here, buckets are indexed by frequency count
        // i.e.,    -> [1, 1, 1, 2, 2, 3]
        // FYI: Array/list indices start from 0, so to access index 6, you need an array of size 7 
        //      Index 0 is unused because no element appears 0 times, but you must allocate it to make index 6 valid!
        // i(count) -> [0, 1, 2, 3, 4, 5, 6] 
        // values   -> [   3, 2, 1         ]
        
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }
        // Bucket: Each index in freq is a bucket for numbers with that frequency (empty at first -> filled later)

        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        // HashMap: After this loop, count contains each unique number from nums as keys, and their frequency as values

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) { 
            freq[entry.getValue()].add(entry.getKey());
        }
        // Bucket: Each freq[i] contains all numbers that appeared exactly i times in the input array

        int[] res = new int[k];
        // The array will have k slots, all initialized to 0 by default
        
        int index = 0;
        // Index pointer for filling the result array

        for (int i = freq.length - 1; i > 0 && index < k; i--) { 
        // Outer: Starts at the rightmost bucket (highest possible frequency) && Move left until all K results are found

            for (int n : freq[i]) { 
            // Inner: Goes through all numbers appeared i times (If multiple numbers have the same frequency, then all included)

                res[index++] = n; 
                // This is a shorthand for: 
                // res[index] = n;
                // index++;

                if (index == k) return res;                
                // No more loops or processing after this—the function returns the result array right away
            }
        }
        return res;
        // If the loop already returned early (when index == k), this end return is just a fallback (safety return)
    }
}


