package CODE.SlidingWindow.E_Best_Time_to_Buy_and_Sell_Stock;

// Find the maximum difference between two prices where the lower price comes before the higher price in the array.

public class Solution {
    public int maxProfit(int[] prices) {
        int l = 0, r = 1; // l = buy, r = sell
        int maxP = 0;    

        while (r < prices.length) {
            if (prices[l] < prices[r]) {
                int profit = prices[r] - prices[l];
                maxP = Math.max(maxP, profit);
            } else { // loss money
                l = r;
                // So, we slide our buy day 'l' forward to the current day 'r'.
            }
            r++;
            // Moves the 'r' (sell) pointer to the next day in every iteration.
        }
        return maxP;
    }
}

// 6:24
