package CODE.Backtracking.M_Combination_Sum;

// Given an array of distinct integers (nums) and a target integer (target), find all unique combinations that sum to target.
// You can use each number from nums as many times as you want in each combination.
// - Each combination must sum exactly to target.
// - Combinations are unique by frequency of numbers, not order.
// - Return a list of all such combinations (order does not matter).

import java.util.*;

public class Solution {

    // Backtracking (Optimal)
    
    List<List<Integer>> res;
    // Declares a result list 'res' to hold all valid combinations (each as a List<Integer>).

    public List<List<Integer>> combinationSum(int[] nums, int target) {
    // Main method: takes array of numbers and target sum, returns all combinations summing to target.

        res = new ArrayList<>();
        // Initialize 'res' as an empty list to store results

        Arrays.sort(nums);
        // Sorts 'nums' (not strictly necessary here, but can help with pruning branches early)
        
        dfs(0, new ArrayList<>(), 0, nums, target);
        // 0: Start index in nums (begin search from first element).
        // new ArrayList<>(): Empty list to build the current combination (no numbers chosen yet).
        // 0: Current sum is 0 (nothing added to the combination yet).
        // nums: The array of candidates (input array).
        // target: The sum we want to reach (input target).

        return res;
        // Returns all valid combinations found
    }

    private void dfs(int i, List<Integer> cur, int total, int[] nums, int target) {
    // Recursive method for backtracking.
    // i: current starting index in nums
    // cur: current combination being built
    // total: current sum of elements in 'cur'
    // nums: input numbers
    // target: required sum

        if (total == target) {                      // Base case: found a valid combination
            res.add(new ArrayList<>(cur));          // Add a copy of current combination to result list
            return;                                 // Stop further processing
        }
        
        for (int j = i; j < nums.length; j++) {
        // Iterate over available numbers from current index 'i' onwards.
        // Allows reusing same element (since 'j' starts at 'i' not 'i+1')

            if (total + nums[j] > target) {
                return;
            }
            // Prune: if adding current number exceeds target, stop searching this path (no need to continue, as numbers sorted)

            cur.add(nums[j]);
            // Choose: add nums[j] to current combination.

            dfs(j, cur, total + nums[j], nums, target);
            // Recursive call: Continues building combinations after choosing nums[j].
            // j: Passes current index, allowing reuse of the same number (since each element can be picked unlimited times).
            // cur: Current combination (now includes nums[j]).
            // total + nums[j]: Updates the current sum to include nums[j].
            // nums: The input array (unchanged).
            // target: The goal sum (unchanged).

            cur.remove(cur.size() - 1);
            // Backtrack: remove last added number to try other possibilities
        }
    }
}


