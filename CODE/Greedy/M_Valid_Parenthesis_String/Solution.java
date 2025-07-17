package CODE.Greedy.M_Valid_Parenthesis_String;

// Given a string 's' of '(', ')', and '*':
// Check if 's' can be a valid parentheses string, '*' can be '(', ')' or empty.
// Return true if possible to make 's' valid, else false.

public class Solution {

    // Greedy

    public boolean checkValidString(String s) {
        int leftMin = 0, leftMax = 0;
        // Think of leftMin and leftMax as the range of possible open left parentheses at every step:
        // - leftMin = fewest possible open ( (if every '*' helps close a ')' or is 'empty' )
        // - leftMax = most possible open ( (if every '*' acts as an extra '(' )

        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftMin++;
                leftMax++;
            } 
            // For '(', increment both min and max open parentheses count
            
            else if (c == ')') {
                leftMin--;
                leftMax--;
            } 
            // For ')', decrement both min and max open parentheses count.
            
            // For '*', it can be '(', ')' or empty:
            else { 
                leftMin--;                                           
                // This assumes * acts as a ), which closes an open (, so the minimum unmatched ( goes down.

                leftMax++;                                                 
                // This assumes * acts as a (, which opens another parenthesis, so the maximum unmatched ( goes up.
            }

            if (leftMax < 0)  return false;
            // If the maximum possible number of unmatched '(' is negative, it means we've seen more ')' 
            // than '(' (even after using '*' as '('), so the string can't ever be valid.

            if (leftMin < 0)  leftMin = 0;
            // 'leftMin' tracks the fewest possible unmatched '(' at this point.
            // If it goes negative, it means we've potentially "over-closed" with ')' or used '*' as ')', 
            // but since '*' can also be empty (do nothing), we can't have fewer than 0 unmatched '('.
            // Resetting to 0 means: "if we used '*' as empty, the unmatched count could never go negative."

        }
        return leftMin == 0;
        // String can be valid -> All open parentheses are matched (possibly using * as needed)
    }
}

