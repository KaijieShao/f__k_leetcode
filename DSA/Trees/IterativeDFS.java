package DSA.Trees;


import java.util.Stack;

public class IterativeDFS {

    // Scenario:
    // Iterative DFS directly resolves the key limitations of recursive DFS
    // Recursive DFS can cause stack overflow for deep trees/graphs, it also lacks explicit control for traversal
    // Iterative DFS uses its own 'Stack' to avoid system stack overflow. Customize the traversal more easily

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public static void inorder(TreeNode root) {             
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {          // There are nodes left to visit 
            if (curr != null) {                             // Visits all left, stack nodes, until reaches a leaf
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();                         // Left  (deepest unvisited left node)
                System.out.println(curr.val);               // Node  (after left subtree, before right subtree)
                curr = curr.right;                          // Right (moves to right child to continue traversal)
            }
        }
    }


    public static void preorder(TreeNode root) { 
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.println(curr.val);               // Step 1: Root
                if (curr.right != null) {                  
                    stack.push(curr.right);                 // Push 'right' to stack, so it's visited later
                }
                curr = curr.left;                           // Step 2: Left
            } else {
                curr = stack.pop();                         // Step 3: Right (only 'right' is ever pushed to stack)
            }
        }
    }


    public static void postorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();              // 'root' is pushed to the 'stack' stack
        stack.push(root);                                  

        Stack<Boolean> visit = new Stack<>();               // 'false' is pushed to the 'visit' stack (unvisited)
        visit.push(false);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();                    // Pop both stacks at the same time
            boolean visited = visit.pop();
            if (curr != null) {
                if (visited) {
                    System.out.println(curr.val);
                } else {
                    stack.push(curr);                       // Pushes same node back onto the stack, mark 'visited'
                    visit.push(true);
                    stack.push(curr.right);                 // Pushes right child first, then left child 
                    visit.push(false);                 // Then... we go over the loop to pop again!
                    stack.push(curr.left);
                    visit.push(false);
                }
            }
        }
    }
}

