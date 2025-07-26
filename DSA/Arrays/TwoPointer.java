package DSA.Arrays;


public class TwoPointer {
    
    // Q: Check if an array is palindrome.

    public static boolean isPalindrome(String word) {
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


    // Q: Given a sorted input array, return the two indices of two elements which sums up to the target value. 
    //    Assume there's exactly one solution.

    public static int[] targetSum(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                return new int[] {L, R};
            }    
        }
        return null;
    }  
}    


