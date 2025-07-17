package CODE.LinkedList.H_Merge_K_Sorted_Linked_Lists;

import java.util.ArrayList;
import java.util.List;

// You must merge k already sorted linked lists into one sorted list efficiently, not just by brute-force sorting. The challenge is to do this with optimal time and space.

public class Solution {

    // main method: manages the overall merging process for all lists, organizing and controlling the merge passes
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // Handles the edge case

        // Outer: keeps repeating until only 1 final merged list remains
        while (lists.length > 1) {
            List<ListNode> mergedLists = new ArrayList<>();
            // Each ListNode in ArrayList is the head of a linked list; all nodes are still connected via their next pointers.
            // The ArrayList does not sort nodes.
            // 1. It just temporarily holds the heads of merged lists after each merge pass.
            // 2. It helps you organize which lists to merge in the next round.

            // Inner: merge 2 lists at a time, adding each merged result to a new list
            for (int i = 0; i < lists.length; i += 2) {
                ListNode l1 = lists[i];
                ListNode l2 = (i + 1) < lists.length ? lists[i + 1] : null;
                mergedLists.add(mergeList(l1, l2));
            }
            // Now, mergedLists contains only the head nodes of each merged sub linked list, not all nodes

            lists = mergedLists.toArray(new ListNode[0]);
            // Changes the storage from an ArrayList back to an array, ready for the next round of merging.
            // ListNode[0]
            // 1. You could use any number (e.g., new ListNode[mergedLists.size()]), but 0 is shorter and standard practice.
            // 2. [0] does not control the length of the result; it just sets the array type.
        }
        return lists[0];
        // Returns head node, which represents the entire merged list
    }


    // helper method: does the actual merging of two sorted linked lists into one sorted linked list at each step
    private ListNode mergeList(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(); // Node
        ListNode tail = dummy;           // Pointer

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
            // Advance the 'tail' pointer to the node that was just added.
        }

        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }

        return dummy.next;
        // Returns the node *after* the dummy node, which is the actual head of our new, merged list.
    }
}

// 42:52