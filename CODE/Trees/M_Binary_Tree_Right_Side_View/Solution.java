package CODE.Trees.M_Binary_Tree_Right_Side_View;

// Return only the values of the nodes you would see if you looked at the tree from the right side, from top to bottom

import java.util.*;

class Solution {

    // Breadth First Search
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // Creates a list to store the right side view node values (top to bottom)

        Queue<TreeNode> q = new LinkedList<>();
        // Creates a queue for level order (BFS) traversal

        q.offer(root);
        // Adds the root node to the queue to start BFS traversal.

        while (!q.isEmpty()) {
            TreeNode rightSide = null;
            // Declares a variable to store the rightmost node at the current level

            int qLen = q.size();

            for (int i = 0; i < qLen; i++) {
            // Inner loop process all nodes at current level. After the loop, rightSide holds the rightmost node at this level

                TreeNode node = q.poll();
                // Removes and returns the first node in the queue, which is the leftmost node of the current level

                if (node != null) {
                    rightSide = node;
                    // 1. The inner for loop starts by setting rightSide to the leftmost node (first polled).
                    // 2. As the loop continues, rightSide is updated for each node, moving left to right.
                    // 3. After the loop, rightSide holds the rightmost node (last node processed) at that level.

                    q.offer(node.left);
                    q.offer(node.right);
                    // Adds the right child to the queue for the next level.
                }
            }

            if (rightSide != null) {
                res.add(rightSide.val);
            }
        }
        return res;
    }
}

