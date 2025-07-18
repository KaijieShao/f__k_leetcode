package CODE.Backtracking.M_Combination_Sum_II;

// Find all unique combinations of numbers from the input array candidates that sum up exactly to the given target.
// - Each number can be used at most once in a combination.
// - No duplicate combinations in the result (order within combinations doesn't matter).
// - Return the list of these unique combinations.

import java.util.*;

public class Solution {

    // Backtracking (Optimal)

    private static List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res.clear();
        // Ensure that previous results do not persist between calls to combinationSum2

        Arrays.sort(candidates);
        dfs(0, new ArrayList<>(), 0, candidates, target);
        // Start DFS from index 0, empty path, current sum 0

        return res;
    }

    private static void dfs(int idx, List<Integer> path, int cur, int[] candidates, int target) {
        if (cur == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // If current sum equals target, add a copy of current path to result

        for (int i = idx; i < candidates.length; i++) {
        // Loop through candidates from current index

            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            // i > idx allows the first occurrence of a duplicate at this level (the current "branch" of recursion) to be used.
            // Only further duplicates (i > idx) are skipped, so you avoid repeating the same combination.
            // candidates[i] == candidates[i - 1] means "current candidate is a duplicate of the previous."

            if (cur + candidates[i] > target) {
                break;
            }
            // Prune search if sum exceeds target (since sorted)
            // If you didn't sort first, this pruning would be incorrect and you might miss valid combinations

            path.add(candidates[i]);
            // Choose current candidate

            dfs(i + 1, path, cur + candidates[i], candidates, target);
            // Recurse to next index, updated path and sum

            path.remove(path.size() - 1);
            // Backtrack: remove last added element before next iteration
        }
    }
}


