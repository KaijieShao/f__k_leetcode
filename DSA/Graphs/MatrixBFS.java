package DSA.Graphs;

// Q: Find the length of the 'shortest' path (unweighted) from the top left to the bottom right

import java.util.Deque;
import java.util.ArrayDeque;
import java.lang.Math;


public class MatrixBFS {

    int[][] grid = {
        {0, 0, 0, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 1},
        {0, 1, 0, 0}
    };

    public int bfs(int[][] grid) {
        int ROWS = grid.length;
        int COLS = grid[0].length;
        int[][] visit = new int[4][4];
        Deque<int[]> queue = new ArrayDeque<>();
        
        queue.add(new int[2]);
        visit[0][0] = 1;

        int length = 0;

        while (!queue.isEmpty()) {
            int queueLength = queue.size();

            for (int i = 0; i < queueLength; i++) {
                int pair[] = queue.poll();
                int r = pair[0], c = pair[1];
                if (r == ROWS - 1 && c == COLS - 1) {
                    return length;
                }
                int[][] neighbors = {{r, c + 1}, {r, c - 1}, {r + 1, c}, {r - 1, c}};

                for (int j = 0; j < 4; j++) {
                    int newR = neighbors[j][0], newC = neighbors[j][1];
                    if (Math.min(newR, newC) < 0 || newR == ROWS || newC == COLS
                        || visit[newR][newC] == 1 || grid[newR][newC] == 1) {

                        continue;
                    }
                    queue.add(neighbors[j]);
                    visit[newR][newC] = 1;
                }
            }
            length++;
        }
        return length;
    }
}

