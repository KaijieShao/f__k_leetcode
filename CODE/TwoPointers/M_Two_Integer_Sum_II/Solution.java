package CODE.TwoPointers.M_Two_Integer_Sum_II;

// 1. You are given a sorted array of integers.
// 2. Find two distinct indices (index1, index2, 1-based, index1 < index2) such that sum of their values equals a given target.
// 3. Return [index1, index2].
// 4. You cannot use extra space (must use O(1) additional space).
// 5. There is exactly one solution guaranteed.

public class Solution {

    public int[] twoSum(int[] numbers, int target) {

        int l = 0, r = numbers.length - 1;

        while (l < r) { 
            int curSum = numbers[l] + numbers[r]; 
            // current sum

            if (curSum > target) {
                r--;
            } else if (curSum < target) {
                l++;
            } else {
            // Already check '>' and '<', then the remaining possibility must be '='

                return new int[] { l + 1, r + 1 };
                // Why l + 1 and r + 1?
                // 1. Java arrays are 0-indexed (first element is at index 0).
                // 2. The problem asks for 1-indexed answers (first element is at index 1)
            }
        }

        return new int[0]; 
        // Returns an empty array (with zero elements)
    }
}
