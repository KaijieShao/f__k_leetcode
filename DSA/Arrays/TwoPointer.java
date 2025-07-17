package DSA.Arrays;

public class TwoPointer {
    
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

