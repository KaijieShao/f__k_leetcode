package CODE.Graphs.M_Islands_and_Treasure;

// For each land cell (INF) in the grid, you must fill it with the shortest distance to any treasure chest (0).
// - Water cells (-1) cannot be traversed or changed.
// - If a land cell cannot reach any treasure, keep it as INF.
// - You can only move up, down, left, or right.
// - Update the grid in-place with these shortest distances.

import java.util.*;

public class Solution {

    // Multi Source BFS

    public void islandsAndTreasure(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        // Create a queue for BFS; each element is an int array [row, col]

        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    q.add(new int[] { i, j });
                }
            }
        }
        // After this block, the queue 'q' contains the coordinates [i, j] for every cell in the grid that contains 0 

        if (q.size() == 0) return;
        // If there are no treasure cells, nothing to do

        int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        // Directions for BFS: up, down, left, right (row change, col change)

        while (!q.isEmpty()) {
            int[] node = q.poll();
            int row = node[0];
            int col = node[1];
            // Dequeue cell

            for (int[] dir : dirs) {
                int r = row + dir[0];   // Moves to the neighbor’s row (up or down depending on dir[0])
                int c = col + dir[1];   // Moves to the neighbor’s column (left or right depending on dir[1])
                // Together, (r, c) is the position of the adjacent cell in the grid relative to the current cell (row, col)

                if (r >= m || c >= n || r < 0 || c < 0 || grid[r][c] != Integer.MAX_VALUE) {
                    continue;
                }
                // The first four checks ensure (r, c) is within the grid boundaries.
                // The last check ensures the cell is an unvisited land cell.
                // - If it's not, skip it (it's either water, treasure, or already processed land).

                q.add(new int[] { r, c });
                // Valid land cell: enqueue it for next BFS layer

                grid[r][c] = grid[row][col] + 1;
                // Updates the land cell at position (r, c) with its shortest distance to the nearest treasure
                // - grid[row][col] is the distance of the current cell from the nearest treasure.
                // - Adding 1 means the neighbor cell (r, c) is one step farther away.
            }
        }
    }
}



