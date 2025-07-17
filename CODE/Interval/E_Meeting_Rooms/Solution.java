package CODE.Interval.E_Meeting_Rooms;

// Given a list of meeting time intervals, can one person attend all meetings without any overlaps/conflicts?
// - Return true if there is no overlap between any meetings (person can attend all).
// - Return false if any two meetings overlap (person cannot attend all).

import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Solution {

    // Sorting
    
    // Inner class to represent a meeting interval with start and end times
    public class Interval {                      
        public int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // Main method to check if a person can attend all meetings.
    public boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, Comparator.comparingInt(i -> i.start));
        // The intervals list is sorted by the start time of each meeting:
        // - Collections.sort: Sorts a list in-place.
        // - intervals: The list to sort (List of Interval objects).
        // - Comparator.comparingInt(...): Builds a comparator for integers.
        // - i -> i.start: Lambda function, takes an Interval i, returns its start field.
        // - Sorts intervals by their start value in ascending order.

        for (int i = 1; i < intervals.size(); i++) {
        // Iterate through the sorted intervals, starting from the second interval.

            Interval i1 = intervals.get(i - 1);
            Interval i2 = intervals.get(i);
            // Get the previous and current intervals.

            if (i1.end > i2.start) {
                return false;
            }
            // Check if the previous interval ends after the current one starts (overlap).
        }

        return true;
        // If no overlaps were found, return true.
    }
}



