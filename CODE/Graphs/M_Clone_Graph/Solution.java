package CODE.Graphs.M_Clone_Graph;

// Q: Create a deep copy (clone) of a connected undirected graph, given a starting node

import java.util.*;

public class Solution {
    public Node cloneGraph(Node node) {
        Map<Node, Node> oldToNew = new HashMap<>();
        // Key:   original node (from the input graph, to be copied)
        // Value: copied (cloned) node (new node you create)

        return dfs(node, oldToNew);
        // This allows the dfs function to:
        // 1. Know which node to start cloning from.
        // 2. Track already-cloned nodes using the shared oldToNew map as it recursively visits neighbors.
    }

    private Node dfs(Node node, Map<Node, Node> oldToNew) {
        if (node == null) {
            return null;
        }
        // Base case:
        // If node == null, it means there is no node to clone (invalid or end of recursion).
        // Returning null immediately prevents errors and handles the edge case of an empty graph.

        if (oldToNew.containsKey(node)) {
            return oldToNew.get(node);
        }
        // Base case:
        // This checks if the node has already been cloned (exists in oldToNew).
        // If yes, returns the existing clone immediately.
        // This prevents creating multiple copies of the same node and handles cycles/shared neighbors.

        Node copy = new Node(node.val);
        oldToNew.put(node, copy);
        // After handling the two base cases,
        // You create a new node (copy) with the same value as the original,
        // Then immediately add it to the HashMap (oldToNew.put(node, copy);)
        // - This prevents infinite loops/cycles and ensures each node is only cloned once.

        for (Node nei : node.neighbors) {
            copy.neighbors.add(dfs(nei, oldToNew));
        }
        // The for loop goes through all neighbors of the current node.
        // For each neighbor, it recursively calls dfs to clone that neighbor (or fetch from map if already cloned)
        // Adds each cloned neighbor to the neighbors list of the current copy.
        // This ensures all connected nodes are recursively cloned and linked correctly.

        return copy;
    }
}

