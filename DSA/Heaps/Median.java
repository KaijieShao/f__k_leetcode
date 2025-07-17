package DSA.Heaps;

import java.util.PriorityQueue;
import java.util.Collections;

public class Median {

    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;

    public Median() {
        small = new PriorityQueue<>(Collections.reverseOrder());
        large = new PriorityQueue<>();
    }

    public void insert(int num) {
        small.add(num);

        int val;
        if (!small.isEmpty() && !large.isEmpty() && small.peek() > large.peek()) {
            val = small.poll();
            large.add(val);
        }
        if (small.size() > large.size() + 1) {
            val = small.poll();
            large.add(val);
        }

        if (large.size() > small.size() + 1) {
            val = large.poll();
            small.add(val);
        }
    }

    public double getMedian() {
        if (small.size() > large.size()) {
            return (double) (small.peek());
        } else if (large.size() > small.size()) {
            return (double) large.peek();
        }
        return (double) (small.peek() + large.peek()) / 2;
    }     
}
