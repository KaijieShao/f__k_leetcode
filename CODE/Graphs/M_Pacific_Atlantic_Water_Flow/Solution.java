package CODE.Graphs.M_Pacific_Atlantic_Water_Flow;

// You are given a grid of heights, represent an island bordered by two oceans: Pacific (top/left) and Atlantic (bottom/right)
// Water can flow from each cell to neighboring cells (up/down/left/right) only if the next cell is the same height or lower
// You must find all grid cells where water can flow to both the Pacific and Atlantic Oceans
// Return the coordinates of such cells

import java.util.*;

public class Solution {

    // Depth First Search

    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // Directions array for moving up, down, left, right

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int ROWS = heights.length, COLS = heights[0].length;
        boolean[][] pac = new boolean[ROWS][COLS];
        boolean[][] atl = new boolean[ROWS][COLS];
        // Two boolean matrices to track reachability for Pacific and Atlantic

        for (int c = 0; c < COLS; c++) {
            dfs(0, c, pac, heights);
            dfs(ROWS - 1, c, atl, heights);
        }
        // Starts DFS from every cell in the top row (dfs(0, c, pac, heights)) for Pacific.
        // Starts DFS from every cell in the bottom row (dfs(ROWS - 1, c, atl, heights)) for Atlantic.

        for (int r = 0; r < ROWS; r++) {
            dfs(r, 0, pac, heights);
            dfs(r, COLS - 1, atl, heights);
        }
        // Starts DFS from every cell in the leftmost column (dfs(r, 0, pac, heights)) for Pacific.
        // Starts DFS from every cell in the rightmost column (dfs(r, COLS - 1, atl, heights)) for Atlantic.

        List<List<Integer>> res = new ArrayList<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (pac[r][c] && atl[r][c]) {
                    res.add(Arrays.asList(r, c));
                }
            }
        }
        // Checks every cell and collects those that can reach both oceans

        return res;
    }

    private void dfs(int r, int c, boolean[][] ocean, int[][] heights) {
        ocean[r][c] = true;
        // Mark as visited/reachable

        for (int[] d : directions) {                         // Try all directions
            int nr = r + d[0], nc = c + d[1];
            // Calculates the next cell’s coordinates in the grid, based on the current cell (r, c) and the direction d.
            // nr = r + d[0]: next row index
            // nc = c + d[1]: next column index
            
            if (nr >= 0                                     // Ensures the next row index is not above the top boundary
                && nr < heights.length                      // Ensures the next row index is not below the bottom boundary
                && nc >= 0                                  // Ensures the next column index is not left of the left boundary
                && nc < heights[0].length                   // Ensures the next column index is not right of the right boundary
                && !ocean[nr][nc]                           // Ensures the cell at (nr, nc) has not been visited yet in the DFS
                && heights[nr][nc] >= heights[r][c]) {
                // Ensures water can flow from the current cell (r, c) to the next cell (nr, nc)
                // i.e., next cell is equal or higher in height.
                
                dfs(nr, nc, ocean, heights);
            }
        }
    }
}

