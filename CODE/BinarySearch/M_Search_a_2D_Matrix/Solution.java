package CODE.BinarySearch.M_Search_a_2D_Matrix;

// You are given a 2D matrix (matrix) and an integer (target).
// Each row sorted in increasing order, and the first number of each row is greater than the last number of the previous row.
// You need to return true if the target exists in the matrix, or false otherwise.
// Your solution must run in O(log(m * n)) time (binary search over the whole matrix).

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int ROWS = matrix.length, COLS = matrix[0].length;

        int l = 0, r = ROWS * COLS - 1; // Set 'r' to the last index if you flatten 2D matrix into 1D array

        while (l <= r) {
            int m = l + (r - l) / 2;
            int row = m / COLS, col = m % COLS;
            // Formula: converts a 1D index 'm' to 2D matrix coordinates

            if (target > matrix[row][col]) {
                l = m + 1;
            } else if (target < matrix[row][col]) {
                r = m - 1;
            } else {
                return true; // found
            }
        }
        return false; // not found
    }
}

