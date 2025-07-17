package CODE.Heap.H_Find_Median_From_Data_Stream;

// Implement a class MedianFinder that can:
// Add numbers to a running list (using addNum(int num))
// Find the median of all added numbers at any time (using findMedian())

import java.util.*;

public class MedianFinder {

    private Queue<Integer> smallHeap; // Max-heap for smaller half (inverted min-heap)
    private Queue<Integer> largeHeap; // Min-heap for larger half

    public MedianFinder() {
        smallHeap = new PriorityQueue<>((a, b) -> b - a);
        // Max-heap: largest value at top, so we invert comparison (b - a)

        largeHeap = new PriorityQueue<>((a, b) -> a - b);
        // Min-heap: smallest value at top, normal comparison (a - b)
    }

    public void addNum(int num) {
        smallHeap.add(num);
        // Always add to max-heap first

        if ( smallHeap.size() - largeHeap.size() > 1 || !largeHeap.isEmpty() && smallHeap.peek() > largeHeap.peek() ) {
        // Balance: Max-heap can't have > 1 more element than min-heap
        //          And all elements in smallHeap must be <= any in largeHeap 

            largeHeap.add(smallHeap.poll());
            // Move top of smallHeap to largeHeap to balance or fix order
        }

        if (largeHeap.size() - smallHeap.size() > 1) {
            smallHeap.add(largeHeap.poll());
        }
        // If largeHeap gets bigger, move its top back to smallHeap
    }

    public double findMedian() {
        if (smallHeap.size() == largeHeap.size()) {
            return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
            // If sizes equal, average tops of both heaps

        } else if (smallHeap.size() > largeHeap.size()) {
            return (double) smallHeap.peek();
            // If max-heap bigger, its top is the median

        } else {
            return (double) largeHeap.peek();
            // Otherwise, min-heap is bigger (shouldn't happen with this balancing)
        }
    }
}

