package CODE.Graphs.M_Rotting_Fruit;

// Given a 2D grid where: 0 = empty cell; 1 = fresh fruit; 2 = rotten fruit
// Every minute, rotten fruit spreads to any directly adjacent (up, down, left, right) fresh fruit, turning them rotten.
// - You need to find:
// - The minimum number of minutes needed to rot all fresh fruit.
// - If it’s impossible (some fresh fruit can never be reached by rotten fruit), return -1.

import java.util.*;

public class Solution {

    // Breadth First Search

    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new ArrayDeque<>();
        // Create a queue to hold the positions of rotten oranges

        int fresh = 0;  
        int time = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    fresh++;
                }
                // If the cell is a fresh fruit, increment the 'fresh' count

                if (grid[r][c] == 2) {
                    q.offer(new int[]{r, c});
                }
                // If the cell is a 'rotten' fruit, add it to the 'q'
            }
        }
        // After this nested loop, 'q' will contain the positions ([row, col]) of all rotten fruits (cells '2') in the grid.

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // Directions: right, left, down, up (for BFS adjacency)

        while (fresh > 0 && !q.isEmpty()) {
            int length = q.size();
            // Number of rotten oranges to process at this minute

            for (int i = 0; i < length; i++) {      // For each rotten fruit in the queue
                int[] curr = q.poll();  
                int r = curr[0], c = curr[1];       // Row, col of rotten fruit

                for (int[] dir : directions) {      // Try all 4 directions
                    int row = r + dir[0];
                    int col = c + dir[1];
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1) {
                    // Checks boundaries: Ensures row and col are inside the grid (not out of bounds)
                    // && Checks if cell is fresh: Ensures the cell contains a fresh orange (grid[row][col] == 1)

                        grid[row][col] = 2;              // Rot this fresh fruit
                        q.offer(new int[]{row, col});    // Add its position to queue for next round
                        fresh--;                         // Decrement fresh count
                    }
                }
            }
            time++;                                     // Increment time after processing this level
        }
        return fresh == 0 ? time : -1;
        // If there are still fresh oranges, return -1 (impossible), else return elapsed time
    }
}



