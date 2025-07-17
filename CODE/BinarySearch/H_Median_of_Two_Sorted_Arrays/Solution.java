package CODE.BinarySearch.H_Median_of_Two_Sorted_Arrays;

import java.util.Arrays;
import java.util.List;

class Solution {

    private int[] nums1;
    private int[] nums2;
    private int n1, n2;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        this.n1 = nums1.length;
        this.n2 = nums2.length;
        this.nums1 = nums1;
        this.nums2 = nums2;

        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int l = 0;
        int r = n1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (check(m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        int m = l;
        double[] partitions = getPartitions(m);
        double left1 = partitions[0];
        double left2 = partitions[1];
        double right1 = partitions[2];
        double right2 = partitions[3];

        if ((n1 + n2) % 2 != 0) {
            return Math.max(left1, left2);
        } else {
            return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
        }
    }

    private boolean check(int m) {
        double[] partitions = getPartitions(m);
        double left1 = partitions[0];
        double left2 = partitions[1];
        double right1 = partitions[2];
        double right2 = partitions[3];

        return (left1 <= right2 && left2 <= right1) || left1 > right2;
    }

    private double[] getPartitions(int m) {
        int k = (n1 + n2 + 1) / 2 - m;

        double left1 = (m > 0) ? nums1[m - 1] : Double.NEGATIVE_INFINITY;
        double left2 = (k > 0) ? nums2[k - 1] : Double.NEGATIVE_INFINITY;
        double right1 = (m < n1) ? nums1[m] : Double.POSITIVE_INFINITY;
        double right2 = (k < n2) ? nums2[k] : Double.POSITIVE_INFINITY;
        
        return new double[]{left1, left2, right1, right2};
    }
}