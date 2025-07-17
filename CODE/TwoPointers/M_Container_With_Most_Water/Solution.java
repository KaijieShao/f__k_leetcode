package CODE.TwoPointers.M_Container_With_Most_Water;

// 1. Given an array of heights, each height represents a vertical bar on the x-axis.
// 2. You choose any two bars.
// 3. These two bars and the x-axis form a container.
// 4. Calculate the maximum area (amount of water) that can be stored between any two bars.
// 5. The area is determined by the shorter bar's height × the distance between the bars.

public class Solution {
    public int maxArea(int[] heights) {
        int l = 0;
        int r = heights.length - 1;
        int res = 0; 
        // Initialize the result variable to store the maximum area found

        while (l < r) {
            int area = Math.min(heights[l], heights[r]) * (r - l);
            // Picks the shorter height between the two indices l and r (since water can't go above the shorter bar)
            // (r - l) is the distance between those two indices

            res = Math.max(res, area);
            // You check every possible area (with current pointers), always keeping the max found.

            // Move the pointer at the shorter height inward
            if (heights[l] <= heights[r]) {
                l++; 
            } else {
                r--; 
            }
        }

        return res; 
        // Return the maximum area found
    }
}

// 08:16:53