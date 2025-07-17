package CODE.Stack.E_Valid_Parentheses;

import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        // Stack: validate parentheses by keeping track of opening brackets as you process the string

        java.util.Map<Character, Character> closeToOpen = new java.util.HashMap<>();
        // HashMap: defines valid bracket pairs

        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');

        for (char c : s.toCharArray()) {
        // Convert the input string `s` to a char array and iterate over each character `c`.

            if (closeToOpen.containsKey(c)) {
            // Checks if the current character 'c' is a closing bracket

                if (!stack.isEmpty() && stack.peek() == closeToOpen.get(c)) {
                // 1. Checks that there is at least one opening bracket on the stack to match against.
                // 2. checks if top opening bracket matches expected opening bracket for current closing bracket c

                    stack.pop();
                } else {
                    return false;
                }

            } else {
                // If `c` is not a key in closeToOpen, it must be an opening bracket: ‘(’, ‘[’, or ‘{’.
                stack.push(c);
                // Push that opening bracket onto the stack so we can match it later.
            }
        }

        return stack.isEmpty();
        // If the stack is empty, it means every opening bracket had a matching closing bracket and was popped off.
        // If the stack is not empty, it means there are unmatched opening brackets left.        
    }
}
