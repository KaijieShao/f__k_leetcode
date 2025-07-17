package CODE.Greedy.M_Jump_Game;

// Given an array nums, each element tells you how far you can jump forward from that index (max steps).
// Starting at index 0, determine if it’s possible to reach the last index, moving according to the allowed jumps.
// Example:
// nums = [1,2,0,1,0]:
// - From index 0, can jump to index 1.
// - From index 1, can jump to index 2 or 3 (choose 3).
// - From index 3, can jump to index 4 (the end).
// So, output is true.
// nums = [1,2,1,0,1]:
// - Get stuck at index 3 (can’t move forward).
// - Get stuck at index 3 (can’t move forward).
// - So, output is false.

public class Solution {

    // Greedy

    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;
        // The index you must reach

        for (int i = nums.length - 2; i >= 0; i--) {
        // Loop backwards from the second last index to the start
        // You only care about positions that can jump to the goal; the last index itself never “jumps,” it’s the endpoint

            if (i + nums[i] >= goal) {
            // Each iteration asks: “Can I jump from i to goal?”
            // i:       is the loop variable (the current index you're visiting in the array)
            // nums[i]: the value stored at index i

                goal = i;
                // If yes, move the goal left to i (now you ask if earlier indices can reach this new, nearer goal).
            }
        }

        return goal == 0;
        // After the loop, if goal == 0, you’ve moved that “must-reach” point all the way to the start
        // If goal > 0, there’s no way to link index 0 to the last index.

        // It can never go below 0, so goal <= 0 is equivalent to goal == 0.
        // Checking == 0 is clearer: it means “the earliest index I must reach is 0,” i.e. start can reach end.
    }
}




