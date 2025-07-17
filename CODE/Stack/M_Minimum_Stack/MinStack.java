package CODE.Stack.M_Minimum_Stack;

import java.util.Stack;
// Java's built-in Stack class is internally backed by a dynamic array

public class MinStack {
    private Stack<Integer> stack;    
    private Stack<Integer> minStack; 

    // Two stacks are used to efficiently support getting the current minimum in constant time:
    // 1. stack:    stores all elements (standard stack operations).
    // 2. minStack: stores the minimum value at each level (tracks current min).

    public MinStack() { 
        stack = new Stack<>();     
        minStack = new Stack<>();  
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) { 
        // If minStack is empty or val is new minimum

            minStack.push(val); 
        }
    }

    // After each push, the top of minStack is ALWAYS the 'current' minimum value of the main stack

    public void pop() {
        if (stack.isEmpty()) return;     
        int top = stack.pop();           
        if (top == minStack.peek()) {
            minStack.pop();             
        }

        // 1. Every time you push a new minimum (or a value equal to the current minimum), you also push it onto minStack.
        // 2. When you pop, if that popped value was the current minimum... 
        // 3. You remove it from both stacks so the next minimum becomes available on top of minStack! 

        // i.e., Suppose you push: 5, 3, 3, 2
        // stack:     [5, 3, 3, 2]
        // minStack:  [5, 3, 3, 2]
        // Now, you pop:
        // stack.pop() removes 2 (top = 2)
        // minStack.peek() is also 2
        // So, you also minStack.pop() -> Stack 移除 2 后，Stack 最小值就不是 2了！所要 minStack 也要移除 2，确保从下一个最小值开始！
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
