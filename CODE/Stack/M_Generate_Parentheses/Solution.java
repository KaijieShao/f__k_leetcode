package CODE.Stack.M_Generate_Parentheses;

import java.util.List;
import java.util.ArrayList;

// Goal: Generate all valid combinations of n pairs of parentheses.
// Each valid string must have:
// 1. Exactly n opens ( and n closes )
// 2. At any point, you cannot have more ) than ( (otherwise it's invalid)

import java.util.List;
import java.util.ArrayList;

public class Solution {
    
    // Define a helper function!
    private void backtrack(int openN, int closedN, int n, List<String> res, StringBuilder stack) {
    // int openN:           The number of open parentheses '(' used so far in the current combination.
    // int closedN:         The number of closed parentheses ')' used so far in the current combination.
    // int n:               The total number of pairs of parentheses we want to generate.
    // List<String> res:    The list that will store all valid combinations of parentheses.
    // StringBuilder stack: A mutable string that represents the current sequence of parentheses being built -> ❌ )()
        
        if (openN == closedN && openN == n) {
        // Base case: checking if all parentheses have been used
        // 1. Every open parenthesis `(` has a matching close parenthesis `)`
        // AND
        // 2. You've used up all `n` open parentheses (because `openN == n`)

            res.add(stack.toString()); 
            return; 
            // 1. stack is a temporary string (like "((()))"), building up one possible parentheses combination.
            // 2. When you hit the base case (all opens/closes used), that means stack contains a complete, valid answer.
            // 3. res is a list (like a bucket) that collects all valid answers.
            // 4. stack.toString() turns the current sequence into a normal string, and res.add(...) puts it into the list.
            // 5. return; means stop—this path is done, so go back and try other possibilities (backtracking).
        }

        // Example (n = 2):
        // 1. Start: ""                                  (openN=0, closedN=0)
        // 2. Add (          → "("                       (openN=1, closedN=0)
        // 3. Add ( again    → "(("                      (openN=2, closedN=0)
        // 4. Can’t add "(" (used up), can add ")":
        //    a. Add )       → "(()"                     (openN=2, closedN=1)
        //    b. Add ) again → "(())"                    (openN=2, closedN=2)       base case, add to result
        // 5. Backtrack steps, try adding ) earlier, or try other possibilities, etc.

        // Recursive case
        // Can I add an open parenthesis `(` ?     ->     Yes if: I haven't used all `n` yet -> openN < n
        // Allows you to keep generating more possibilities by adding ( until you hit the limit.

        // Backtracking:
        // 1. When recursion finishes one path (like building "(())"), it removes the last character (")"), 
        // 2. Goes back to the previous step, and tries other options that weren’t tried yet.
        // 3. If after remove last ")", string is "(()" again and no more options, it will backtrack more by remote "(" again
        // 3. This is called "backtracking": undoing choices to explore different sequences.
        
        if (openN < n) {
            stack.append('('); 
            backtrack(openN + 1, closedN, n, res, stack); 
            stack.deleteCharAt(stack.length() - 1);
        }

        // Recursive case
        // Can I add a close parenthesis `)` ?     ->     Yes if: There are more opens than closes -> closedN < openN
        // Only lets you add ) if it will NOT make the string invalid (never have more closes than opens at any point)
        if (closedN < openN) {
            stack.append(')'); 
            backtrack(openN, closedN + 1, n, res, stack); 
            stack.deleteCharAt(stack.length() - 1); 
        }
    }

    
    // Main Method
    public List<String> generateParenthesis(int n) {

        List<String> res = new ArrayList<>(); 
        // Create the result list to hold all combinations

        StringBuilder stack = new StringBuilder();
        // A built-in class used to build a string by adding characters one at a time

        backtrack(0, 0, n, res, stack);
        // Initialize the backtracking process to generate all valid parentheses combinations

        return res; 
    }
}
