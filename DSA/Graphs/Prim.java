package DSA.Graphs;

// Q: Given a weighted, undirected graph, find the subset of min weight edges 1) connect all vertices, 2) no cycles

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;


public class Prim {
    public static List<Integer[]> mst(int[][] edges, int n) {
        Map<Integer, ArrayList<Integer[]>> adj = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            adj.put(i, new ArrayList<Integer[]>());
        }
        for (int[] edge : edges) {
            int n1 = edge[0], n2 = edge[1], weight = edge[2];
            adj.get(n1).add(new Integer[] {n2, weight});
            adj.get(n2).add(new Integer[] {n1, weight});
        }

        Queue<int[]> minHeap = new PriorityQueue<>((n1, n2) -> (n1[0] - n2[0]));
        for (Integer[] neighbor : adj.get(1)) {
            int node = neighbor[0], weight = neighbor[1];
            minHeap.add(new int[]{weight, 1, node});
        }

        List<Integer[]> mst = new ArrayList<>();
        HashSet<Integer> visit = new HashSet<>();
        visit.add(1);

        while(visit.size() < n){
            int[] cur = minHeap.remove();
            int w1 = cur[0], n1 = cur[1], n2 = cur[2];
            if (visit.contains(n2)) {
                continue;
            }
            mst.add(new Integer[]{n1, n2});
            visit.add(n2);
            for (Integer[] pair: adj.get(n2)) {
                Integer neighbor = pair[0], weight = pair[1];
                if (!visit.contains(neighbor)) {
                    minHeap.add(new int[]{weight, n2, neighbor});
                }
            }
        }
        return mst;
    }
}
