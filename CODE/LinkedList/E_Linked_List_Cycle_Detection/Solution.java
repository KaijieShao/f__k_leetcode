package CODE.LinkedList.E_Linked_List_Cycle_Detection;

// The question asks you to detect if a given singly linked list contains a cycle.
// 1. A cycle exists if, by following .next pointers, you can revisit a node you've already encountered.
// 2. You must return true if a cycle exists, or false if the list ends at null (no cycle).
// 3. You are not given the location of the cycle (index is internal info for test setup).

class Solution {

    public boolean hasCycle(ListNode head) {  

        ListNode fast = head;  
        ListNode slow = head;  

        while (fast != null && fast.next != null) {  
            fast = fast.next.next;   
            slow = slow.next;        
            if (fast == slow) return true;  
        }
        return false; 
    }
}

// 3:04