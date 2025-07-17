package CODE.Stack.M_Car_Fleet;

import java.util.Stack;
import java.util.Arrays;

public class Solution {

    public int carFleet(int target, int[] position, int[] speed) {

        int[][] pair = new int[position.length][2];
        // Creates a 2D array with position.length rows and 2 columns
        // Each row represents a car.
        // 1. The 1st column (pair[i][0]) will store the car's position.
        // 2. The 2nd column (pair[i][1]) will store the car's speed.

        // i.e., So, for 5 cars, you get:
        // pair = [
        //   [__, __], // car 0
        //   [__, __], // car 1
        //   [__, __], // car 2
        //   [__, __], // car 3
        //   [__, __]  // car 4
        // ]

        for (int i = 0; i < position.length; i++) { 
            pair[i][0] = position[i]; 
            pair[i][1] = speed[i];    
        }
        // Fills the pair array

        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));  // Syntax: (parameters) -> {body}
        // A lambda function is a short way to write a function, mainly used for things like sorting or filtering collections

        // 'a' and 'b' are two different cars, each represented as [position, speed]
        // The lambda compares only the positions ([0]). It does not compare speeds ([1]).

        // The order of b[0] and a[0] matters!
        // Integer.compare(b[0], a[0]) sorts in descending order (higher position first).
        // Integer.compare(a[0], b[0]) would sort in ascending order (lower position first).

        // After sorting, cars are ordered from closest to farthest from the target (high position to low position)
        
        Stack<Double> stack = new Stack<>();
        // Creates a stack to store the time (as a double) it takes for each car to reach the target

        for (int[] p : pair) {
        // Loop & Stack determine how many fleets arrive at the target separately, based on their speeds and starting positions

            stack.push((double)(target - p[0]) / p[1]);
            // time = (target - position) / speed
            // (double) casts the numerator to double, ensuring the division produces a decimal result (not integer division)

            // A fleet is a group of cars that arrive at the target together at the same time.
            // IF block ensures only the lead car of each distinct fleet remains on the stack.
            
            if (stack.size() >= 2 && stack.peek() <= stack.get(stack.size() - 2)) {
            // stack.size() >= 2: make sure there are at least 2 cars on the stack to compare (current one and the one ahead)
            // stack.peek() <= stack.get(stack.size() - 2): last last car catch up to last car, arrive together, one fleet!

                stack.pop();
                // Remove the duplicate (the catch-up car's time), so only one representative for that fleet remains
            }
        }

        return stack.size();
    }
}
