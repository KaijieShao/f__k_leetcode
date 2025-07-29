package DSA.Graphs;


import java.util.ArrayList;
import java.util.HashMap;

public class AdjacencyList {

    // Scenario:
    // Simple lists/arrays can't capture complex, interconnected relationships
    // Graphs can manage networks of interconnected objects (nodes) and their relationships (edges)
    // Enable algorithms for search (BFS/DFS), shortest path (Dijkstra), spanning trees (Kruskal/Prim), etc.

    private HashMap<String, ArrayList<String>> adjList = new HashMap<>();

    public void printGraph() {
        System.out.println(adjList);
    }


    public boolean addVertex(String vertex) {
        if (adjList.get(vertex) == null) {                   // Adjacency list NOT already contain this vertex?
            adjList.put(vertex, new ArrayList<String>());    // If not exist, initializes a new empty list for it
            return true;
        }
        return false;
    }


    public boolean addEdge(String vertex1, String vertex2) {
        if (adjList.get(vertex1) != null && adjList.get(vertex2) != null) {
            adjList.get(vertex1).add(vertex2);
            adjList.get(vertex2).add(vertex1);
            return true;
        }
        return false;
    }


    public boolean removeEdge(String vertex1, String vertex2) {
        if (adjList.get(vertex1) != null && adjList.get(vertex2) != null) {
            adjList.get(vertex1).remove(vertex2);
            adjList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
    }


    public boolean removeVertex(String vertex) {
        if (adjList.get(vertex) == null) return false;

        for (String otherVertex : adjList.get(vertex)) {     // Loops through all neighbors 
            adjList.get(otherVertex).remove(vertex);         // REMOVES vertex from their lists
        }
        adjList.remove(vertex);

        return true;
    }
}


