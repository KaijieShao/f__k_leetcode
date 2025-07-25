package DSA.Arrays;


// Variable-size sliding window is useful when:
// 1. The window size isn't fixed.
// 2. You need to expand/shrink the window to meet constraints (e.g., sum, unique elements, etc.)

public class SlidingWindowVariableSize {
    
    // Q: Find the 'length' of the longest subarray with the same value in each position: O(n)

    public static int longestSubarray(int[] nums) {
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


    // Q: Find length of minimum size subarray where the sum is greater than or equal to the 'target': O(n)
    
    public static int shortestSubarray(int[] nums, int target) {
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

