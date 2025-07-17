package CODE.Trees.M_Validate_Binary_Search_Tree;

// Given the root of a binary tree, determine if the tree is a valid Binary Search Tree (BST)

public class Solution {

    // Depth First Search

    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    // Starts with the widest range to avoid edge cases (long for large integer ranges)

    public boolean valid(TreeNode node, long left, long right) {
        if (node == null) {
            return true;
        }
        // Base case: An empty subtree is always a valid BST
        
        if (!(left < node.val && node.val < right)) {
            return false;
        }
        // Constraint check: if the current node's value is NOT within the valid range, return false

        return valid(node.left, left, node.val) && valid(node.right, node.val, right);
        // Recursive: checks if both left and right subtrees are valid BSTs (order matters) within their updated value bounds
        // 1. For node.left, allowed range is from left up to node.val (left children in a BST must be less than the parent).
        // 2. For node.right, allowed range is from node.val up to right (right children must be greater than the parent).
    }
}


