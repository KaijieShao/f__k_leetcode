package CODE.Stack.H_Largest_Rectangle_In_Histogram;

// Challenge:
// The main challenge is efficiently finding the largest possible rectangle for every bar while considering that rectangles can overlap multiple bars, and doing so in linear time.

// Stack:
// 1. It lets you keep track of previous bars in increasing order.
// 2. As soon as you see a shorter bar, you know you can't extend a rectangle past this point.
// 3. Popping from the stack gives the exact index where the current bar can no longer extend

import java.util.Stack;

public class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();
        // Stack allow us know where the ractangle start and ends in increasing height order (end when lower bar is found)

        for (int i = 0; i <= n; i++) {
        // Outer: ensures every bar is visited
            
        // i = n is an extra cleanup step after all bars: 
        // 1. Some rectangles start at a bar and never meet a lower bar to the right—they run all the way to the end
        // 2. Their area isn't computed during the main loop because the "end" (a lower bar) never comes
        // 3. This extra step forces us to treat the end of the array as a virtual lower bar, so all rectangles are computed
        // 4. Without this, you could miss the largest rectangle if it ends at the last bar

            while (!stack.isEmpty() && (i == n || heights[stack.peek()] >= heights[i])) {
            // Inner: ensures every possible rectangle gets its area calculated

            // 1. !stack.isEmpty() is a basic safety check
            //    Prevents errors by making sure the stack is not empty before calling peek() or pop()

            // 2. (i == n || heights[stack.peek()] >= heights[i])
            //     i == n: All bars processed, triggers cleanup (calculates area for all remaining bars in stack)
            //     heights[stack.peek()] >= heights[i]: Current bar is lower or equal to bar at stack top
            //                                          time to pop stack's bar and calculate area before pushing the new bar

                int height = heights[stack.pop()];
                // Popped bar as its lowest (limiting) height and extends as wide as possible left/right
                
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                // 1. If stack is empty, there are no taller bars to the left, so width is just the current index
                // 2. Otherwise, width is the number of bars between the previous shorter bar and the current bar
                //               -1 is to exclude the previous shorter bar itself from the width
                //               since the bar at stack.peek() is not included in the rectangle (it's shorter)

                maxArea = Math.max(maxArea, height * width);
                // height * width computes the area of the rectangle for the popped bar
                // maxArea = Math.max(maxArea, height * width); updates maxArea to be the largest area found so far

            }
            stack.push(i);
            // push i onto the stack when current bar is taller than or equal to the bar at the top of the stack
        }
        return maxArea;
    }
}

