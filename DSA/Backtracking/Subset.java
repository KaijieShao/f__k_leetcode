package DSA.Backtracking;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Subset {

    // Scenario:
    // Imagine you have a bag of items (like numbers, cards, or objects)
    // The Subset algorithm helps you list out every possible group you could pull from the bag (even 0 or all)
    // A 'subset' is any selection of elements from a 'set', possibly including none or all
    // i.e., For set {1, 2}, subsets are {}, {1}, {2}, {1, 2} (4 subsets in total)

    // Time: O(n * 2^n), Space: O(n)
    public static List<List<Integer>> subsetsWithoutDuplicates(int[] nums) {   
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> curSet = new ArrayList<>();                // Current subset being built
        helper(0, nums, curSet, subsets);                      // Starts from index 0
        return subsets;
    }

    public static void helper(int i, int[] nums, List<Integer> curSet, List<List<Integer>> subsets) {
        if (i >= nums.length) {                                 // DONE: Considered each element in the array
            subsets.add(new ArrayList<>(curSet));
            return;
        }

        curSet.add(nums[i]);                                    // 1st Call: Explore all subsets INCLUDE nums[i]
        helper(i + 1, nums, curSet, subsets);

        curSet.remove(curSet.size() - 1);                       // 2nd Call: Explore all subsets EXCLUDE nums[i]
        helper(i + 1, nums, curSet, subsets);
    }

    
    // Time: O(n * 2^n), Space: O(n)
    public static List<List<Integer>> subsetsWithDuplicates(int[] nums) {       
        Arrays.sort(nums);                                      // All duplicate are adjacent to one another
        List<List<Integer>> subsets = new ArrayList<>();        
        List<Integer> curSet = new ArrayList<>();
        helper2(0, nums, curSet, subsets);
        return subsets;
    }
   
    public static void helper2(int i, int[] nums, List<Integer> curSet, List<List<Integer>> subsets) {
        if (i >= nums.length) {
            subsets.add(new ArrayList<>(curSet));
            return;
        }

        curSet.add(nums[i]);
        helper2(i + 1, nums, curSet, subsets);

        curSet.remove(curSet.size() - 1);
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) { // 1) Within bounds, 2) Detect duplicates -> Skip
            i++;
        }
        helper2(i + 1, nums, curSet, subsets);
    }
}

