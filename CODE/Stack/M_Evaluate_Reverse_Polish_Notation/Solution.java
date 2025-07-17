package CODE.Stack.M_Evaluate_Reverse_Polish_Notation;

import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {        

        Stack<Integer> stack = new Stack<>(); 
        //  Stack stores intermediate results after each operand operation

        for (String c : tokens) { 
            if (c.equals("+")) { 
                stack.push(stack.pop() + stack.pop()); 
            } else if (c.equals("-")) { 
                int a = stack.pop(); 
                int b = stack.pop();
                stack.push(b - a); 
            } else if (c.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (c.equals("/")) {
                int a = stack.pop(); 
                int b = stack.pop(); 
                stack.push(b / a); 
            } else { 
            // When the input has only one number, it is pushed and immediately returned as the result, with no operations performed.
                stack.push(Integer.parseInt(c)); 
            }
        }

        return stack.pop(); 
        // After processing all tokens, the stack contains only the final calculated result at the top
    }
}

// Suppose tokens = ["2", "1", "+", "3", "*"]
// This represents (2 + 1) * 3.

// Let’s walk through your code:
// "2": Not an operator, so push 2 onto the stack.
// Stack: [2]
// "1": Not an operator, so push 1.
// Stack: [2, 1]
// "+": Operator. Pop 1 and 2, add them (2 + 1 = 3), push result.
// Stack: [3]
// "3": Not an operator, so push 3.
// Stack: [3, 3]
// "*": Operator. Pop 3 and 3, multiply (3 * 3 = 9), push result.
// Stack: [9]

// At the end, pop and return the result: 9.