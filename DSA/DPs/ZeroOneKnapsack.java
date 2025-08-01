package DSA.DPs;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ZeroOneKnapsack {

    // Scenario:
    // 0/1 Knapsack is designed to solve the optimal subset selection problem under a single capacity constraint
    // i.e., Packing a backpack (knapsack) for a trip: you want the most valuable set of things (by 'profit')
    //       but can't exceed the 'weight' limit, and can't take multiples of the same item (either 0 or 1 times)

    // Q: Return the 'max total profit' can be contained in the backpack - pick or not pick each item at most once

    // 1️⃣ Brute force Solution -> Time: O(2^n), Space: O(n), where n is the number of items
    public static int dfs(List<Integer> profit, List<Integer> weight, int capacity) {
        return dfsHelper(0, profit, weight, capacity);  // 0 means start from the 1st item
    }

    public static int dfsHelper(int i, List<Integer> profit, List<Integer> weight, int capacity) {
        if (i == profit.size()) {                         
            return 0;                                     // All options explored, no more items to be picked
        }

        int maxProfit = dfsHelper(i + 1, profit, weight, capacity); // What if I skip this item?

        int newCap = capacity - weight.get(i);                      // Calculate remaining capacity if you pick 'i'
        if (newCap >= 0) {                                          // Check if there's enough space to pick 'i'
            int p = profit.get(i) + dfsHelper(i + 1, profit, weight, newCap);  
            maxProfit = Math.max(maxProfit, p);                     // Take the best choice - pick or not pick 'i'
        }
        return maxProfit;
    }



    // 2️⃣ Top-Down Solution -> Time: O(n * m), Space: O(n * m), where n is the number of items & m is the capacity
    public static int memoization(List<Integer> profit, List<Integer> weight, int capacity) {
        int N = profit.size(), M = capacity;

        List<Integer[]> cache = new ArrayList<>();  // Each entry: [current item index, current knapsack capacity]
        for (int row = 0; row < N; row++) {         // Builds a 2D 'cache' for memoization
            cache.add(row, new Integer[M + 1]);     // Creates integer array (row) with capacity (0 to M inclusive) 
            Arrays.fill(cache.get(row), -1);        // Sets every value in that that row to -1 (uncomputed)
        }

        return memoHelper(0, profit, weight, capacity, cache);
    }

    public static int memoHelper(int i, List<Integer> profit, List<Integer> weight, int capacity, 
                                                                                    List<Integer[]> cache) {
        if (i == profit.size()) {             
            return 0;
        }
        if (cache.get(i)[capacity] != -1) {
            return cache.get(i)[capacity];
        }

        cache.get(i)[capacity] = memoHelper(i + 1, profit, weight, capacity, cache);   // Skip it

        int newCap = capacity - weight.get(i);
        if (newCap >= 0) {
            int p = profit.get(i) + memoHelper(i + 1, profit, weight, newCap, cache);  // Pick it
            cache.get(i)[capacity] = Math.max(cache.get(i)[capacity], p);              
        } 
        return cache.get(i)[capacity];
    } 

    

    // 3️⃣ Bottom-Up Solution -> Time: O(n * m), Space: O(n * m), where n is the number of items & m is the capacity
    public static int dp(List<Integer> profit, List<Integer> weight, int capacity) {
        int N = profit.size(), M = capacity;     // N: # of rows (each row = 1 item), M: # of cols (capacity)
        List<Integer[]> dp = new ArrayList<>();  // 'dp' is a table with N rows (height) and M cols (width)

        for (int row = 0; row < N; row++) {
            dp.add(row, new Integer[M + 1]);     // M + 1 to allow access to index 'M'
            Arrays.fill(dp.get(row), 0);     // Sets every value in the array at 'row' to 0
        }
        for (int i = 0; i < N; i++) {
            dp.get(i)[0] = 0;                    // Explicitly tell you no profit can be obtained at 0 capacity 
        }

        // Pattern:
        // 1. You must calculate first item first, which provides the base case for the DP table
        // 2. All future rows (for later items) depend on these initial values to build their solutions

        for (int c = 0; c <= M; c++) {                    
            if (weight.get(0) <= c) {                // 1st item fits in the knapsack at capacity 'c'
                dp.get(0)[c] = profit.get(0);  // Fill 1st row '0' at each col 'c' to its profit
            }                                              // else knapsack is too small to fit it, profit stays 0
        }

        // The nested loop will handle remaining rows -> each cell is filled ONCE by choosing the best option

        for (int i = 1; i < N; i++) {                      // Iterates over each item (each row after the first)
            for (int c = 1; c <= M; c++) {                 // Iterate over each capacity (each column)
                int skip = dp.get(i-1)[c];                 
                // FIRST CASE: Don't take it (loos at the prev row 'i-1' at current capacity 'c')
                // 1: If that cell is 0, no profit is possible without taking this item
                // 2: If it has a value, it means the best profit so far using previous items at this capacity

                int include = 0;                           // SECOND CASE: If the item doesn't fit, include stays 0
                if (c - weight.get(i) >= 0) {              // Fits!
                    include = profit.get(i) + dp.get(i-1)[c - weight.get(i)]; 
                }                                          // 1) Take current item (profit) + 2) Remaining capacity
                dp.get(i)[c] = Math.max(include, skip);    
            }
        }

        return dp.get(N-1)[M];                             // [last row, last col]
    }



    // 4️⃣ Bottom-Up (Optimized) -> Time: O(n * m), Space: O(m)
    public static int optimizedDp(List<Integer> profit, List<Integer> weight, int capacity) {
        int N = profit.size(), M = capacity;
        Integer[] dp = new Integer[M+1];           // Initializes a 1D array with size M+1
        Arrays.fill(dp, 0);

        for (int c = 0; c <= M; c++) {
            if (weight.get(0) <= c) {
                dp[c] = profit.get(0);       // Space complexity drops from O(N×M) to O(M)
            } 
        }

        for (int i = 1; i < N; i++) {
            Integer[] curRow = new Integer[M+1];   // Only two "rows" exist at any time (current and previous)
            Arrays.fill(curRow, 0);
            for (int c = 1; c <= M; c++) {
                int skip = dp[c];                  // Case 1
                int include = 0;                   // Case 2
                if (c - weight.get(i) >= 0) {
                    include = profit.get(i) + dp[c - weight.get(i)];
                }
                curRow[c] =  Math.max(include, skip);
            }
            dp = curRow;
        }
        return dp[M];                              // Return the max profit achievable with the capacity M
    }
}




