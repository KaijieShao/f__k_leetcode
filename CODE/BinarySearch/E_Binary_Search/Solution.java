package CODE.BinarySearch.E_Binary_Search;

// Search for a given target value in a sorted array of distinct integers (nums).
// If the target is found, return its index.
// If not found, return -1.
// Your solution must run in O(logn) time, so you should use binary search.

public class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int m = l + ((r - l) / 2);
            if (nums[m] > target) {
                r = m - 1;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }
}
