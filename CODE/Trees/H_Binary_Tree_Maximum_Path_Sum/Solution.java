package CODE.Trees.H_Binary_Tree_Maximum_Path_Sum;

// Given the root of a binary tree, find the largest possible sum you can get by following any connected path of nodes 
// You may start and end at any nodes, and each node can only appear once). Return that maximum path‐sum.

public class Solution {

    // Depth First Search (Optimal)

    public int maxPathSum(TreeNode root) {
        int[] res = new int[]{root.val};
        // res[0] holds the maximum sum found so far; initialize to root's value (object is passed by reference)

        dfs(root, res);
        // root: is the entry node for the current recursive call (maybe original root or any descendant treated as root)
        // res: a single-element array used as a mutable holder of global max path sum found anywhere in the tree so far

        return res[0];
    }

    // post‐order: leftMax -> rightMax -> root
    
    private int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        // Base case: It stops recursion when you hit a leaf’s child (no node)

        int leftMax = Math.max(dfs(root.left, res), 0);
        // root.left: tells dfs to recurse into the left subtree and compute its max-downward path sum
        // res: the single-element int[] holding the global maximum
        //      passing it lets every recursive call update res[0] when it finds a larger through-node sum
        
        int rightMax = Math.max(dfs(root.right, res), 0);
        // Same for right subtree

        res[0] = Math.max(res[0], root.val + leftMax + rightMax);
        // Considers the path that goes from the best left branch, through root, into the best right branch

        return root.val + Math.max(leftMax, rightMax);
        // res[0] holds the best “through-node” total: leftMax + root.val + rightMax
        // the return gives the best single-branch extension: root.val + max(leftMax, rightMax) (valid recursion)
    }
}


