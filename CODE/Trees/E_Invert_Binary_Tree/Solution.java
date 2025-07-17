package CODE.Trees.E_Invert_Binary_Tree;

// You are asked to invert (mirror) a binary tree.
// For every node, swap its left and right children.
// Example: For input [1,2,3,4,5,6,7], the output should be [1,3,2,7,6,5,4], which is the original tree mirrored.

public class Solution {

    // Depth First Search

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null; // Base case: prevents further recursive calls on empty nodes

        // Swap
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        // Recursively invert the left and right subtrees

        return root;
        // Root represents the whole inverted tree as a group of connected nodes
    }
}


