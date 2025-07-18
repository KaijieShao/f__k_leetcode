package CODE.Backtracking.M_Word_Search;

// You are given a 2D grid of characters (board) and a string (word).
// Determine if the word can be found in the grid by moving horizontally or vertically to adjacent letters, 
// using each cell at most once. Return true if the word exists in the grid, else return false.

public class Solution {

    // Backtracking (Optimal)

    private int ROWS, COLS;
    // Variables to store grid dimensions

    // Main function to check if word exists
    public boolean exist(char[][] board, String word) {
        ROWS = board.length;
        COLS = board[0].length;

        // Try every cell as a starting point
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
                // If DFS from this cell finds the word, return true
            }
        }
        return false;
    }

    // Depth-First Search helper function
    private boolean dfs(char[][] board, String word, int r, int c, int i) {
    // int i is the current index of the character in the target word you are matching

        if (i == word.length()) {
            return true;
        }
        // Base case: if all chars in word matched, return true

        if (r < 0 || c < 0 || r >= ROWS || c >= COLS || board[r][c] != word.charAt(i) || board[r][c] == '#') {
            return false;
        }
        // r < 0: row index is above the grid (out of bounds top)
        // c < 0: column index is left of the grid (out of bounds left)
        // r >= ROWS: row index is below the grid (out of bounds bottom)
        // c >= COLS: column index is right of the grid (out of bounds right)
        // board[r][c] != word.charAt(i): current cell does NOT match required character in word
        // board[r][c] == '#': current cell is already visited in this search path

        board[r][c] = '#';
        // Mark cell as visited by replacing with '#'

        boolean res = dfs(board, word, r + 1, c, i + 1) ||
                      dfs(board, word, r - 1, c, i + 1) ||
                      dfs(board, word, r, c + 1, i + 1) ||
                      dfs(board, word, r, c - 1, i + 1);
         // Explore all 4 directions (down, up, right, left)

        board[r][c] = word.charAt(i);
        // Restore original value for backtracking

        return res;
        // Return if path found
    }
}


