package DSA.Trees;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    // Scenario:
    // BST invented to solve inefficiency of searching, inserting, and deleting in unsorted arrays or LL (O(n))
    // Recursion is commonly used with Advanced Sorting Algorithms and Navigating Trees

    public Node root; 

    class Node {
        public int value; 
        public Node left;
        public Node right;

        Node(int value) {
            this.value = value; 
        }
    }


    // BST 
    public boolean insert(int value) {
        Node newNode = new Node(value);

        if (root == null) { 
            root = newNode;
            return true;
        }

        Node temp = root; 
        while (true) {
            if (newNode.value == temp.value) return false;
            if (newNode.value < temp.value) {
                if (temp.left == null) {
                    temp.left = newNode;
                    return true;
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = newNode;
                    return true;
                }
                temp = temp.right;
            }
        }
    }


    public boolean contains(int value) {
        if (root == null) return false;

        Node temp = root;
        while (temp != null) {                                            // 'null' = reach 'leaf'
            if (value < temp.value) {
                temp = temp.left;
            } else if (value > temp.value) {
                temp = temp.right;
            } else {
                return true;
            }
        }
        return false;
    }



    // rBST
    // 1. Recursive BST (rBST) does not solve a fundamentally different problem than an ordinary (iterative) BST
    // 2. Just code becomes cleaner and easier to read for tree structures, since trees are naturally recursive 
    // 3. No performance benefit—just a stylistic and sometimes organizational preference.

    private boolean rContains(Node currentNode, int value) {              // Encapsulates logic to client
        if (currentNode == null) return false;
        if (currentNode.value == value) return true;

        if (value < currentNode.value) {
            return rContains(currentNode.left, value);
        } else {
            return rContains(currentNode.right, value);
        }
    }
    
    public boolean rContains(int value) {                                 // Constructor overloading
        return rContains(root, value);
    }


    private Node rInsert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);                  // Create and return a new node
    
        if (value < currentNode.value) {
            currentNode.left = rInsert(currentNode.left, value);          
            // Updates the left child of currentNode on each recursive call

        } else if (value > currentNode.value) {
            currentNode.right = rInsert(currentNode.right, value);
        }
        return currentNode; 
    }
    
    public void rInsert(int value) {                              
    // It just calls the recursive insert (rInsert(root, value)) to perform insertion.
    // All updates happen inside the tree structure (modifying nodes/links), not by returning a value -> void

        if (root == null) root = new Node(value);
        rInsert(root, value); 
    }


    private Node deleteNode(Node currentNode, int value) {
        if (currentNode == null) return null;
    
        if (value < currentNode.value) {                                  // Recursively traverses the left subtree
            currentNode.left = deleteNode(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = deleteNode(currentNode.right, value);
        } else {
            if (currentNode.left == null && currentNode.right == null) {  // case 1: leaf
                currentNode = null;
            } else if (currentNode.left == null) {                        // case 2: one right child
                currentNode = currentNode.right;
            } else if (currentNode.right == null) {                       // case 3: one left child
                currentNode = currentNode.left;
            } else {
                int subTreeMin = minValue(currentNode.right);             // case 4: two children
                currentNode.value = subTreeMin;
                currentNode.right = deleteNode(currentNode.right, subTreeMin);
            }
        }
        return currentNode;
    }
    
    public void deleteNode(int value) {
        root = deleteNode(root, value);
    }
    
    private int minValue(Node currentNode) {
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }    


    
    // BFS
    // BFS (Breadth-First Search) and DFS (Depth-First Search) both traverse/search trees or graphs.
    // Same goal: Visit/search all nodes or find a specific value.
    // Difference: 1) BFS: Level by level (uses queue)
    //             2) DFS: As deep as possible before backtracking (uses stack or recursion)

    public ArrayList<Integer> BFS() {
        Node currentNode = root;
        Queue<Node> queue = new LinkedList<>();
        ArrayList<Integer> results = new ArrayList<>();
    
        queue.add(currentNode);
        while (queue.size() > 0) {
            currentNode = queue.remove();
            results.add(currentNode.value);

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return results;
    }

    

    // DFS
    public ArrayList<Integer> DFSPreOrder() {                             // root, left, right
        ArrayList<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                results.add(currentNode.value);
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
            }
        }
        new Traverse(root);
        return results;
    }
        

    public ArrayList<Integer> DFSPostOrder() {                            // left, right, root
        ArrayList<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
                results.add(currentNode.value);
            }
        }
        new Traverse(root);
        return results;
    }


    public ArrayList<Integer> DFSInOrder() {                              // left, root, right
        ArrayList<Integer> results = new ArrayList<>();

        class Traverse {
            Traverse(Node currentNode) {
                if (currentNode.left != null) {
                    new Traverse(currentNode.left);
                }
                results.add(currentNode.value);
                if (currentNode.right != null) {
                    new Traverse(currentNode.right);
                }
            }
        }
        new Traverse(root);
        return results;
    }
}


