package DSA.Graphs;


import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

public class Dijkstra {

    // Scenario:
    // Find shortest path from a given starting node to every other node in a graph with non-negative edge weights
    // Brute force: Tries all paths, which is exponential time—impractical for large graphs
    // Greedy: Picking the immediate smallest edge can miss the overall shortest path due to local minima
    // Dijkstra: Uses a priority queue to always expand the node with the current smallest known distance

    // Q: Starting from A, find the length of the shortest path to EVERY other node "Greedy BFS"

    public static Map<Integer, Integer> shortestPath(int[][] edges, int n, int src) {
    // 🔵 edges: edge[i] = [source, destination, weight], 🔵 n: # of nodes in graph, 🔵 src: starting node
    // 🟣 destination node: 🟣 shortest path weight (distance from 'src')

        Map<Integer, ArrayList<Integer[]>> adj = new HashMap<>(); // Adjacency list: node -> [neighbor, weight]
        for (int i = 1; i < n + 1; i++) {
            adj.put(i, new ArrayList<Integer[]>());               // Initialize adj list for each node (1 to n)
        }
        for (int[] edge : edges) {
            int s = edge[0], d = edge[1], w = edge[2];
            adj.get(s).add(new Integer[] {d, w});                 // Populate 'adj' using edge list
        }

        HashMap<Integer, Integer> shortest = new HashMap<>();     // key: each node, value: shortest path from src
        Queue<int[]> minHeap = new PriorityQueue<>((n1, n2) -> n1[0] - n2[0]);
        // int[]: [0] -> distance, [1] -> node | Custom comparator: sort by distance to return smallest out first

        minHeap.add(new int[] {0, src});                  // Distance from src to itself is '0'
        while (!minHeap.isEmpty()) {                      // Keep processing until all nodes are visited
            int[] cur = minHeap.remove();                 // Get node with smallest distance
            int w1 = cur[0], n1 = cur[1];
            if (shortest.containsKey(n1)) {
                continue;
            }
            shortest.put(n1, w1);                         // Set shortest distance for this node

            // 'adj' uses [neighbor, weight]:   helps you find where you can go next and the cost
            // 'minHeap' uses [distance, node]: helps you always process the next closest node.

            for (Integer[] pair : adj.get(n1)) {          // Check all neighbors of 'n1' | refer to line 24
                int n2 = pair[0], w2 = pair[1];
                if (!shortest.containsKey(n2)) {
                    minHeap.add(new int[] {w1 + w2, n2});
                    // 1. w1 = shortest distance to current node n1
                    // 2. w2 = edge weight from n1 to neighbor n2
                    // 3. w1 + w2 = new candidate distance to reach n2 through n1
                }
            }
        }

        return shortest;
    }
}


