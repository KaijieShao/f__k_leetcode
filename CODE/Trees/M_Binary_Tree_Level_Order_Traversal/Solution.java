package CODE.Trees.M_Binary_Tree_Level_Order_Traversal;

// It's asking you to perform a level order traversal (breadth-first traversal) on a binary tree.
// Return a list of lists: each sublist contains all node values at one tree level, in order from left to right.

import java.util.*;

class Solution {

    // Breadth First Search
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // Store a list for each tree level

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        // Puts the tree's root node into the queue, starting the traversal from the top level

        // One tree level
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            // Store the values of all nodes at the current tree level

            // One node at that level
            for (int i = q.size(); i > 0; i--) {
            // Process all nodes currently in the queue (one tree level)

                TreeNode node = q.poll();
                // Remove the front node from the queue to process it

                if (node != null) {
                    level.add(node.val);
                    q.add(node.left);
                    q.add(node.right);
                }
            }
            // After that 'for' loop, level contains all node values for the current tree level.

            if (level.size() > 0) {
                res.add(level);
            }
            // If level is not empty, you add it to res using res.add(level)
        }
        return res;
    }
}

// 19:34
