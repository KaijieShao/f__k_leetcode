package DSA.LinkedLists;


public class FastAndSlow {
    
    // Scenario:
    // Detect cycles or find specific positions (like the middle) in a LL using only constant extra space
    // Normal traversal can't detect cycles or find the midpoint in a single pass without extra memory!

    class Node {
        int value;
        Node next;
    
        Node(int value) {
            this.value = value;
        }
    }


    public static Node middleOfList(Node head) {
    // Q: Find the middle of a linked list

        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static boolean hasCycle(Node head) {
    // Q: Determine if a Linked List has a cycle

        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    public static Node cycleStart(Node head) {
    // Q: Determine if a Linked List has a cycle and return the head

        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        
        if (fast == null || fast.next == null) {  // Checks if the list does NOT have a cycle
            return null;
        }

        Node slow2 = head;                        // If a cycle exists
        while (slow != slow2) {                   
            slow = slow.next;                     // Both slow and slow2 then move one step at a time
            slow2 = slow2.next;                
        }
        return slow;                              // The node where they meet is returned: start of the cycle
    }
}


