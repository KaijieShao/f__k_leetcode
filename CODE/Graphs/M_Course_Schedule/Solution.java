package CODE.Graphs.M_Course_Schedule;

// Given course prerequisites, return a valid order to take all courses to finish them all.
// - Each prerequisite [a, b] means you must take course b before course a.
// - If multiple valid orders exist, any one is acceptable.
// - If it’s impossible (due to cycles), return an empty array.

import java.util.*;

// Topological Sort (Kahn's Algorithm)

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        // Create array to store number of prerequisites for each course

        List<List<Integer>> adj = new ArrayList<>();
        // Create adjacency list to store what courses depend on each course

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        // Initialize empty list for each course

        for (int[] pre : prerequisites) {
        // Loops over each prerequisite pair pre in prerequisites (where pre[1] is a prerequisite for pre[0])

            indegree[pre[1]]++;                 
            // Increments the indegree of course pre[1]
            // Means: another course (pre[0]) depends on pre[1]

            adj.get(pre[0]).add(pre[1]);        
            // Adds pre[1] as a prerequisite for pre[0] in the adjacency list
            // Means: to take pre[0], you must take pre[1] first
        }
        // Build the graph (fill adjacency list and indegree array)

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }
        // Finds all starting courses (those you can take immediately) and puts them in the queue for processing (无前置课)

        int finish = 0;                               // Counter for number of courses processed
        int[] output = new int[numCourses];           // Stores the topological order of courses.
        while (!q.isEmpty()) {
            int node = q.poll();                      // Remove a course from the queue
            output[numCourses - finish - 1] = node;   
            // numCourses = total number of courses.
            // finish = how many courses have been processed so far.
            // Why numCourses - finish - 1?
            // - The first processed node goes to the last position in output.
            // - The next goes to the second-last, and so on.
            // - This stores the result in reverse order (from end to start).

            finish++;                                 // Increment processed count.

            for (int nei : adj.get(node)) {           // For each neighbor nei (course depending on node):
                indegree[nei]--;                      // Decreases count of prerequisites for nei, since node is now completed.
                if (indegree[nei] == 0) {             // If all prerequisites for nei are satisfied,
                    q.add(nei);                       //  add nei to the queue so it can be processed next
                }
            }
        }
        
        if (finish != numCourses) {                 
        // If not all courses were processed, it means there's a cycle (impossible to finish all courses)

            return new int[0];
            // Returns an empty array
        }
        return output;
        // Otherwise, returns the computed valid course order
    }
}

