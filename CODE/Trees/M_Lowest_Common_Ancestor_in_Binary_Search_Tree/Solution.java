package CODE.Trees.M_Lowest_Common_Ancestor_in_Binary_Search_Tree;

// Given a binary search tree (BST) and two nodes (p and q), find the lowest common ancestor (LCA) of these two nodes.
// LCA is the deepest node in the tree that has both p and q as descendants (a node can be its own descendant).
// You need to write a function that, given the BST root and two nodes, returns the LCA node.

public class Solution {

    // Recursion

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        // Base case (stop recursion when...) & Edge case (guard for invalid input)

        if (Math.max(p.val, q.val) < root.val) {
            return lowestCommonAncestor(root.left, p, q);
            // If both p and q are less than root, LCA is in the left subtree

        } else if (Math.min(p.val, q.val) > root.val) {
            return lowestCommonAncestor(root.right, p, q);
            // If both p and q are greater than root, LCA is in the right subtree

        } else {
            return root;
            // Otherwise (they diverge at root or one equals root), root is their lowest common ancestor
        }
    }
}