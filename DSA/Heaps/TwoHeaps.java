package DSA.Heaps;


import java.util.PriorityQueue;
import java.util.Collections;

public class TwoHeaps {

    // Scenario:
    // Sorting after every number is slow
    // Two heaps (max-heap + min-heap) split data into lower and upper halves, making median calculation efficient
    
    PriorityQueue<Integer> small;                                  // Smaller half (max-heap)
    PriorityQueue<Integer> large;                                  // Larger  half (min-heap)

    public TwoHeaps() {
        small = new PriorityQueue<>(Collections.reverseOrder());   // Default to min-heap, reverse to max-heap
        large = new PriorityQueue<>();
    }

    // 1. ENSURE smallest value in 'minHeap' is larger than the largest value in 'maxHeap'
    // 2. ENSURE the heap sizes stay the same or WITHIN the difference of 1

    // O(logn) time
    public void insert(int num) {
        small.add(num);                                            // Push to 'maxHeap', then swap with 'minHeap'

        int val;
        if (!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
            val = small.poll();                                    // 'val' is the max element in 'small' heap
            large.add(val);
        }
        if (small.size() > large.size() + 1) {                     // 's' can have at most 1 more element than 'l'
            val = small.poll();
            large.add(val);
        }
        if (large.size() > small.size() + 1) {                     // It triggers when the difference is 2 or more
            val = large.poll();
            small.add(val);
        }
    }


    // O(1) time
    public double getMedian() {
        if (small.size() > large.size()) {                         // # of element in small is 'odd'
            return (double) (small.peek());
        } else if (large.size() > small.size()) {                  // # of element in large is 'odd'
            return (double) large.peek();
        }
        return (double) (small.peek() + large.peek()) / 2;         // 'even' -> take the average of the two heaps
    }     
}


