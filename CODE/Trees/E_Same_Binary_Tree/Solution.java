package CODE.Trees.E_Same_Binary_Tree;

// Compare two binary trees (roots given as p and q).
// Return true if:
// 1. Both trees have exactly the same structure (the same nodes in the same positions)
// 2. All corresponding nodes have the same value
// Return false if either the structure or any value is different.

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        // Both trees are empty here, so they are equal

        if (p != null && q != null && p.val == q.val) {
        // Checks if either node is null or if their values are different

            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            // Recursively check left children and right children for both trees

        } else {
        // Trees are not the same if only one is null or values don't match
        
            return false;
        }
    }
}

