package CODE.Trees.M_Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal;

// Given two arrays, preorder and inorder, which represent the preorder and inorder traversals of a binary tree,
// Reconstruct the original binary tree from these traversals,
// Return the root node of the reconstructed tree.

public class Solution {
    int preIdx = 0;          // scans preorder[]: always points to the next root (invariants)
    int inIdx = 0;           // scans inorder[]: used to detect subtree boundaries (invariants)

    // Depth First Search (Optimal)

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, Integer.MAX_VALUE);
    }
    // MAX serves as that “∞” boundary—ensuring the initial call never early-terminates until all nodes are consumed.

    private TreeNode dfs(int[] preorder, int[] inorder, int limit) {
    // 'limit' marks the “stop value” in your inorder scan so you only build nodes that belong to the current subtree

        if (preIdx >= preorder.length) return null;
        // global base case: if no more nodes to consume in preorder → no subtree

        if (inorder[inIdx] == limit) {
            inIdx++;                    
            return null;                
        }
        // 1. That if runs before you create a TreeNode
        // 2. It checks “Did we hit the parent’s value in the inorder array?”
        //    Hitting limit means “we’ve traversed all of this subtree’s left side and now reached the parent node”
        // 3. inIdx++ skips over that parent marker so future calls start after it
        // 4. return null tells the caller “this branch has no node here”
        //    i.e. you’ve reached the boundary, so don’t build further on this side

        TreeNode root = new TreeNode(preorder[preIdx++]);
        // 1. Reads preorder[preIdx] to construct the new TreeNode
        // 2. Then increments preIdx by 1, so the next call uses the following element

        root.left = dfs(preorder, inorder, root.val);
        // For the left subtree, its inorder segment runs up to the root’s value, so you set the new sentinel to root.val

        root.right = dfs(preorder, inorder, limit);
        // For the right subtree, covers nodes after root.val but still before the parent’s boundary (old 'limit')

        return root;
    }
}



