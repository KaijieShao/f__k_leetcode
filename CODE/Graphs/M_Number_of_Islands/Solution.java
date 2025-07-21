package CODE.Graphs.M_Number_of_Islands;

// Count the number of islands in a 2D grid (matrix) of '1' (land) and '0' (water).
// An island is a group of connected '1's (land), connected horizontally or vertically (not diagonally).

public class Solution {

    // Depth First Search

    private static final int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    // Directions for DFS: right, left, down, up
    
    public int numIslands(char[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int islands = 0;
        // Count of islands found
        
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == '1') {
                    dfs(grid, r, c);
                    // Mark the entire island as visited

                    islands++;
                }
            }
        }
        
        return islands;
    }
    
    private void dfs(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }
        // Check for out-of-bounds or water ('0')
        
        grid[r][c] = '0';
        // Mark the current land cell as visited (change '1' to '0')

        for (int[] dir : directions) {
            dfs(grid, r + dir[0], c + dir[1]);
        }
        // Calls the dfs function recursively for each adjacent cell.
        // dir[0] and dir[1] represent the row/column step for one of the four directions (right, left, down, up).
        // r + dir[0], c + dir[1] compute the coordinates of the neighbor cell.
        // Explores all connected land cells (all parts of the island) by moving in all four directions from the current cell.
    }
}

