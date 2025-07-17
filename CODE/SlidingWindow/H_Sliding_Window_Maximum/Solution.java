package CODE.SlidingWindow.H_Sliding_Window_Maximum;

// You have an array of numbers, for example: [1, 2, 1, 0, 4, 2, 6].
// 1. You are given a number k, which is the size of the window (for example, k = 3).
// 2. Imagine a window that covers k numbers at a time in the array.
// 3. Move this window from the start to the end, one position at a time.
// 4. Each time, write down the biggest number inside the window.

import java.util.Deque; // Deque in Java is a 'double ended queue' to add & remove from both front & back
import java.util.LinkedList;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;               // Length of the input array
        int[] output = new int[n - k + 1]; // Hold the max value for each sliding window

        Deque<Integer> q = new LinkedList<>(); // LinkedList implements the Dequeue interface -> DLL
        int l = 0, r = 0;
        // l (left pointer) is used to update the position in the output array where the current window's max value is stored.
        // r (right pointer) iterates through each element in the original nums array, expanding the window rightward.

        // Outer: Slides the window by moving r
        while (r < n) {

            // Inner loop: Maintains deque so it only keeps useful indices for max calculation (not for moving the window)
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            q.addLast(r);
            // Remove from the back (less than the current value) -> Add current index (new max in current window)

            if (l > q.getFirst()) {
                q.removeFirst();
            }
            // If left boundry of current window is greater than the index at the front of deque, then remove it!

            // Now, let's update the max for each sliding window!
            if ((r + 1) >= k) {                                     // Checking the window size is at least k
                output[l] = nums[q.getFirst()];                     // Read the max in the current window and assigns it
                l++;
                // You record the current window's max, then advance to compute and store the next max
            }

            r++;
            // Increment the right pointer to move the window forward
        }

        return output;
    }
}


// 29:15

