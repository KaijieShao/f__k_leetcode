package CODE.BinarySearch.M_Find_Minimum_in_Rotated_Sorted_Array;

// find the minimum element in a rotated sorted array (array was sorted, then rotated).
// You must do this in O(log n) time (using binary search), not by scanning the whole array.
// Example:
// Input: [3,4,5,6,1,2]
// Output: 1 (the smallest value)

public class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l) / 2;
            // (r - l) / 2 gives the offset from l to the middle.
            // Adding l shifts this offset to the actual middle index between l and r.

            if (nums[m] < nums[r]) {
                r = m;
                // The minimum is at m or to the left (right half is sorted "increasing" and m could be the min).
                // So, move r = m (i.e., 1, 2, 3, 4, 5).
                
            } else {
                l = m + 1;
                // The minimum is to the right (left half is sorted, but m is not the min).
                // So, move l = m + 1.
            }
        }
        return nums[l];
        // After the loop, l points to the index of the minimum element.
    }
}


