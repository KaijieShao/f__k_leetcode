package CODE.LinkedList.M_Find_the_Duplicate_Number;

// Find the one integer that appears more than once in an array of length n+1, where each value is from 1 to n.

// Constraints:
// Every number from 1 to n appears exactly once, except one number, which appears at least twice.
// You need to return the duplicated number.

// Follow-up:
// Can you solve it without modifying the array and using O(1) extra space?

public class Solution {
    public int findDuplicate(int[] nums) {

        // Negative Marking

        for (int num : nums) {
            int idx = Math.abs(num) - 1;
            // Get a valid, non-negative index
            // Here, idx is not the loop index (i), but an index calculated from the value of num

            if (nums[idx] < 0) {
                // This checks if the value at the calculated index is already negative.
                // A negative value means this index has been visited before, which implies the current number is a duplicate.

                return Math.abs(num);
                // If a duplicate is found, its absolute value is returned.
            }
            nums[idx] *= -1;
            // If the value at the index is positive, we "mark" it as seen by flipping its sign to negative.
        }
        return -1; 
        // This line is a fallback and would only be reached if no duplicate were found
    }
}

// 20:39