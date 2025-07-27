package DSA.Arrays;


public class TwoPointer {
    
    // Scenario:
    // Searching or comparing elements in a linear data structure (like an array or string) from two ends
    // L and R usually may start at different positions and move towards each other or in the same direction
    // This often reducing time complexity compared to nested loops approach

    public static boolean isPalindrome(String word) {
    // Q: Check if an array is palindrome

        int L = 0, R = word.length() - 1;
        while (L < R) {
            if (word.charAt(L) != word.charAt(R)) {
                return false;
            }       
            L++;
            R--;
        }
        return true;
    }


    public static int[] targetSum(int[] nums, int target) {
    // Q: Given a sorted input array, return the two indices of two elements which sums up to the target value

        int L = 0, R = nums.length - 1;
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                return new int[] {L, R};                 // Creates a new array object and fills it with L and R
            }    
        }
        return null;                                    // No solution found
    }  
}    



