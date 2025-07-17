package CODE.TwoPointers.M_3Sum;

// 1. Find all unique triplets [a, b, c] in the array such that a + b + c = 0.
//    Each triplet must use distinct indices (i, j, k; i ≠ j ≠ k).
//    No duplicate triplets in the output (triplets with same numbers in any order count as duplicates).
// 2. Return all such triplets as a list of lists.
// 3. If no such triplets exist, return an empty list.

import java.util.Arrays;       // Utility class
import java.util.List;         // Interface
import java.util.ArrayList;    // Class (implements List interface)

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums); 
        // Calls Java's internal sorting algorithm to sort the array nums in ascending order

        List<List<Integer>> res = new ArrayList<>();
        // Creates a 2D list to store all resulting triplets (each triplet is a list of 3 integers)

        for (int i = 0; i < nums.length; i++) {
        // Outer: Iterate each number 

            if (nums[i] > 0) break; 
            // Base case: since array is sorted, if nums[i] > 0, then nums[i+1] > 0

            if (i > 0 && nums[i] == nums[i - 1]) continue; 
            // i > 0 checks we don't go out of bounds (no -1 index at start)
            // nums[i] == nums[i - 1] checks if the current number is the same as the previous neighbor
            // If true, continue; skips this iteration to avoid duplicate triplets starting with the same first number

            int l = i + 1, r = nums.length - 1;
            // After checking for duplicates at index i, you ensure:
            // 1. The first number (nums[i]) is unique for this loop iteration.
            // 2. The two-pointer scan (l and r) finds the other two numbers for the triplet.

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r]; 
                // Calculate the sum for the current triplet at each step of your two-pointer loop

                if (sum > 0) {
                    r--; 
                } else if (sum < 0) {
                    l++; 
                } else {                   
                    res.add(Arrays.asList(nums[i], nums[l], nums[r])); 
                    // Arrays.asList: creates a List containing the three numbers: nums[i], nums[l], and nums[r]
                    
                    l++; 
                    // Move left pointer to look for next unique triplet
                    r--; 
                    // Move right pointer as well for same reason

                    // Since the array is sorted in ascending order, duplicates are always neighbors

                    while (l < r && nums[l] == nums[l - 1]) l++;
                    // skips over duplicate values for the left pointer (l)

                    while (l < r && nums[r] == nums[r + 1]) r--;
                    // skips over duplicate values for the right pointer (r)
                }
            }
        }

        return res; 
        // Return the list of all unique triplets
    }
}
