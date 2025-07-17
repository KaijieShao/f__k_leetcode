package CODE.Trees.E_Diameter_of_Binary_Tree;

// Find the diameter of a binary tree.
// 1. "Diameter" means the longest path (in number of edges) between any two nodes in the tree.
// 2. The path can go through or not through the root.

class Solution {

    // Depth First Search

    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1]; 
        // int:   primitive type (value, not reference)
        // int[]: reference type (object, can be modified via reference)
        // int[1] means res is an array with only one element, res[0]

        dfs(root, res);        
        // root is the current node (starting from the tree's top parent node)
        // res: an integer array of length 1, used to store the maximum diameter found so far

        return res[0];
        // res[0] holds the largest diameter found during the entire DFS traversal from dfs(root, res)
    }

    // Helper: returns the height (depth) of the current subtree
    private int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        // Recursively compute the depths of the left and right subtrees

        res[0] = Math.max(res[0], left + right);
        // left + right = total edges from leftmost leaf (via root) to rightmost leaf (via root) at this node

        return 1 + Math.max(left, right);
        // res[0] tracks the max diameter globally, but each call must return its own height for the recursion to work
    }
}

