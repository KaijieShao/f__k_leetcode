package CODE.LinkedList.E_Reverse_Linked_List;

public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        // no previous node at the start

        ListNode curr = head;
        // both `curr` and `prev` start at the head of the list

        // SLL requires this approach since you only have the forward pointer and must track the previous node manually

        // To reverse a singly linked list, you do not modify the values inside the nodes.
        // You only reverse the direction of the pointers (-> becomes <-)

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}

// 5:59