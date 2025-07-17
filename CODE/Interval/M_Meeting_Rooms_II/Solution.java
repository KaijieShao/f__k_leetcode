package CODE.Interval.M_Meeting_Rooms_II;

// Given a list of meeting intervals
// What is the minimum number of days needed to schedule all meetings so that no two meetings on the same day overlap in time?

import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    // Min Heap

    public class Interval {
        public int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public int minMeetingRooms(List<Interval> intervals) {
        intervals.sort((a, b) -> a.start - b.start);
        // Sort intervals by start time (ascending) to process in chronological order

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Min heap to keep track of the earliest 'end' time of ongoing meetings

        for (Interval interval : intervals) {
            if (!minHeap.isEmpty() && minHeap.peek() <= interval.start) {
                minHeap.poll();
                // If the earliest end time ≤ current start, remove that end time (reuse day)
            }
            minHeap.offer(interval.end);
            // Always add the current meeting's end time (allocate or reuse a day)
        }
        return minHeap.size();
        // Number of elements in heap = minimum rooms/days needed
    }
}

