package CODE.Graphs.M_Surrounded_Regions;

// Any group of 'O's that is NOT connected to the border should become 'X'.
// If an 'O' is on the border, or connected (up/down/left/right) to an 'O' on the border, it stays as 'O'.
// You must change the board directly, not return a new one.

public class Solution {                       

    // Depth First Search

    private int ROWS, COLS;                    

    public void solve(char[][] board) {       
        ROWS = board.length;                  
        COLS = board[0].length;               

        // Finds all '0' on left & right borders
        for (int r = 0; r < ROWS; ++r) {         // Loop through every row index from 0 to ROWS-1 (Here, ++r = r++)
            if (board[r][0] == 'O')              // If the leftmost cell in this row is 'O'
                capture(board, r, 0);          // Start DFS from the left border cell to mark all connected 'O's
            if (board[r][COLS - 1] == 'O')       // If the rightmost cell in this row is 'O'
                capture(board, r, COLS - 1);     // Start DFS from the right border cell to mark all connected 'O's
        }

        // Finds all '0' on top & bottom borders
        for (int c = 0; c < COLS; ++c) {       
            if (board[0][c] == 'O')
                capture(board, 0, c);        
            if (board[ROWS - 1][c] == 'O')
                capture(board, ROWS - 1, c);   
        }

        // Flips all remaining 'O's to 'X'
        for (int r = 0; r < ROWS; ++r) {          // Iterate over every row
            for (int c = 0; c < COLS; ++c) {      // Iterate over every column in the row
                if (board[r][c] == 'O')           // If cell is 'O', it's surrounded (not marked safe)
                    board[r][c] = 'X';            // Change it to 'X' because it's not connected to border
                else if (board[r][c] == 'T')      // If cell is 'T', it was marked safe (connected to border)
                    board[r][c] = 'O';            // Change it back to 'O'
            }
        }        
    }

    // DFS marks 'O' cells as 'T' to indicate they are safe (connected to the border)
    private void capture(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r == ROWS || c == COLS || board[r][c] != 'O')
            return;                               // Stop if out of bounds or not 'O'

        board[r][c] = 'T';                        // Mark current 'O' as safe by setting to 'T'

        // 'T' is a temporary marker to remember which 'O's are safe (connected to a border).
        // After all DFS calls, only 'O's NOT connected to borders will remain as 'O'—these will be flipped to 'X'.
        // Later, 'T's are changed back to 'O' to restore the original value for border-connected regions.

        capture(board, r + 1, c);                 // Recursively check cell below
        capture(board, r - 1, c);                 // Recursively check cell above
        capture(board, r, c + 1);                 // Recursively check cell to the right
        capture(board, r, c - 1);                 // Recursively check cell to the left
    }
}
