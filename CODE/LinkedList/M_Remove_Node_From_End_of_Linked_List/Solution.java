package CODE.LinkedList.M_Remove_Node_From_End_of_Linked_List;

// 1. You are given the head of a singly linked list and an integer n.
// 2. You need to remove the n-th node from the end of the linked list.
// 3. After removing that node, return the modified head of the list.

// SLL

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int N = 0;
        // This variable will be used to store the total number of nodes in the linked list.

        ListNode cur = head;
        // This 'cur' pointer will be used to traverse the list

        while (cur != null) {
            N++;
            cur = cur.next;
        }
        // After this loop, 'N' holds the total number of nodes in the list

        int removeIndex = N - n;
        // Calculates the 0-based index of the node to remove, counting from the beginning of the list.
        // For example, if there are 5 nodes (N=5) and we want to remove the 2nd from the end (n=2), the removeIndex is 3.

        if (removeIndex == 0) {
            return head.next;
        }
        // Edge case: if the head is the node to be removed, it simply returns the second node, which becomes the new head.

        cur = head;
        // Resets the 'cur' pointer back to the head of the list to begin the second traversal.

        for (int i = 0; i < N - 1; i++) {
        // Goal: stop 'cur' at the node *just before* the one we want to remove

            if ((i + 1) == removeIndex) {
                // i is the current index.
                // i + 1 is the index of the node to be removed.
                
                cur.next = cur.next.next;
                break;
            }
            cur = cur.next;
            // cur iterates forward one node at a time until it's just before the node at removeIndex.
        }
        return head;
        // Returns the original head of the list. 
        // Works for all cases except when the head itself was removed (which was handled earlier)
    }
}

// 47:52