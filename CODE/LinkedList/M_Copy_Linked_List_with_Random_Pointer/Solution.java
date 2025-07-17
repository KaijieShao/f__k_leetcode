package CODE.LinkedList.M_Copy_Linked_List_with_Random_Pointer;

// Goal: clone the entire list, preserving both next and random pointers, 
//       so the new list is structurally identical but entirely independent.

public class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // Edge case: if the list is empty, there's nothing to copy, so it returns null.
        
        // --- Pass 1: Create and interleave copied nodes ---
        //             Each original node is followed immediately by its copy
        Node l1 = head;
        // Initializes a pointer 'l1' to traverse the original list. It starts at the head.

        while (l1 != null) {
            Node l2 = new Node(l1.val);
            // For each original node 'l1', it creates a new node 'l2' with the same value.

            l2.next = l1.next;
            // Sets the 'next' pointer of the new node 'l2' to point to 'l1's original next node.

            l1.next = l2;
            // Re-routes the 'next' pointer of the original node 'l1' to point to the new node 'l2'.
            // This places the new node directly after its original counterpart.

            l1 = l2.next;
            // Moves the 'l1' pointer forward two steps to the next original node (which is l2.next).
        }

        Node newHead = head.next;
        // Stores a reference to the head of the new, copied list 
        // This is the second node in the interleaved list) -> will be our return value.


        // --- Pass 2: Assign random pointers for the copied nodes ---

        l1 = head;
        // Resets the 'l1' pointer back to the head to traverse the interleaved list again.

        while (l1 != null) {
            if (l1.random != null) {
                // Checks if the original node 'l1' has a 'random' pointer.

                l1.next.random = l1.random.next;
                // This is the key step. 
                // 'l1.next'        is the copied node.
                // 'l1.random'      is the original node's random target.
                // 'l1.random.next' is the copy of the original's random target.

                // Set copied node's random pointer to the copy of the node that the original node's random pointer points to.
            }
            l1 = l1.next.next;
            // Moves 'l1' two steps ahead to the next original node in the interleaved list.
        }


        // --- Pass 3: Separate the original and copied lists ---

        l1 = head;
        // Resets the 'l1' pointer to the head of the interleaved list.

        while (l1 != null) {
            Node l2 = l1.next;
            // Creates a pointer 'l2' to the copied node.

            l1.next = l2.next;
            // Restores the 'next' pointer of the original node 'l1' to the next original node (l2.next).

            if (l2.next != null) {
            // l2.next points to the next original node in the interleaved list.
            // If true, it sets l2.next (the copied node's next pointer) to l2.next.next (the next copied node).

                l2.next = l2.next.next;
            }
            l1 = l1.next;
            // Moves the 'l1' pointer to the next original node.
        }

        return newHead;
        // Returns the head of the newly created, fully independent copied list.
    }
}

// 38:42