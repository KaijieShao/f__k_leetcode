package DSA.Trees;


import java.util.Map;
import java.util.HashMap;

public class UnionFind {
    
    // Scenario:
    // Imagine each person starts on their own island.
    // Union: When two people become friends, you build a bridge between their islands—now, CONNECT each other
    // Find: To check if two people are in the same group, just see if there’s a path (bridge) between islands
    // Union Find makes checking if two people share an island instant—even as the network grows and changes
    // Without it, you’d have to explore all paths between islands every time, which is slow

    Map<Integer, Integer> par;                          // Node : Parent
    Map<Integer, Integer> rank;                         // Root : Height

    public UnionFind(int n) {                           // 'n' is the number of nodes to manage here
        par = new HashMap<>();                       
        rank = new HashMap<>();                      

        for (int i = 1; i < n + 1; i++) {               // At least 1 node/person, index start from 1 (inclusive)
            par.put(i, i);                              // Start tobe its own parent first
            rank.put(i,0);                        // Start with 0 height
        }
    }

    public int find(int n) {                            // 1 -> 2 -> 3: find(1) or (2) should both return '3'
        int p = par.get(n);                             
        while (p != par.get(p)) {                       // Parent != Root
            par.put(p, par.get(par.get(p)));            // Update 'parent' to 'grandparent'
            p = par.get(p);                             // 'p' will become 'grandparent' of the original 'p'
        }
        return p;                                       // Return the root parent of 'n'
    }

    public boolean union(int n1, int n2) {              // Merge the sets containing 'n1' and 'n2'
        int p1 = this.find(n1), p2 = this.find(n2);     
        if (p1 == p2) {                                
            return false;                               // Already in same set
        }

        if (rank.get(p1) > rank.get(p2)) {              // p1's tree is deeper
            par.put(p2, p1);                            // Attach p2's tree under p1
        } else if (rank.get(p1) < rank.get(p2)) {      
            par.put(p1, p2);
        } else {                                        // Same rank
            par.put(p1, p2);                            // Attach p1 under p2
            rank.put(p2, rank.get(p2) + 1);             // Fetch p2's current rank, add 1
        }
        return true;                                    // Union successful
    }
}

