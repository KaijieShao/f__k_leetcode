package CODE.Arrays.E_Two_Sum;

import java.util.HashMap;

// Find two distinct indices i and j such that nums[i] + nums[j] == target

public class Solution {

    // Hash Map (One Pass)

    public int[] twoSum(int[] nums, int target) { 

        HashMap<Integer, Integer> prevMap = new HashMap<>();
        // Key: numbers
        // Value: indices

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];            
            int diff = target - num;            

            if (prevMap.containsKey(diff)) {        
                return new int[] { prevMap.get(diff), i };
                // prevMap.get(diff) -> fetches the index of 'diff' from the map
                // i -> is the current index
            }

            prevMap.put(num, i);
        }        

        return new int[] {};
        // Returns an empty array, meaning no solution (no indices found)
    }
}

