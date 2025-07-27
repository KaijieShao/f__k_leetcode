package DSA.Arrays;


public class SlidingWindowVariableSize {
    
    // Scenario:
    // You need to compute results (like max/min/condition) on subarrays,
    // But the window size is NOT fixed — it can grow or shrink as needed to meet some criteria.

    public static int longestSubarray(int[] nums) {
    // Q: Find the 'length' of the longest subarray with the same value in each position: O(n)

        int length = 0;                              // Track the maximum length found so far
        int L = 0;                                   // Marks the beginning of the current subarray

        for (int R = 0; R < nums.length; R++) {      
            if (nums[L] != nums[R]) {                // Current element 'R' is DIFFERENT from 'L'
                L = R;                               // Pattern Broken -> Reset 'L' to current position 'R'
            }
            length = Math.max(length, R - L + 1);   
            // 1. Calculate the current subarray of identical elements (from L to R)
            // 2. Update 'length' if this subarray is longer than the max length found so far
        }
        return length;
    }

    
    public static int shortestSubarray(int[] nums, int target) {
    // Q: Find length of minimum size subarray where the sum is greater than or equal to the 'target': O(n)

        int L = 0, total = 0;                            // 'total' stores sum of the elements in current window
        int length = Integer.MAX_VALUE;                  // search 'minimum', set to 'MAX_VALUE', vice versa

        for (int R = 0; R < nums.length; R++) {          
            total += nums[R];                            // 'R' is added to the window's total sum
            while (total >= target) {                    // Found a valid candidate!
                length = Math.min(R - L + 1, length);    
                total -= nums[L];                        // Removes the leftmost element from current window
                L++;                                     // Move 'left' 1 step to the 'right', complete the 'slide'
            }
        }

        if (length ==  Integer.MAX_VALUE) {              // Checked all possibilities, 'length' never changed
            return 0;                                    
        } 
        return length;                                  
    }
}



