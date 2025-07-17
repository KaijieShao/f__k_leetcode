package CODE.LinkedList.M_Add_Two_Numbers;

// Imagine you have two numbers, but instead of writing them as normal, you break each digit into a linked list in reverse order.
// Example: Number 123 becomes nodes: 3 -> 2 -> 1
// You get two such lists (two numbers).
// You need to add the numbers digit by digit and make a new linked list for the result, also in reverse order.

// Example:
// First list:  2 -> 4 -> 3 (means 342)
// Second list: 5 -> 6 -> 4 (means 465)
// Add: 342 + 465 = 807
// Output as a linked list: 7 -> 0 -> 8 (means 807)

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        // At the start, dummy is just a standalone node

        ListNode cur = dummy;
        // 'current' pointer to build new result list (starts at the dummy node)

        // Carry is needed because:
        // 1. When adding two digits (from the linked lists), their sum can be > 9
        // 2. Only the unit digit can go in the current node; the tens digit (carry) must be added to the next pair of digits.
        //    Example: 7 + 8 = 15 -> Write 5, carry over 1 to the next place.

        int carry = 0;
        // carry is set to 0 initially because there's no carry before you start adding

        while (l1 != null || l2 != null || carry != 0) {
        // This ensures all digits and any final carry are processed

            int v1 = (l1 != null) ? l1.val : 0;
            // If l1 is not null, v1 is assigned l1.val. If l1 is null, v1 is assigned 0.

            int v2 = (l2 != null) ? l2.val : 0;

            int val = v1 + v2 + carry;
            // Calculates the total sum for the current digit position

            carry = val / 10;
            // In this problem, carry will only ever be 0 or 1

            val = val % 10;
            // You store the remainder (ones place) in val, and the carry handles the tens.            

            cur.next = new ListNode(val);
            // creates a new node in the result linked list with the digit val for the current addition step
            // This builds the sum as a linked list, one digit (node) at a time, in reverse order.

            cur = cur.next;
            // Moves the 'cur' pointer forward to this newly created node.

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        // After the loop: you have built a new linked list representing the sum, with each digit as a node, in reverse order

        return dummy.next;
        // 1. dummy is a placeholder node (not part of the answer).
        // 2. All result nodes were attached to dummy.next, dummy.next.next, etc.
        // 3. Returning dummy.next gives you the head of the real result list (the first digit node).
    }
}

