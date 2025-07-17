package CODE.Heap.M_K_Closest_Points_to_Origin;

// You are given a list of points in 2D space and a number k.
// Find the k points that are closest to the origin (0,0) using Euclidean distance x^2 + y^2 (勾股定理)
// Return those k points (order does not matter).

import java.util.PriorityQueue;
import java.util.Comparator;

public class Solution {

    // Min-Heap (Java's PriorityQueue is a min-heap by default -> smallest element is ALWAYS at the root)

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparing(a -> a[0]));
        // Create min-heap [distance, x, y], sorted by their first element (distance) in increasing order

        for (int[] point : points) {
            int dist = point[0] * point[0] + point[1] * point[1];
            minHeap.offer(new int[]{dist, point[0], point[1]});  // Add [distance, x, y] to heap
        }

        int[][] result = new int[K][2];                          // result: K points, each point has 2 coordinates 'x' and 'y'
        for (int i = 0; i < K; ++i) {                            // ++i = i++
            int[] point = minHeap.poll();
            result[i] = new int[]{point[1], point[2]};           // Store x and y coordinates only
        }
        return result;
        // The result is sorted by distance (because you poll from the min-heap)
        // You only return the coordinates [x, y] for each of the K closest points—distance is not included in the final output
    }
}


