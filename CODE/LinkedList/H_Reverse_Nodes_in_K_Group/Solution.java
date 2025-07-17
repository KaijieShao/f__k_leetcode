package CODE.LinkedList.H_Reverse_Nodes_in_K_Group;

// The question asks you to reverse the nodes of a singly linked list in groups of size k
// 1. For every consecutive group of k nodes, reverse the order of nodes within that group.
// 2. If the remaining nodes are fewer than k, leave them as they are (do not reverse).
// 3. You can only change the next pointers (not node values).

class Solution {

    // Main method: Orchestrates group-by-group reversal
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        // Creates a dummy node with value as '0' and point to the head.
    
        ListNode groupPrev = dummy;
    
        while (true) {
            ListNode kth = getKth(groupPrev, k);
            // The group to reverse starts at groupPrev.next and ends at kth (inclusive).
    
            if (kth == null) {
                break;
            }
            // If kth is null, it means there are fewer than k nodes left.

            ListNode groupNext = kth.next;      // Point to the node after the current group
            ListNode prev = kth.next;           // Point to the node after the current group
            ListNode curr = groupPrev.next;     // Point to the first node in the group being reversed
    
            while (curr != groupNext) {         // When 'curr' reaches 'groupNext', you finished reversing the entire group
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }
            // Now, 'curr' point to the node after the group (groupNext); 'tem' point to the tail of the reversed group

            // 1. 'groupPrev' point to the dummy node originally
            // 2.  after each group reversal, 'groupPrev' is updated to point to the tail of the most recently reversed group
    
            ListNode tmp = groupPrev.next;     // Store the head of the new group

            groupPrev.next = kth;             
            // 1. groupPrev is the node before the group being reversed.
            // 2. kth is the tail of the group before reversal, but after reversal it becomes the new head of this group.
            // 3. So: It links the end of the last processed section to the new (reversed) start of the current group.

            groupPrev = tmp;                 
        }
        return dummy.next;
    }

    // Helper method: Locates the end node for each group
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
        // 'curr' is now at the k-th node, or null if the list was shorter than 'k' nodes.
    }
}

// 45:00