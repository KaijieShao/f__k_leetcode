package CODE.Backtracking.M_Permutations;

// Takes an array of unique integers (nums) and returns all possible orderings (permutations) of those numbers as 2D lists.
// Each ordering must use every number exactly once. The order of the permutations in your result doesn't matter.

import java.util.*;

public class Solution {

    // Backtracking (Optimal)

    List<List<Integer>> res;
     // Declare a variable to store all permutations (result)

     // Main method to generate all permutations of the input array
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();     // Initialize the result list
        backtrack(nums, 0);      // Start backtracking from index 0
        return res;                  // Return the list of permutations
    }

    // Helper method to generate permutations using backtracking
    public void backtrack(int[] nums, int idx) {
        if (idx == nums.length) {                       // If all positions are fixed (idx reaches end), save the permutation
            List<Integer> perm = new ArrayList<>();     // Create a list for the current permutation
            for (int num : nums) perm.add(num);         // Add each number to the list
            res.add(perm);                              // Add the permutation to the result
            return;                                     // End current recursion
        }

        // Systematically generates every possible ordering (permutation) of the input array.
        // 1. At each recursion depth (idx), it tries every element as the next choice.
        // 2. Swapping places the chosen element at the "current" position.
        // 3. Recursing fixes this choice and permutes the rest.
        // 4. Swapping back restores the array so other choices can be tried.
        
        for (int i = idx; i < nums.length; i++) {  
        // Iterates from idx to end of nums, trying each element as the next candidate in the permutation. 

            swap(nums, idx, i);                        
            // Puts the i-th candidate at current position (idx) by swapping.

            backtrack(nums, idx + 1);                   
            // Recursively builds permutations for the next position.

            swap(nums, idx, i);                       
            // Restores the array to its original state to try other possibilities (backtracking).
        }
    }

    // Utility method to swap elements at indices i and j in the array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];                             // Store nums[i] in temp
        nums[i] = nums[j];                              // Assign nums[j] to nums[i]
        nums[j] = temp;                                  // Assign temp to nums[j]
    }
}

