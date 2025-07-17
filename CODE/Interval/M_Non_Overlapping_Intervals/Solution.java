package CODE.Interval.M_Non_Overlapping_Intervals;

// Given a list of intervals, you need to remove the minimum number of intervals such that no intervals overlap.
// Two intervals overlap if they share any points except for exactly one endpoint 
// - e.g. [1,3] and [2,4] overlap, but [1,2] and [2,3] do not
// Your answer should be the minimum number of intervals to remove so the rest are all non-overlapping.

import java.util.Arrays;

public class Solution {

    // Greedy (Sort By Start)
    
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // Sort intervals by their start value 
        int res = 0;                                                   // Initialize counter for removals
        int prevEnd = intervals[0][1];                               
        // prevEnd keeps track of end of previous interval in the loop
        // - intervals[0]: the first interval (row) in the 2D array
        // -          [1]:  fetches the end value (second element) of that interval
        
        for (int i = 1; i < intervals.length; i++) {               
        // Iterate through intervals starting from the second one
            
            int start = intervals[i][0];
            int end = intervals[i][1];
            // Get start and end of current interval

            if (start >= prevEnd) {
                prevEnd = end;
            }
            // If current interval does NOT overlap with previous, update prevEnd

            else {
                res++;
                prevEnd = Math.min(end, prevEnd);
            }
            // If overlap, increment 'removal counter' and update prevEnd to the smaller end
            // Choosing the smaller end reduces the chance of overlapping with the next intervals
        }
        return res;
        // Return the total number of removals needed
    }
}

