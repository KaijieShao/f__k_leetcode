package DSA.Graphs;


import java.lang.Math;

public class MatrixDFS {

    // Scenario:
    // DFS and BFS can both traverse or search all cells in a matrix, find connected components (order differs)
    // DFS explores as deep as possible before backtracking (stack/recursion) -> simpler for backtrack tasks
    // BFS explores all neighbors at the current depth before going deeper (queue) -> preferred for shortest path

    // Q: Count the unique paths from the top left to the bottom right (cannot visit same cell more than once)

    int dfs(int[][] grid, int r, int c, int[][] visit) {   // Method returning an 'int'
        int ROWS = grid.length, COLS = grid[0].length;

        if (Math.min(r, c) < 0 || r == ROWS || c == COLS || visit[r][c] == 1 || grid[r][c] == 1 ) {
            return 0; 
        }
        // Fail
        // 1. The cell is out of bounds
        // 2. The cell has already been visited
        // 3. The cell is blocked (value is 1)

        if (r == ROWS - 1 && c == COLS - 1) {              // Valid path: reached the bottom right corner
            return 1; 
        }
        visit[r][c] = 1;                                   // Mark the current cell as 'visit'

        int count = 0;                                     // Initialize total path count from this cell
        count += dfs(grid, r + 1, c, visit);               // down
        count += dfs(grid, r - 1, c, visit);               // up
        count += dfs(grid, r, c + 1, visit);               // right
        count += dfs(grid, r, c - 1, visit);               // left

        visit[r][c] = 0;                                   // Backtrack: reset cell to allow other paths to use it
        return count; 
    }
}


