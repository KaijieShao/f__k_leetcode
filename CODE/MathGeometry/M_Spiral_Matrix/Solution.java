package CODE.MathGeometry.M_Spiral_Matrix;

// Take a 2D matrix and return all its elements in "spiral order."
// "Spiral order" means starting from the top-left, go right, then down, then left, then up, 
// and keep looping inward until all elements are visited.
// For [[1,2],[3,4]], spiral order is [1,2,4,3] (right → down → left → up as needed).

import java.util.*;

public class Solution {

    // Iteration (Optimal)

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Defines movement directions: right, down, left, up

        int[] steps = {matrix[0].length, matrix.length - 1};
        // Creates an array steps with two elements:
        // steps[0] = matrix[0].length: # of columns in the matrix (how far you can move right or left at the current layer)
        // steps[1] = matrix.length - 1: # of rows minus one (how far you can move down or up at the current layer)

        int r = 0, c = -1, d = 0;
        // int r = 0: Start at the first row (top of the matrix)
        // int c = -1: Start just before the first column, so the first move right (c += 1) lands on the first column (index 0)
        // int d = 0: Start with direction 0, which is "right" per the directions array.

        while (steps[d % 2] > 0) {
        // while (steps[d % 2] > 0) repeats the spiral traversal as long as there are still steps left in the current direction.
        // d % 2 gives 0 for horizontal moves (right/left), 1 for vertical moves (down/up).
        // steps[0] is how many columns you can move (right or left).
        // steps[1] is how many rows you can move (down or up).

            for (int i = 0; i < steps[d % 2]; i++) {
            // Loops exactly the number of steps you can currently move in the given direction.
            // - If moving horizontally (d % 2 == 0): loop for remaining columns.
            // - If moving vertically (d % 2 == 1): loop for remaining rows.
            // Each loop iteration:
            // - Moves one step in the current direction.
            // - Visits (and records) the next matrix element.

                r += directions[d][0];   // Move in row according to current direction.
                c += directions[d][1];   // Move in column according to current direction.
                res.add(matrix[r][c]);   // Add the current matrix element to result list
            }
            steps[d % 2]--;             
            // After completing movement in one direction, decrement step count for that direction (spiral shrinks inward)

            d = (d + 1) % 4;
            // Move to next direction; cycles through 0-3 (right, down, left, up)
        }
        return res;
    }
}



