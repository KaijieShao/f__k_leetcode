package CODE.Backtracking.M_Subsets;

// Given an array 'nums' of unique integers, return all possible subsets (the power set) of 'nums'.
// Example: If nums = [1,2], output: [[], [1], [2], [1,2]]
// - Output must not contain duplicate subsets.
// - Subsets can be in any order.
// - Each subset can be of any length (including the empty set and the set itself).

import java.util.List;
import java.util.ArrayList;

public class Solution {
    
    // Backtracking

    public List<List<Integer>> subsets(int[] nums) {
    // Main method to generate all subsets (power set) of an integer array.

        List<List<Integer>> res = new ArrayList<>();
        // Create a list to store all subsets.

        List<Integer> subset = new ArrayList<>();
        // Create a temporary list to build subsets during recursion.

        dfs(nums, 0, subset, res);
        // Start backtracking DFS from the first index (0).

        return res;
    }

    private void dfs(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
    // Helper method for backtracking (Depth-First Search)
    // 'nums': input array, 'i': current index, 'subset': current subset, 'res': list to store all subsets

        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));
            // Add a copy of the current subset to the result.

            return;
        }
        // Base case: if index is out of bounds (all numbers considered)

        subset.add(nums[i]);
        // Include nums[i] in the current subset.

        dfs(nums, i + 1, subset, res);
        // Recursively call dfs to consider the next index with current element included.

        subset.remove(subset.size() - 1);
        // Backtrack: remove the last added element to explore subsets without it.
        
        dfs(nums, i + 1, subset, res);
        // Recursively call dfs to consider the next index without current element.
    }
}


