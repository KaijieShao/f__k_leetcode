package DSA.Graphs;


import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

import DSA.Trees.UnionFind;

public class Kruskal {

    // Scenario:
    // Kruskal's and Prim's algorithms both solve the MST problem.
    // You can choose either based on:
    // - Which is easier for you to understand/implement
    // - The structure of your graph (Kruskal: good for sparse graphs, Prim: better for dense graphs)

    public static List<Integer[]> mst(int[][] edges, int n) {
        Queue<int[]> minHeap = new PriorityQueue<>((n1, n2) -> (n1[0] - n2[0]));
        for (int[] edge : edges) {                           
            int n1 = edge[0], n2 = edge[1], weight = edge[2];
            minHeap.add(new int[]{weight, n1, n2});
        }

        UnionFind unionFind = new UnionFind(n);
        // UnionFind: Efficiently merge two sets together or Quickly determine if two elements are in the same set
        
        List<Integer[]> mst = new ArrayList<>();
        while(mst.size() < n - 1){                       // tree with n nodes ALWAYS has n - 1 edges
            int[] cur = minHeap.remove();
            int w1 = cur[0], n1 = cur[1], n2 = cur[2];
            if (unionFind.union(n1, n2) == false) {      // 'n1' and 'n2' are already connected, skip this edge
                continue;
            }
            mst.add(new Integer[]{n1, n2});              // Add edge to mst
        }
        return mst;
    }
}



