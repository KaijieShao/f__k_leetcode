package DSA.LinkedLists;

public class FastAndSlow {
    
    public static class ListNode {
        int val;
        ListNode next;
    
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode middleOfList(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static ListNode cycleStart(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        
        if (fast == null || fast.next == null) { 
            return null;
        }

        ListNode slow2 = head;                   
        while (slow != slow2) {
            slow = slow.next;  
            slow2 = slow2.next;
        }
        return slow;           
    }
}

