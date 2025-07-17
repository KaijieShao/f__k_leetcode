package DSA.Trees;

public class rBST {

    public Node root;

	class Node {
        public int value;
        public Node left;
        public Node right;

        Node(int value) {
            this.value = value;
        }
    }    

    private boolean rContains(Node currentNode, int value) {
        if (currentNode == null) return false;
    
        if (currentNode.value == value) return true;
    
        if (value < currentNode.value) {
            return rContains(currentNode.left, value);
        } else {
            return rContains(currentNode.right, value);
        }
    }
    
    public boolean rContains(int value) {
        return rContains(root, value);
    }


    private Node rInsert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value);
    
        if (value < currentNode.value) {
            currentNode.left = rInsert(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = rInsert(currentNode.right, value);
        }
    
        return currentNode;
    }
    
    public void rInsert(int value) {
        if (root == null) root = new Node(value);
        rInsert(root, value);
    }


    private Node deleteNode(Node currentNode, int value) {
        if (currentNode == null) return null;
    
        if (value < currentNode.value) {
            currentNode.left = deleteNode(currentNode.left, value);
        } else if (value > currentNode.value) {
            currentNode.right = deleteNode(currentNode.right, value);
        } else {
            if (currentNode.left == null && currentNode.right == null) {
                currentNode = null;
            } else if (currentNode.left == null) {
                currentNode = currentNode.right;
            } else if (currentNode.right == null) {
                currentNode = currentNode.left;
            } else {
                int subTreeMin = minValue(currentNode.right);
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
    
}
 
