package CODE.Backtracking.M_Subsets_II;

// Given an integer array nums (which may have duplicates), generate all possible subsets (the power set).
// - Each subset can be in any order.
// - Do not include duplicate subsets in your result.
// - Example:
//   For [1,2,2], valid unique subsets are:
//   [[], [1], [2], [1,2], [2,2], [1,2,2]]

import java.util.*;

public class Solution {

    // Iteration

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        // Sort array so duplicate numbers are adjacent

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>()); 
        // Add the empty subset ([])

        int prevIdx = 0;
        // Remembers the size of res before adding new subsets in the previous loop iteration.
        // Used to identify which subsets were just added, so duplicates can be handled.

        int idx = 0;
        // Decides where to start adding the current number:
        // - If current number is a duplicate, start from prevIdx (only extend new subsets added last round to avoid duplicates)
        // - If not a duplicate, start from 0 (extend all existing subsets).

        for (int i = 0; i < nums.length; i++) {
        // Outer: Iterates through each number in the sorted input array nums

            idx = (i >= 1 && nums[i] == nums[i - 1]) ? prevIdx : 0;
            // i >= 1 checks if the loop is not on the first element

            // Checks if the current number is a duplicate of the previous one.
            // - If yes: idx = prevIdx (only extend subsets created in the last step, to avoid duplicates).
            // - If no: idx = 0 (extend all existing subsets).

            prevIdx = res.size();
            // Save current size before adding new subsets

            for (int j = idx; j < prevIdx; j++) {
            // Inner: Extends the appropriate subsets with the current number

            // Loops from idx up to (but not including) prevIdx.
            // - If current number is not a duplicate: idx = 0, so it loops over all existing subsets.
            // - If current number is a duplicate: idx = prevIdx from last round, 
            //   so it only loops over subsets added in previous iteration.

                List<Integer> tmp = new ArrayList<>(res.get(j));
                // This creates a new list (tmp) that is a copy of the subset at index j in res.
                // The size of tmp will be the same as the size of the subset stored in res.get(j) (not just size j).
                // It copies all elements from res.get(j) into tmp.

                // Why create this 'tmp' list?
                // 1. To create a new subset based on an existing one.
                // 2. tmp is a copy, so changing it (by adding nums[i]) won't affect the original subset in res.
                // 3. Each new subset must be independent; otherwise, all references in res would change together.

                tmp.add(nums[i]);
                // Adds the current number to the copied subset (tmp), creating a new subset that includes nums[i].

                res.add(tmp);
                // Adds this new subset (tmp) to the results list (res), so it will be part of the final output
            }
        }

        return res;
    }
}



