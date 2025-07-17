package CODE.LinkedList.M_Reorder_Linked_List;

// Reorder a singly linked list so that nodes are rearranged from the ends towards the center, in an alternating fashion:

// Take the 1st node, then the last node, then the 2nd node, then the 2nd-to-last node, and so on.
// Example: Input [2, 4, 6, 8] becomes [2, 8, 4, 6].
// You cannot change the values inside the nodes; you must rearrange the pointers to achieve this new order.

// Goal: Modify the list in-place to achieve the new node order: [0, n-1, 1, n-2, 2, n-3, ...]

class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        // Setting fast = head.next makes slow stop at the first middle node in even-length lists.
        // This is important for splitting the list into two nearly equal halves for merging later.
        
        while (fast != null && fast.next != null) {
        // The loop stops when fast is at the end or one before the end

            slow = slow.next;
            fast = fast.next.next;
            // The pattern is always "slow moves one, fast moves two".
        }

        // After the first while loop:
        // 1. slow points to the middle node (for even length, the first of the two middles).
        // 2. fast points to the last node   (for even length, fast will be null and not point to any node)

        ListNode second = slow.next;
        // Point to the node one after the middle

        ListNode prev = slow.next = null; 
        // 1. Sets the next pointer of the slow node to null, breaking the list into two parts.
        // 2. prev is a new pointer variable (not a node) that initialized to null to help reverse the second half of the list
        
        while (second != null) {
            ListNode tmp = second.next;
            second.next = prev;
            prev = second;
            second = tmp;
        }

        // 1. After the reversal loop, 'second' is null.
        // 2. The 'head' of the reversed list is now pointed to by 'prev'.

        // Now, we can merge the two halves alternatively

        ListNode first = head;
        // 'first' is the head of the original list

        second = prev;
        // 'second' is the head of the reversed list

        while (second != null) {
        // Ensures all nodes from the reversed half are merged
        // But the merge always starts with a node from the first half

            ListNode tmp1 = first.next;
            // next node after the current first node in the first half

            ListNode tmp2 = second.next;
            // next node after the current second node in the reversed half

            first.next = second;
            second.next = tmp1;
            // Step 1: Link first.next to second, and second.next to tmp1 (which is the next node from the first half)

            first = tmp1;
            second = tmp2;
            // Step 2: Move both pointers forward: first = tmp1; and second = tmp2
        }
    }
}

// 35:00