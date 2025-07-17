package CODE.Heap.M_Kth_Largest_Element_In_An_Array;

// Given an unsorted array 'nums' and an integer 'k'
// You find the element that would be in the kth position if the array were sorted in descending order (largest to smallest)
// Example: If nums = [3, 2, 1, 5, 6, 4] and k = 2, the sorted array is [6, 5, 4, 3, 2, 1], so the 2nd largest is 5.
// - It does not mean the kth unique/largest value—just the kth in sorted order, even if there are duplicates.
// - The "follow-up" asks if you can solve this without fully sorting the array (using, e.g., a heap or quickselect).

import java.util.PriorityQueue;

public class Solution {

    // Min-Heap

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
                // By always removing the smallest when the size exceeds k, you ensure only the largest k elements remain.
            }
        }
        return minHeap.peek();
        // The smallest in this heap (the root, peek()) is the kth largest element overall
        // i.e., K = 3 -> after the loop, the heap has the 3 largest numbers. The smallest among them is the 3rd largest overall
    }
}

