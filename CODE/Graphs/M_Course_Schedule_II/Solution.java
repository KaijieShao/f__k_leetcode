package CODE.Graphs.M_Course_Schedule_II;

// Given a list of course prerequisites, can you finish all courses?
// - Each prerequisite [a, b] means you must take course b before course a.
// - You have 'numCourses' total courses labeled 0 to numCourses -1.
// - Return true (No cycles) if you can finish all courses, false if not (There is at least one cycle):

import java.util.*;

// Cycle Detection (DFS)

public class Solution {
    private Map<Integer, List<Integer>> preMap = new HashMap<>();       // Map each course to its prerequisites
    private Set<Integer> visiting = new HashSet<>();                    // Store all courses along the current DFS path

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }
        // Initialize the map: each course points to an empty list

        for (int[] prereq : prerequisites) {
            preMap.get(prereq[0]).add(prereq[1]);                       // prereq[0] depends on prereq[1]
        }
        // Fill the map with actual prerequisite relationships

        for (int c = 0; c < numCourses; c++) {
            if (!dfs(c)) {                                              // DFS course 'c' to check if there's a cycle from 'c'
                return false;                                           // If a cycle is found, return false
            }
        }
        // Try to DFS each course to detect cycles

        return true;                                                    // If no cycles are found, return true
    }

    // Helper: check for cycles
    private boolean dfs(int crs) {             // 'crs' = the course number currently being visited in the DFS.
        if (visiting.contains(crs)) {
            return false;
        }
        // If we're already visiting this course, cycle detected.

        if (preMap.get(crs).isEmpty()) {
            return true;
        }
        // If this course has no prerequisites (already processed), it's safe.

        visiting.add(crs);
        // Mark this course as being visited along the current path.

        // DFS on each prerequisite.
        for (int pre : preMap.get(crs)) {     // use 'crs' as the key in 'preMap' to get the list of prerequisites for 'crs'
            if (!dfs(pre)) {                  // Calls DFS recursively for each prerequisite pre of the current course
                return false;
            }
        }
        // Checks if any prerequisite leads to a cycle—if so, the whole current path is invalid.

        visiting.remove(crs);                 // Backtrack: remove from current path after processing.
        preMap.put(crs, new ArrayList<>());   // Mark as fully processed by emptying its prerequisites.
        
        return true;
    }
}



