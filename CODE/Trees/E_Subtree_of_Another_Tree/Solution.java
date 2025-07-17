package CODE.Trees.E_Subtree_of_Another_Tree;

// Given two binary trees (root and subRoot), check if subRoot exists as a subtree within root.
// "Subtree" means: starting from some node in root, the entire structure and all values match subRoot.
// Return true if such a subtree exists, false otherwise.

class Solution {
    
    // Main
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        // An empty tree is always a subtree of any tree (including empty)

        if (root == null) {
            return false;
        }
        // A non-empty tree cannot be a subtree of an empty tree

        if (sameTree(root, subRoot)) {
            return true;
        }
        // First check if the current subtree rooted at root is identical to subRoot

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        // Otherwise, recursively checks the left and right subtrees of root
    }

    // Helper
    public boolean sameTree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root != null && subRoot != null && root.val == subRoot.val) {
            return sameTree(root.left, subRoot.left) && sameTree(root.right, subRoot.right);
            // Both must be true (&&), so the entire structure and values of both trees must match
        }
        return false;
    }
}




