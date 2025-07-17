package CODE.TwoPointers.H_Trapping_Rain_Water;

// 1. Given a series of bars (heights), how much water can be trapped above and between all the bars after rain?
// 2. At each index, water can be trapped if there are taller bars on both sides.
// 3. You must sum the trapped water above every bar, not just between two bars.

public class Solution {
    public int trap(int[] height) {

        if (height == null || height.length == 0) {
        // Checks two edge cases first. If either is true, the function returns 0 immediately (no data to process)
        // 1. height == null: The array reference itself is null (not initialized).
        // 2. height.length == 0: The array exists but is empty (no values).

            return 0;
        }

        int l = 0, r = height.length - 1;
        // l and r are pointers, leftMax and rightMax are actual values

        int leftMax = height[l], rightMax = height[r];
        // Track the maximum height seen so far from the left (leftMax) and from the right (rightMax)

        int res = 0; 

        // By updating leftMax and rightMax as you move the pointers, you always know the tallest wall so far on each side

        while (l < r) {
            if (leftMax < rightMax) {
                l++; 
                leftMax = Math.max(leftMax, height[l]);
                // update leftMax to always store the highest bar seen so far from the left up to index l

                res += leftMax - height[l]; 
                // Calculates the water trapped at position 'l' to get the height difference, and add it to the total result

            } else {
                r--; 
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r]; 
                // Order Matters!
                // If you swapped the order, you’d use an outdated max and get incorrect results
            }
        }

        return res; 
        // Return the total trapped water
    }
}

// 20:34