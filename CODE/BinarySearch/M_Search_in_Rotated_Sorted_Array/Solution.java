package CODE.BinarySearch.M_Search_in_Rotated_Sorted_Array;

// Find the index of a given target in a rotated sorted array (sorted, then rotated).
// If target is not present, return -1.
// You must solve it in O(log n) time (using a modified binary search).
// Example:
// Input: nums = [3,4,5,6,1,2], target = 1
// Output: 4 (since nums[4] = 1)

class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while(l <= r) { // Edge case: l == r (only one element left)

            int mid = (l + r) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[l] <= nums[mid]) { // Left half is sorted in ascending order i.e., [1, 2, 3, 4, 5, 6, 7]
                if (target > nums[mid] || target < nums[l]) { // Checks if the target is NOT in the sorted left half
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else { // Left half contains the rotation (so left half has larger values than mid) i.e., [6, 7, 1, 2, 3, 4, 5]
                if (target < nums[mid] || target > nums [r]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

        }

        return -1;
    }
}
