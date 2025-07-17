package CODE.Stack.M_Daily_Temperatures;

import java.util.Stack;

public class Solution {

    public int[] dailyTemperatures(int[] temperatures) {

        int[] res = new int[temperatures.length];
        // Result is the same length as temperatures

        // Stack: stores the days still waiting for their next warmer temperature
        Stack<int[]> stack = new Stack<>();
        // Stack<int[]> means each item on the stack is an array of two integers.
        // When you do stack.push(new int[]{t, i}), you are pushing an array containing:
        //                                  t — the temperature for that day
        //                                  i — the index (the day number)
        // So, when you pop from the stack:
        //                                  pair[0] is the temperature of the waiting day.
        //                                  pair[1] is the index (which day in the array it was).

        for (int i = 0; i < temperatures.length; i++) {
        // Outer loop: Loop over each temperature in the input array

            int t = temperatures[i]; 
            // Get the current day's temperature -> 73

            while (!stack.isEmpty() && t > stack.peek()[0]) {
            // Inner loop: checks if the curreny day can complete the waiting days in the stack by being warmer
            // stack.peek()    gives you the top element of the stack without removing it
            // stack.peek()[0] gives you the temperature value at the top of the stack

                // After the inner loop finds a warmer temperature for previous days:

                int[] pair = stack.pop();  
                // This removes (pops) the top value from the stack and stores it in the array pair

                res[pair[1]] = i - pair[1];
                // i - pair[1] calculates how many days it took to find a warmer temperature for the day at index pair[1]
                // res[1] would always update the answer for day 1 only, which is incorrect
                // pair[1] is the index of the day that was waiting for a warmer temperature
            }

            stack.push(new int[]{t, i}); 
        }

        return res; 
    }
}

