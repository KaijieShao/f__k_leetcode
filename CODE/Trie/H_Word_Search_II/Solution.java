package CODE.Trie.H_Word_Search_II;

// Given a 2D grid of letters (board) and a list of words (words),
// Return all words from words that can be formed by a path in the grid
// 1. You can move horizontally or vertically to neighboring cells
// 2. You cannot use the same cell twice in the same word

import java.util.List;
import java.util.ArrayList;

public class Solution {

    // Backtracking
    
    public List<String> findWords(char[][] board, String[] words) {
        int ROWS = board.length, COLS = board[0].length;
        List<String> res = new ArrayList<>();

        for (String word : words) {
            boolean flag = false;
            for (int r = 0; r < ROWS && !flag; r++) {
                for (int c = 0; c < COLS; c++) {
                    if (board[r][c] != word.charAt(0)) continue;
                    if (backtrack(board, r, c, word, 0)) {
                        res.add(word);
                        flag = true;
                        break;
                    }
                }
            }
        }
        return res;
    }

    private boolean backtrack(char[][] board, int r, int c, String word, int i) {
        if (i == word.length()) return true;
        if (r < 0 || c < 0 || r >= board.length || 
            c >= board[0].length || board[r][c] != word.charAt(i))
            return false;

        board[r][c] = '*';
        boolean ret = backtrack(board, r + 1, c, word, i + 1) ||
                      backtrack(board, r - 1, c, word, i + 1) ||
                      backtrack(board, r, c + 1, word, i + 1) ||
                      backtrack(board, r, c - 1, word, i + 1);
        board[r][c] = word.charAt(i);
        return ret;
    }
}