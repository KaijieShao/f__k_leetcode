package DSA.Trees;


import java.util.ArrayList;

public class TreeMaze {

    // Scenario:
    // Backtracking is designed to solve 'search' and 'decision' problems
    // How can I try all possible choices to solve a problem, and revert (backtrack) if a choice fails?
    // Just like explore a maze: you try one path -> hit a dead end -> backtrack -> try another path (until exit)

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }


    public boolean canReachLeaf(TreeNode root) {
    // Q: Determine if a path exists from the root of the tree to a leaf node. It may not contain any zeros.

        if (root == null || root.val == 0) {             // Base Case (failure): node is null, or 0 means dead end
            return false;
        }
        if (root.left == null && root.right == null) {   // Base Case (success): reached a 'leaf' (path complete)
            return true;
        }

        if (canReachLeaf(root.left)) {                   // Valid path in the left subtree
            return true;
        }
        if (canReachLeaf(root.right)) {                  // Valid path in the right subtree
            return true;
        }

        return false;                                    // No valid path found in either subtree
    }


    public boolean leafPath(TreeNode root, ArrayList<Integer> path) {
    // Q: A slight variation would be, instead of returning a boolean, to build the path if it exists

        if (root == null || root.val == 0) {
            return false;
        }

        path.add(root.val);                              // ONLY add to 'path' when we visit a valid node

        if (root.left == null && root.right == null) {   
            return true;
        }

        if (leafPath(root.left, path)) return true;      // All recursive calls share and update the same path list
        if (leafPath(root.right, path)) return true;     // If left subtree failed, try the right subtree

        path.remove(path.size() - 1);                    // Backtrack: if neither subtree worked, remove last node

        return false;                                    // Return false to parent, so it can try other children
    }
}

