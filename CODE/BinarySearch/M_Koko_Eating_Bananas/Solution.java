package CODE.BinarySearch.M_Koko_Eating_Bananas;

// Find the minimum integer eating rate k (bananas per hour), so that you can finish all the piles of bananas in at most h hours.
// 1. Each hour, you must eat bananas from a single pile.
// 2. If a pile has less than k bananas, you eat all remaining bananas in that pile in that hour, and cannot start a new pile in the same hour.

import java.util.Arrays;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt();
        // finds the maximum value in the piles array and assigns it to r

        int res = r;
        // You set res (k) to the max as the initial "answer" before searching for a better (smaller) rate.

        while (l <= r) { // Check every possible value including the last 'l == r'
            int k = (l + r) / 2;

            long totalTime = 0;
            // totalTime is always a whole number of hours.
            // If the sum of hours for all piles is very large, int could overflow, so long is safer.

            // Inner: adds up the hours needed to eat each pile at speed 'k'
            for (int p : piles) {
                totalTime += Math.ceil((double) p / k);
                // Sum the total hours needed to finish all piles at speed k.
            }

            // Now, 'totalTime' is the total hours needed to each all piles at speed 'k' (sum of all piles)

            if (totalTime <= h) { // Current speed 'k' is fast enough to finish in time
                res = k;          // Record the candidate answer
                r = k - 1;        // Move the right bound
            } else {              // 'k' is too slow (can't finish in time)
                l = k + 1;        // Move the left bound
            }
        }
        return res;
    }
}

