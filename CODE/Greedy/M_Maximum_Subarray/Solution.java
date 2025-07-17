package CODE.Greedy.M_Maximum_Subarray;

// Find the contiguous subarray (one chunk, no skipping elements) in 'nums' whose elements sum to the largest value.

public class Solution {

    // Greedy (Kadane's Algorithm)

    public int maxSubArray(int[] nums) {
    // Find and return the maximum sum of any contiguous subarray in nums

        int maxSub = nums[0], curSum = 0;
        // maxSub: Stores the largest sum found so far across any subarray seen up to current point
        // curSum: Stores the sum of the current subarray being considered (may not be the largest)

        for (int num : nums) {
            if (curSum < 0) {
                curSum = 0;
            }
            // If curSum is negative, drop it (start a new subarray from current num)

            curSum += num;
            // Add current number to curSum (extend current subarray)

            maxSub = Math.max(maxSub, curSum);
            // Update maxSub if curSum is greater
        }
        return maxSub;
    }
}

