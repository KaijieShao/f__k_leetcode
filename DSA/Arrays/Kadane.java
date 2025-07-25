package DSA.Arrays;


public class Kadane { // Greedy

    // Q: Find a non-empty subarray(consecutive elements) with the largest sum.

    public static int bruteForce(int[] nums) {       // O(n^2)
        int maxSum = nums[0];                        // maxSum NEVER holds more than 1 number
        for (int i = 0; i < nums.length; i++) {      // Loop over all possible start indices
            int curSum = 0;                          // Reset 'curSum' for new subarray starting at 'i'
            for (int j = i; j < nums.length; j++) {  // Loop over all possible end indices >= 'i'
                curSum += nums[j];                   // Extend current subarray by nums [j]
                maxSum = Math.max(maxSum, curSum);   // Update maxSum if current subarray sum is larger
            }
        }
        return maxSum;
    }


    public static int kadanes(int[] nums) {          // O(n)
        int maxSum = nums[0];                        // Track the maximum sum found so far
        int curSum = 0;                              // Track the current sum
        for (int n : nums) {                         
            curSum = Math.max(curSum, 0);          // Reset 'curSum' to 0 if it's negative (only carry positive)
            curSum += n;                             // Add the current number to 'curSum' (extend subarray)
            maxSum = Math.max(maxSum, curSum);       // Update maxSum if curSum is now the largest seen so far
        }
        return maxSum;
    }


    public static int[] slidingWindow(int[] nums) {
        int maxSum = nums[0];                        
        int curSum = 0;                              
        int maxL = 0, maxR = 0;                      // Record the start/end indices of the best subarray found
        int L = 0;                                   // L marks the tentative start of the current subarray
        for (int R = 0; R < nums.length; R++) {      // R scan from left to right as end index of current subarray
            if (curSum < 0) {                        // 
                curSum = 0;                          // Reset running sum
                L = R;                               // New Subarray candidate starts at R
            }
            curSum += nums[R];                       // Add the current nums[R]
            if (curSum > maxSum) {
                maxSum = curSum;                     // Update best sum
                maxL = L;                            // Record its start index
                maxR = R;                            // Record its end index
            }
        }
        return new int[] { maxL, maxR };             // Return the bounds [start, end] of the max-sum subarray
    }
}





