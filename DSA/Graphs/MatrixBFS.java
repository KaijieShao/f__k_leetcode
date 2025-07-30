package DSA.Graphs;


import java.util.Deque;
import java.util.ArrayDeque;
import java.lang.Math;

public class MatrixBFS {

    // Scenario:
    // Find the shortest path in an unweighted grid (matrix) from 'start' to 'target', possibly avoiding obstacles
    // 1. BFS explores all cells at the same distance from the start before moving further
    // 2. Guarantees the first time you reach the target cell, you used the minimum number of steps.
    // 3. DFS cannot guarantee the shortest path in unweighted grids.

    // Q: Find the length of the shortest path from top left of the grid to the bottom right.

    int[][] grid = {
        {0, 0, 0, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 1},
        {0, 1, 0, 0}
    };

    public int bfs(int[][] grid) {
        int ROWS = grid.length;                         // .length is used for arrays. It's field not a method()
        int COLS = grid[0].length;                      // .size() is used for collections i.e., ArrayList
        int[][] visit = new int[4][4];
        Deque<int[]> queue = new ArrayDeque<>();         // Add / remove [row, col] pairs from both ends
        
        queue.add(new int[2]);                           // Create [0, 0] as the starting cell (top-left corner)
        visit[0][0] = 1;                                 // Mark starting cell as visited

        // BFS guarantees: The first time you reach the target, it’s via the shortest path,
        //                 so you don’t need to "try to find the best path" from each cell
        //                 BFS ensures the path is built level by level (shortest first)

        int length = 0;                                  // Initialize path length counter
        while (!queue.isEmpty()) {                       // 1️⃣ Controls the iteration through all BFS 'levels'

            int queueLength = queue.size();              // # of elemnts at the current level
            for (int i = 0; i < queueLength; i++) {      // 2️⃣ Iterate ALL nodes at the current level
                int pair[] = queue.poll();               // Dequeues the front cell from the queue
                int r = pair[0], c = pair[1];            // Grabs the row and column from the just-polled cell
                if (r == ROWS - 1 && c == COLS - 1) {     
                    return length;                       // Reached the GOAL
                }
                int[][] neighbors = {{r, c + 1}, {r, c - 1}, {r + 1, c}, {r - 1, c}}; 
                // order does NOT matter -> BFS explores all neighbors first BEFORE going deeper

                for (int j = 0; j < 4; j++) {                                    // 3️⃣ 4 neighbors of current cell
                    int newR = neighbors[j][0], newC = neighbors[j][1];          // Get 'row' & 'col' indexes
                    if (Math.min(newR, newC) < 0 || newR == ROWS || newC == COLS 
                        || visit[newR][newC] == 1 || grid[newR][newC] == 1) {

                        continue;               // Invalid cells are ignored, not retried or rebuilt
                    }
                    queue.add(neighbors[j]);    // Add the valid neighbor cell 
                    visit[newR][newC] = 1;      // Marks the j neighbor as visited
                }
            }
            length++;                           // Increment for each BFS level -> shortest path steps so far
        }
        return length;
    }
}


