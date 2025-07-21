package CODE.Backtracking.H_N_Queens;

// Find all unique ways to place n queens on an n×n board so that no two attack each other (row, column, or diagonal)
// Return each solution as a list of n strings, where 'Q' means a queen and '.' means empty.

import java.util.*;

// Backtracking

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        // n x n chessboard

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        // Initialize every cell and set all to empty

        backtrack(0, board, res);
        // Start backtracking from row 0

        return res;
    }

    private void backtrack(int r, char[][] board, List<List<String>> res) {
        if (r == board.length) {
        // It means you've placed queens in all rows without conflict, so you have found a valid solution.

            List<String> copy = new ArrayList<>();
            for (char[] row : board) {
                copy.add(new String(row));
            }
            // That loop converts each char[] row of the board to a String and adds it to the 'copy' list.
            // So 'copy' becomes a list of strings, each string representing one row of the board in the current solution.

            res.add(copy);
            // Store the solution in the result list

            return;
            // Stop, go back up recursion
        }
        for (int c = 0; c < board.length; c++) {
        // Try all columns in current row

            if (isSafe(r, c, board)) {
            // Check if placing a queen is safe
            
                board[r][c] = 'Q';                // Place queen
                backtrack(r + 1, board, res);   
                // Moves to the next row after placing a queen in the current row r.
                // Each recursive call handles placing a queen in the next row.
                
                board[r][c] = '.';                // Undo move (backtrack)
            }
        }
    }

    private boolean isSafe(int r, int c, char[][] board) {
    // We only check rows above because queens are placed row by row, top to bottom. Lower rows aren’t filled yet.

        for (int i = r - 1; i >= 0; i--) {
            if (board[i][c] == 'Q') return false;
        }
        // Column check: checks all rows above in the same column (c)

        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // Upper-left diagonal: moves up one row and left one column each step, checking upper-left diagonal cells.
        // j >= 0: Ensures you don't go left of the board (column index can't be negative)
        // For upper-left diagonal, you move j-- (must stop at 0)

        for (int i = r - 1, j = c + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // Upper-right diagonal: moves up one row and right one column each step, checking upper-right diagonal cells.
        // j < board.length: Ensures you don't go right of the board (column index can't exceed last column).
        // For upper-right diagonal, you move j++ (must stop at board.length - 1)

        // Row is never checked because each row gets only one queen during backtracking

        return true;
    }
}

