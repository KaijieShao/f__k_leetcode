package CODE.Graphs.M_Max_Area_of_Island;

// Find the largest area (number of cells) of any island in the grid.
// An island is a group of connected 1s (horizontally or vertically).
// For each island, count how many 1s are connected together.

public class Solution {
    private static final int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    // Directions for DFS: right, left, down, up
    
    public int maxAreaOfIsland(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int area = 0;
        
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 1) {
                // Found new island cell

                    area = Math.max(area, dfs(grid, r, c));
                    // Update max area if a larger island is found
                }
            }
        }
        return area;
    }
    
    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }
        
        grid[r][c] = 0;     // Mark visited (sink this piece of land)
        int res = 1;        // Start area count for this cell

        for (int[] dir : directions) {
            res += dfs(grid, r + dir[0], c + dir[1]);
        }
        // Explore all 4 directions
        
        return res;
    }
}

