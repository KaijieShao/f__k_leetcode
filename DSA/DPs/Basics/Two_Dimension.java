package DSA.DPs.Basics;


public class Two_Dimension {
    
    // Scenario:
    // 1D: State depends on one variable (e.g., dp[i]) -> Uses a single array i.e., Fibonacci, 1D knapsack
    // 2D: State depends on two variables (e.g., dp[i][j]) -> Uses a 2D array i.e., LCS, unique paths in grid

    // Q: Count the # of unique paths from top-left to bottom-right of a grid (ONLY RIGHT/DOWN moves allowed)

    // Brute Force - Time: O(2 ^ (n + m)), Space: O(n + m)
    public static int bruteForce(int r, int c, int rows, int cols) {  // row index, col index, height, width
        if (r == rows || c == cols) {                                 // Fail: out of grid bounds
            return 0;
        }
        if (r == rows - 1 && c == cols - 1) {                         // Succeed: Reached bottom-right goal
            return 1;
        }
        return (bruteForce(r + 1, c, rows, cols) + bruteForce(r, c + 1, rows, cols));  // ONLY right/down moves
    }   


    // Top-Down - Time and Space: O(n * m)
    public static int memoization(int r, int c, int rows, int cols, int[][] cache) {
        if (r == rows || c == cols) {
            return 0;
        }    
        if (cache[r][c] > 0) {                                       // Cache hit: return cached result
            return cache[r][c];                                      // Space: O(rows x cols) avoid redundant work
        }    
        if (r == rows - 1 && c == cols - 1) {
            return 1;
        }
        cache[r][c] = (memoization(r + 1, c, rows, cols, cache) + memoization(r, c + 1, rows, cols, cache));
        return cache[r][c];
    }  


    // Bottom-Up - Time: O(n * m), Space: O(m), where m is num of cols
    public static int dp(int rows, int cols) {
        int[] prevRow = new int[cols];                               // 'prevRow' is BELOW 'curRow'

        for (int i = rows - 1; i >= 0; i--) {                        // Bottom-up: from bottom-right to top-left
            int[] curRow = new int[cols];                            
            curRow[cols - 1] = 1;                                    // Blocked! You can only move down now!
            for (int j = cols - 2; j >= 0; j--) {                    // Loop through second-to-last col <-
                curRow[j] = curRow[j + 1] + prevRow[j];              // = sum of paths from right and bottom
            }
            prevRow = curRow;                                        // Point to the same row
        } 
        return prevRow[0];                                           // Return the top-left corner [0][0]
    }
}


