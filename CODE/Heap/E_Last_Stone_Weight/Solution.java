package CODE.Heap.E_Last_Stone_Weight;

// You’re given an array stones[] of weights
// Repeatedly do: Remove the two largest weights x and y
// 1. If x == y, both are destroyed
// 2. Else insert a new stone of weight |x – y|
// Stop when ≤ 1 stone remains -> Return its weight (or 0 if none remain)

import java.util.PriorityQueue;

class Solution {

    // Heap

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int s : stones) {
            minHeap.offer(-s);
            // Use min-heap as max-heap by inserting negative values
        }

        while (minHeap.size() > 1) {
            int first = minHeap.poll();          // Largest (most negative)
            int second = minHeap.poll();
            if (second > first) {
                minHeap.offer(first - second);   // Only insert if they are not equal; use negative difference
            }
        }

        minHeap.offer(0);                      // Ensure heap has at least one element (0 if empty)
        return Math.abs(minHeap.peek());         // Return the absolute value (since we used negatives)
    }
}


