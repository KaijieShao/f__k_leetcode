package CODE.Interval.M_Merge_Intervals;

// Given: An array of intervals, each as [start, end].
// Task: Merge all overlapping intervals into one and return a list of non-overlapping intervals covering the same ranges.
// Overlapping means any shared points (e.g., [1,2] and [2,3] overlap because of the shared 2).
// Example:
// - Input: [[1,3],[1,5],[6,7]]
// - Output: [[1,5],[6,7]]
// - [1,3] and [1,5] merge to [1,5], [6,7] stays.

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    // Sorting (This is not merge sort syntax or logic)
    
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));  // Sort intervals by their start value (interval[0])
        // After sorting, intervals are ordered by start value, but their end values can still create gaps

        List<int[]> output = new ArrayList<>();                         // Create a dynamic list to store merged intervals
        output.add(intervals[0]);                                  
        // intervals[0] is added first as, after sorting, it's the earliest interval and cannot conflict with previous interval

        for (int[] interval : intervals) {                              // Iterate through each interval in the input
            int start = interval[0];                                    // Extract start and end for convenience
            int end = interval[1];
            int lastEnd = output.get(output.size() - 1)[1];
            // output.get ... - 1): Gets the last interval currently in 'output' (output.size()-1 is the index of last element)
            // [1]: Accesses the end value of that interval (since each interval is an int array, [0] is start, [1] is end)
            // 'int lastEnd = ...;' Stores that end value in lastEnd for comparison.

            if (start <= lastEnd) {
            // If the current interval overlaps with the last one in 'output'
            // i.e., Two intervals [a, b] and [c, d] overlap if c <= b

                output.get(output.size() - 1)[1] = Math.max(lastEnd, end);
                // Merge: update the last interval's end to the max of both ends

            } else {
                output.add(new int[]{start, end});
                // No overlap: add the current interval to output as is
            }
        }
        return output.toArray(new int[output.size()][]);
        // new int[output.size()][] creates a new 2D array that has the same number of rows as elements in output, 
        //                          but the columns are unspecified at this step.
        // toArray(...) copies all elements from the output list into this new 2D array.
        // The result is a standard int[][] array (not a list).
    }
}




