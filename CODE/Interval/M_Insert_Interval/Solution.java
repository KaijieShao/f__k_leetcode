package CODE.Interval.M_Insert_Interval;

// Insert a new interval into a list of sorted, non-overlapping intervals.
// If the new interval overlaps with existing ones, merge them so the result remains sorted and non-overlapping.
// Return the updated list of intervals.
// Example:
// - If intervals = [[1,3], [6,9]] and newInterval = [2,5],
// - the output should be [[1,5], [6,9]] (since [1,3] and [2,5] overlap and merge to [1,5]).

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // Greedy

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        // res: stores final list of merged intervals

        for (int[] interval : intervals) {
        // Loop through each interval in input

            if (newInterval == null || interval[1] < newInterval[0]) {
                res.add(interval);
            }
            // Case 1: Checks if the end of the current 'interval' is before the start of the 'newInterval'
            //         If so, there is no overlap; this 'interval' is completely before newInterval.

            else if (interval[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(interval);
                newInterval = null;
            } 
            // Case 2: Current interval starts after newInterval ends, so no overlap, add newInterval before current interval
            //         - Once 'newInterval' is added (when no further merge or insert is needed), 
            //         - it’s set to null to signal it’s finished -> no more merging or adding required.

            else {
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }
            // Case 3: merges the current 'interval' and 'newInterval' if they overlap by updating to min start and max end
            //         it does NOT add the merged interval to the result list yet
        }
        if (newInterval != null) res.add(newInterval);
        // This line adds newInterval to the result list after the loop if it hasn’t already been added

        return res.toArray(new int[res.size()][]);
        // new int[res.size()][] creates a new 2D array with the same number of rows as res has elements
        // .toArray(...) fills that array with the contents of the list.
    }
}


