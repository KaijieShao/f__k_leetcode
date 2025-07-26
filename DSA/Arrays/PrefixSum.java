package DSA.Arrays;


// 1. A prefix array (or prefix sum array) always starts at the beginning (index 0) and is continuous.
//   [2, -1, 3, -3, 4] -> Prefix Sum: [2, 1, 4, 1, 5] or Prefix Product: [2, -2, -6, 18, 72]

// 2. A postfix (or suffix) sum is always calculated from some index in the array to the end.
//    1) If you start at the very last index, the postfix sum is just that last element.
//    2) If you start in the middle (i.e., index 2), postfix sum is the sum of all elements from index 2 to end.

import java.util.List;
import java.util.ArrayList;

public class PrefixSum {
    
    // Q: Given an array of values, design a data structure that can query the sum of a subarray of the values.

    List<Integer> prefix;              // Interface (General)

    public PrefixSum(int[] nums) {
        prefix = new ArrayList<>();    // Concrete implementation
        int total = 0;
        for (int n : nums) {
            total += n;
            prefix.add(total);         // Collect every running total as you loop through the array (build prefix)
        }
    }

    public int rangeSum(int left, int right) {              // Takes left and right indices as inputs
        int preRight = prefix.get(right);                   // Get the prefix sum at the right index
        int preLeft = left > 0 ? prefix.get(left - 1) : 0;  // Get the prefix sum before left, or 0 if left is 0
        return (preRight - preLeft);                        
    }
    // i.e., If we have [1, 2, 3, 4, 5] and we want to calculate the sum of [3, 4]
    //       We already have prefix sum of this array stored in `prefix`
    //       We can simply use prefix from [1 to 4] minus the prefix from [1 to 2] to get the sum of [3, 4]    
}


