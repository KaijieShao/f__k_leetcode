package CODE.Trees.M_Count_Good_Nodes_in_Binary_Tree;

// Count how many nodes in the binary tree are "good" nodes.
// A node is "good" if there is no node with a value greater than it on the path from the root to itself (including itself)

class Solution {

    // Depth First Search

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int maxVal) {
        if (node == null) {
            return 0;
        }

        int res = (node.val >= maxVal) ? 1 : 0;
        // If this node value >= the max value seen so far, it's a good node (add 1)

        maxVal = Math.max(maxVal, node.val);
        // Update maxVal to be the greater of the current maxVal and this node's value
        
        res += dfs(node.left, maxVal);
        // Recursively count "good" nodes in the left subtree, passing the updated maxVal

        res += dfs(node.right, maxVal);

        return res;
    }
}

