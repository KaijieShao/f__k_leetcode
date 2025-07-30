package DSA.Graphs;


import java.util.Set; 
import java.util.HashSet; 
import java.util.Map; 
import java.util.HashMap; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Collections; 

public class TopologicalSort { 

    // Scenario:
    // Given a set of tasks and dependencies, can you order all tasks so every task is performed only after its prereq?
    // Possible only if no cycles (DAG). Cycle = circular dependency, so no valid ordering
    // i.e., Task Scheduling: To schedule tasks with dependencies (e.g., build systems, course prerequisites)

    public static List<Integer> topologicalSort(int[][] edges, int n) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>(); // Adjacency list: node -> [neighbor, weight]
        for (int i = 1; i < n + 1; i++) {
            adj.put(i, new ArrayList<>()); 
        } 
        for (int[] edge : edges) { 
            int src = edge[0], dst = edge[1];                   // [src, dst], NO weight!
            adj.get(src).add(dst);                              // 'src' is 'dst's prereq
        } 

        List<Integer> topSort = new ArrayList<>(); 
        Set<Integer> visit = new HashSet<>(); 
        for (int i = 1; i < n + 1; i++) {                    
            dfs(i, adj, visit, topSort); 
        } 
        // running DFS on every node ensures all nodes are visited and included in topSort

        Collections.reverse(topSort);                           // Reverse: prerequisite before dependent
        return topSort; 
    } 
    

    public static void dfs(int src, Map<Integer, ArrayList<Integer>> adj, Set<Integer> visit, List<Integer> topSort) { 
        if (visit.contains(src)) {               // This base case will run every time 'dfs' is called on a visited node
            return; 
        } 

        visit.add(src);                          // Ensures each node is added to topSort only ONCE
        for (int neighbor : adj.get(src)) {      // Ensure all prereqs are visited first
            dfs(neighbor, adj, visit, topSort); 
        } 

        topSort.add(src);                       // Guarantees that 'src' is added AFTER all its prereqs already in the list
        return; 
    } 
}



