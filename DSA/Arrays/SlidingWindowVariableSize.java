package DSA.Arrays;

// Variable-size sliding window is useful when:
// 1. The window size isn't fixed.
// 2. You need to expand/shrink the window to meet constraints (e.g., sum, unique elements, etc.)
// 3. Common problems: longest/shortest subarray with some property (sum, all same, etc.)

// Fixed-size sliding window is only for cases where the window size is set in advance.

public class SlidingWindowVariableSize {
    
    // Q: Find the length of longest subarray, with the same value in each position: O(n)

    public static int longestSubarray(int[] nums) {
    // This method takes an array of integers and returns the length of the longest subarray of equal elements.
    
        int length = 0;
        // Initializes 'length' to 0. This variable will keep track of the maximum length found so far.
    
        int L = 0;
        // Initializes a left pointer 'L' to the start of the array. 
        // It marks the beginning of the current subarray of identical elements.
    
        for (int R = 0; R < nums.length; R++) {
            if (nums[L] != nums[R]) {
                // Checks if the current element at 'R' is different from the element at the start of the current subarray ('L').
    
                L = R;
                // If the elements are different, it means the streak of identical numbers is broken.
                // The left pointer 'L' is reset to the current position 'R' to start a new subarray.
            }
            length = Math.max(length, R - L + 1);
            // Calculates the length of the current subarray of identical elements (from L to R).
            // Updates the 'length' variable if the current subarray's length is greater than the max length found so far.
        }
        return length;
        // After the loop finishes, 'length' holds the maximum length found, which is returned.
    }


    // Q: Find the minimum length subarray, where the sum is greater than or equal to the target. Assume all values are positive
    
    public static int shortestSubarray(int[] nums, int target) {
    // This method finds the shortest subarray with a sum >= target.
    
        int L = 0, total = 0;
        // 'L' is the left pointer of our sliding window, initialized to the start.
        // 'total' will store the sum of the elements currently in the window.
    
        int length = Integer.MAX_VALUE;
        // 'length' will store our answer. 
        // By starting with the largest possible value, any valid subarray length you find will be smaller, update downwards.
    
        // Standard Convention
        // 1. Algorithms search for a minimum, set to MAX_VALUE
        // 2. Algorithms search for a maximum, set to MIN_VALUE
        
        for (int R = 0; R < nums.length; R++) {
            total += nums[R];
            // The value of the new element at 'R' is added to the window's total sum.
    
            while (total >= target) {
            // Once the window's sum is >= target, this inner loop starts.
            // It shrinks the window from the left to find the shortest possible valid length.
    
                length = Math.min(R - L + 1, length);
                // The current window's length (R - L + 1) is a valid candidate.
                // We update our answer 'length' if this new length is shorter than any we've previously found.
    
                total -= nums[L];
                // To shrink the window, we subtract the value of the leftmost element from the total.
    
                L++;
                // We then move the left pointer one step to the right, completing the "slide".
            }
        }
    
        if (length == Integer.MAX_VALUE) {
            // After checking all possibilities, if 'length' never changed from its initial value,
            // it means no subarray met the target sum.

            return 0;
            // In this case, we return 0 as per convention.

        } 
        return length;
        // Otherwise, we return the shortest length found.
    }
}

