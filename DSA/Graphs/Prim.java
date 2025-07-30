package DSA.Graphs;


import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

public class Prim {

    // Scenario:
    // Given a weighted, undirected graph, find a subset of edges that:
    // - Connects all vertices (spanning)
    // - Has the minimum total edge weight (minimum)
    // - Forms no cycles (tree)
    // This is called the 'Minimum Spanning Tree (MST)' problem

    // Prim's Algorithm is a classic example of a 'Greedy' Algorithm
    // Greedy: Chooses the optimal choice at each step, meaning it selects the best option available at each step

    public static List<Integer[]> mst(int[][] edges, int n) {       // Returns the edges of the MST
        Map<Integer, ArrayList<Integer[]>> adj = new HashMap<>();   // Adjacency list: node -> [neighbor, weight]
        for (int i = 1; i < n + 1; i++) {
            adj.put(i, new ArrayList<Integer[]>());
        }
        for (int[] edge : edges) {
            int n1 = edge[0], n2 = edge[1], weight = edge[2];
            adj.get(n1).add(new Integer[] {n2, weight});
            adj.get(n2).add(new Integer[] {n1, weight});            // In undirected graph, edge connects both ways
        }

        Queue<int[]> minHeap = new PriorityQueue<>((n1, n2) -> (n1[0] - n2[0]));
        for (Integer[] neighbor : adj.get(1)) {                 // Loop through all neighbors of node 1
            int node = neighbor[0], weight = neighbor[1];           // [neighbor, weight]
            minHeap.add(new int[]{weight, 1, node});                // [edge weight, from node 1, to neighbor node]
        }

        List<Integer[]> mst = new ArrayList<>();                    // [[from, to], [from, to], ...]
        HashSet<Integer> visit = new HashSet<>();                   // No Cycles

        visit.add(1);                                             // Starting point
        while(visit.size() < n){                                    // Repeat until all nodes are included in mst
            int[] cur = minHeap.remove();                           
            int w1 = cur[0], n1 = cur[1], n2 = cur[2];              // [weight, from, to]
            if (visit.contains(n2)) {                               
                continue;
            }
            mst.add(new Integer[]{n1, n2});                         
            visit.add(n2);                                          // Destination node is now visited
            for (Integer[] pair: adj.get(n2)) {                     
                Integer neighbor = pair[0], weight = pair[1];       
                if (!visit.contains(neighbor)) {                    // If neighbor not visited, add to minHeap
                    minHeap.add(new int[]{weight, n2, neighbor});
                }
            }
        }
        return mst;
    }
}

