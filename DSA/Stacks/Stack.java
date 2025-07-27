package DSA.Stacks;


public class Stack {

    // Scenario:
    // Solve problems where you need to manage data in a LIFO order i.e., backtracking, undo operations etc
    // Choose ArrayList for better cache performance and less memory per item
    // choose LinkedList for frequent dynamic resizing and simple O(1) operations without resizing costs.

    private Node top;                                 // All operations (push, pop, peek) happen at the top
    private int height;                               // Track how many nodes are in the stack

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }
    

    public Stack(int value) {
        Node newNode = new Node(value);
        top = newNode;
        height = 1;
    }


    public void printStack() {                        // Print the stack from top to bottom
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }


    public void getTop() {
        if (top == null) {
            System.out.println("Top: null");
        } else {
            System.out.println("Top: " + top.value);
        }
    }


    public void getHeight() {
        System.out.println("Height: " + height);
    }


    public void push(int value) {
        Node newNode = new Node(value);
        if(height == 0) {
            top = newNode;
        } else {
            newNode.next = top;                     // Set newNode.next to the current top
            top = newNode;                          // Update top to be the new node
        }
        height++;
    }


    public Node pop() {
        if (height == 0) return null;

        Node temp = top;
        top = top.next;
        temp.next = null;                           // Disconnect popped node: not necessary, but a good practice
        height--;

        return temp;
    }
}


