package CODE.Trees.M_Kth_Smallest_Element_In_a_Bst;

// Given the root of a BST and an integer k, return the k-th smallest value in the BST (using 1-based indexing).
// 1. If k = 1, return the smallest value in the BST.
// 2. If k = 2, return the second smallest value, etc.

public class Solution {

    // Main public method: returns the k-th smallest value in BST
    public int kthSmallest(TreeNode root, int k) {
        int[] tmp = new int[2];      // tmp[0]: counts nodes visited, tmp[1]: stores answer
        dfs(root, tmp, k);           // Start in-order DFS traversal (best way to fetch smallest value in BST)
        return tmp[1];               // Return stored k-th smallest value
    }

    // Helper DFS method for in-order traversal
    private void dfs(TreeNode node, int[] tmp, int k) {
        if (node == null) {          // Base case: empty subtree
            return;
        }

        dfs(node.left, tmp, k);      // LEFT: Traverse left subtree (smaller values first)

        tmp[0]++;                    // CURRENT: Visit current node: increment visited count

        if (tmp[0] == k) {           // If visited k nodes, found the k-th smallest
            tmp[1] = node.val;       // Store answer
            return;                  // Early exit (optional, for efficiency)
        }

        dfs(node.right, tmp, k);     // RIGHT: Traverse right subtree (larger values)
    }
}


