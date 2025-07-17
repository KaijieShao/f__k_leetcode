package CODE.LinkedList.E_Merge_Two_Sorted_Linked_Lists;

// The question is asking you to combine two sorted linked lists into one new sorted linked list.
// 1. Each list is already sorted in ascending order.
// 2. You must merge the two lists so that the new list is also sorted in ascending order.
// 3. You should reuse the original nodes from list1 and list2 (do not create new nodes).
// 4. Return the head of the merged, sorted linked list.

public class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {                

        ListNode dummy = new ListNode(0);
        // 'dummy' node is a real node object acts as the temporary "head" of the new list

        ListNode node = dummy;
        // node is a pointer (reference) that initially points to the dummy node, and then moves forward as you adding nodes
        
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                node.next = list1;
                list1 = list1.next;
                // moves the list1 pointer to its next node after connecting node.next = list1

            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
            // After attaching the smaller node, move the node pointer forward to the new end of your merged list
        };

        // 1. After the while loop, one list may still have remaining nodes (because the lists could be different lengths).
        // 2. Those remaining nodes are already sorted and larger than all previous nodes in the merged list.

        node.next = (list1 != null) ? list1 : list2;

        return dummy.next;  
        // Return the merged list, skipping dummy node
    }
}

// 11:26