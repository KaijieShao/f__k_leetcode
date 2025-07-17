package CODE.Greedy.M_Gas_Station;

// You have several gas stations in a circle, each with some gas (array gas) and a cost to reach the next station (array cost).
// You start with zero gas at any station you choose.
// At each station, you fill up with gas[i], and to reach the next station, you must spend cost[i].
// Find the index of the station to start from so that you can complete a full circle without running out of gas at any point.
// If it's not possible from any station, return -1.

import java.util.Arrays;

public class Solution {

    // Greedy

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
        // Checks if total gas available is less than total cost required.
        
            return -1; 
            // If so, it's impossible to complete the circuit from any station.
        }

        int total = 0; // Tracks the current excess/shortage of gas while traversing.
        int res = 0;   // Tracks the current candidate starting index.

        for (int i = 0; i < gas.length; i++) { 
        // Loop through each gas station (index i)

            total += gas[i] - cost[i]; 
            // Add gas received at station i and subtract gas spent to next station.

            if (total < 0) { 
            // If total drops below zero, you cannot reach the next station from res.

                total = 0; 
                // Reset total to zero for the next segment.

                res = i + 1; 
                // Update the candidate starting index to the next station.
            }
        }
        return res; 
        // Return the valid starting index.
    }
}

