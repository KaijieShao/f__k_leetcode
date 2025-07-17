package DSA.Graphs;

import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;

import DSA.Trees.UnionFind;

// Kruskal's Algorithm is just another way to find the Minimum Spanning Tree of a connected Graph
// So, if Prim's Algorithm was too confusing for you, you might prefer Kruskal's Algorithm (much easier to understand)

public class Kruskal {
    public static List<Integer[]> mst(int[][] edges, int n) {
        Queue<int[]> minHeap = new PriorityQueue<>((n1, n2) -> (n1[0] - n2[0]));
        for (int[] edge : edges) {
            int n1 = edge[0], n2 = edge[1], weight = edge[2];
            minHeap.add(new int[]{weight, n1, n2});
        }

        UnionFind unionFind = new UnionFind(n);
        List<Integer[]> mst = new ArrayList<>();
        while(mst.size() < n - 1){
            int[] cur = minHeap.remove();
            int w1 = cur[0], n1 = cur[1], n2 = cur[2];
            if (unionFind.union(n1, n2) == false) {
                continue;
            }
            mst.add(new Integer[]{n1, n2});
        }
        return mst;
    }
}



