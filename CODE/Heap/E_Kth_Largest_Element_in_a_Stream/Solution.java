package CODE.Heap.E_Kth_Largest_Element_in_a_Stream;

// You need to design a class that tracks the kth largest element in a stream of integers.
// On initialization, you get k and an initial list of numbers.
// Every time you call add(val), you add val to your stream and must return the current kth largest number.
// Example:
// If k = 3, and your stream is [1, 2, 3, 3], the 3rd largest is 2.
// After adding 5, stream is [1, 2, 3, 3, 5], the 3rd largest is 3.

import java.util.PriorityQueue;

class KthLargest {
    
    // Min-Heap
    
    private int k;
    private PriorityQueue<Integer> minHeap;
    // stores at most k largest elements seen so far

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            // heapify happens automatically

            if (minHeap.size() > k) {
                minHeap.poll();
                // poll in a Java PriorityQueue always removes and returns the smallest element (the root of the min-heap)
            }
        }
    }

    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
        // returns the smallest value at the root of the min-heap (which is a PriorityQueue in Java)
    }
}

