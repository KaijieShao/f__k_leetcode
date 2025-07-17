package CODE.Trees.E_Maximum_Depth_of_Binary_Tree;

// Calculate the maximum depth of a binary tree.
// Depth means how many nodes exist on the longest path from the root node to any leaf node.

public class Solution {

    // Recursive DFS

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        // Adds 1 for the current root node
    }
}
