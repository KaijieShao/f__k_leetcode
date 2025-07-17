package CODE.Arrays.M_Valid_Sudoku;

import java.util.Set;       // Interface
import java.util.Map;
import java.util.HashSet;   // Concrete Implementation
import java.util.HashMap;

public class Solution {

    public boolean isValidSudoku(char[][] board) {        

        Map<Integer, Set<Character>> cols = new HashMap<>();
        // Key: it represents the column index (from 0 to 8)
        // Value: A set of characters representing the digits ('1' to '9') that have already appeared in that column

        Map<Integer, Set<Character>> rows = new HashMap<>();

        Map<String, Set<Character>> squares = new HashMap<>();
        // Key: the small 3×3 sub-box coordinate in the Sudoku board
        // Value: A set of characters representing the digits ('1' to '9') that have already appeared in that sub-box

        for (int r = 0; r < 9; r++) {            
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.')
                    continue;
                    // In a Sudoku board, '.' means empty spot. So we jump it!

                String squareKey = (r / 3) + "," + (c / 3);
                // After nested loop, now we iterates over every cell in the Sudoku board  
                // For each cell (r, c), we compute 'squareKey' to give each spot a sub-box 'location' within larger grid

                if (
                    rows
                        .computeIfAbsent(r, k -> new HashSet<>())
                        .contains(board[r][c])
                    || cols
                        .computeIfAbsent(c, k -> new HashSet<>())
                        .contains(board[r][c])
                    || squares
                        .computeIfAbsent(squareKey, k -> new HashSet<>())
                        .contains(board[r][c])
                ) {
                    return false;
                }

                // Checks if the current digit board[r][c] already exists in the corresponding row, column, or sub-box 

                // lambda functions: computeIfAbsent(key, k -> new HashSet<>())
                // key: the map key you’re looking up (e.g., r, c, or squareKey)
                // k -> new HashSet<>(): this is the lambda function
                //                       1. First try to find the HashSet for the given key (r, c, or squareKey)
                //                       2. If it doesn't exist, lambda is called to create an empty HashSet for k
                
                // After confirming no duplicates in the row, column, or sub-box:

                rows.get(r).add(board[r][c]);                
                cols.get(c).add(board[r][c]);
                squares.get(squareKey).add(board[r][c]);        

                // These lines add the current digit to the corresponding sets in each map  
            }
        }

        return true;
        // If the 'if block' detects a duplicate, return false; is executed immediately and the function exits.
        // If no duplicates found after checking all cells (all iterations), the code reaches return true; at the end.
    }
}
