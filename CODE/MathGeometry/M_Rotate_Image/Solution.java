package CODE.MathGeometry.M_Rotate_Image;

// You are given a square matrix (n x n).
// You must rotate it 90 degrees clockwise, in-place (modify the original; don't use extra space for another matrix).

public class Solution {

    // Reverse And Transpose

    public void rotate(int[][] matrix) {
        reverse(matrix);
        // 1. Flips the matrix upside down (vertical reversak)
        // 2. Swaps elements across the diagonal, which completes the 90° clockwise rotation

        for (int i = 0; i < matrix.length; i++) {           // Loop through each row
            for (int j = i; j < matrix[i].length; j++) {    // Loops through each column in the current row
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
            // matrix[i][j] and matrix[j][i] are on opposite sides of the diagonal; swapping them transposes the matrix
        }
    }

    private void reverse(int[][] matrix) {
    // Helper method to reverse the matrix vertically (flip upside down)

        int n = matrix.length;                       // n represents both row and col count as matrix is guaranteed to be square
        for (int i = 0; i < n / 2; i++) {            // Only need to swap the top half with the bottom half to flip the matrix
            int[] temp = matrix[i];                  // Stores the current row at index i
            matrix[i] = matrix[n - 1 - i];           // Swap the current row with the corresponding row from the bottom half
            matrix[n - 1 - i] = temp;                // Copies the original current row (stored in temp) into the bottom half
        }
    }
}

