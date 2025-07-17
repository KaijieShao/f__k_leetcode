package CODE.Interval.H_Minimum_Interval_to_Include_Each_Query;

// For each value in queries, find the shortest interval in intervals that covers that query point.
// returns an array where each value is the length of the smallest interval containing the query, or -1 if none exists.

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;


public class Solution {

    public int[] minInterval(int[][] intervals, int[] queries) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // Sort intervals by their left endpoint (start of interval)

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        // The logic of what a[0] means depends on what you add later, but this syntax always compares the first element
        // In this 'minHeap', a[0] is the interval length (r - l + 1) AND a[1] is the right endpoint (r)

        Map<Integer, Integer> res = new HashMap<>();
        // Key: the query value (an integer from the queries array)
        // Value: the result for that query (length of the smallest covering interval, or -1 if none)

        int i = 0;
        // Initializes a pointer (or index) used to traverse the intervals array

        // For each sorted query, update heap
        for (int q : Arrays.stream(queries).sorted().toArray()) {
        // Arrays.stream(): turning an array into a Stream that lets you process elements in a functional style i.e., .sorted()
        // .toArray():      Converts the sorted stream back to an int[] array to iterate over

            // Adds intervals whose left endpoint is less than or equal to the current query
            while (i < intervals.length && intervals[i][0] <= q) {
                int l = intervals[i][0]; // Gives the current interval (array of two integers) first element  (left endpoint)
                int r = intervals[i][1]; // Gives the current interval (array of two integers) second element (right endpoint)
                minHeap.offer(new int[]{r - l + 1, r}); 
                // r - l + 1: Calculates the length of the current interval ([l, r]).
                // r: The right endpoint of the interval.
                // new int[]{r - l + 1, r}: Creates a new array holding [interval length, right endpoint].

                i++; 
            }

            // Remove expired intervals
            while (!minHeap.isEmpty() && minHeap.peek()[1] < q) {
                minHeap.poll();
            }
            // 1. Each element in the heap is new int[]{interval length, right endpoint}
            // 2. minHeap.peek()[1] accesses the right endpoint of the interval at the top of the heap.
            // 3. Remove intervals from the heap whose right endpoint < current query (no longer relevant)

            res.put(q, minHeap.isEmpty() ? -1 : minHeap.peek()[0]);
            // q: The current query (key)
            // minHeap.isEmpty() ? -1 : minHeap.peek()[0]: 
            // - If the heap is empty (no interval covers q), use -1.
            // - Otherwise, use minHeap.peek()[0] (the length of the smallest interval covering q, at the top of the heap)
            // - minHeap.peek()[0] is the interval length (right - left + 1)
        }
        int[] result = new int[queries.length];
        // The array's length is set to queries.length (the number of queries).
        // This array will store the final answers for each query, in the same order as the input.

        // Output results in original order
        for (int j = 0; j < queries.length; j++) {
            result[j] = res.get(queries[j]);
        }
        // For each query at index j, it gets the result from the res map using the original query value queries[j]
        // It stores that result in the result array at the same index j

        return result;
    }
}

