package CODE.Trees.E_Balanced_Binary_Tree;

// You are asked to check if a binary tree is "height-balanced".
// A height-balanced tree means: for every node, the height difference between its left and right subtrees is at most 1.

class Solution {

    // Depth First Search

    public boolean isBalanced(TreeNode root) {
        return dfs(root)[0] == 1;
        // Call DFS to check if the first value (balanced flag) is 1 (true)
    }

    private int[] dfs(TreeNode root) {

        // Base case: empty subtree is balanced and has height 0
        if (root == null) {
            return new int[]{1, 0}; // {balanced, height}
        }

        // Recursively check left and right subtrees
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        // A node is balanced if:
        // 1. Both left and right subtrees are balanced (left[0] == 1 && right[0] == 1)
        // 2. The heights of left and right subtrees differ by no more than 1
        boolean balanced = (left[0] == 1 && right[0] == 1) && 
                            (Math.abs(left[1] - right[1]) <= 1);

        int height = 1 + Math.max(left[1], right[1]);
        // Height of current node = 1 (for current node) + max height of subtrees

        return new int[]{balanced ? 1 : 0, height};
        // Return array: {isBalanced (1 or 0), height}
    }
}

