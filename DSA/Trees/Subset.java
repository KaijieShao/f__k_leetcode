package DSA.Trees;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Subset {

    public static List<List<Integer>> subsetsWithoutDuplicates(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> curSet = new ArrayList<>();
        helper(0, nums, curSet, subsets);
        return subsets;
    }

    public static void helper(int i, int[] nums, List<Integer> curSet, List<List<Integer>> subsets) {
        if (i >= nums.length) {
            subsets.add(new ArrayList<>(curSet));
            return;
        }

        curSet.add(nums[i]);
        helper(i + 1, nums, curSet, subsets);

        curSet.remove(curSet.size() - 1);
        helper(i + 1, nums, curSet, subsets);
    }

    public static List<List<Integer>> subsetsWithDuplicates(int[] nums) {
        Arrays.sort(nums);
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

        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }
        helper2(i + 1, nums, curSet, subsets);
    }
}
