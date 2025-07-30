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

    // Brute force Solution -> Time: O(2^n), Space: O(n), where n is the number of items
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


    // Top-Down Solution -> Time: O(n * m), Space: O(n * m), where n is the number of items & m is the capacity
    public static int memoization(List<Integer> profit, List<Integer> weight, int capacity) {
        int N = profit.size(), M = capacity;
        List<Integer[]> cache = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            cache.add(row, new Integer[M + 1]);
            Arrays.fill(cache.get(row), -1);
        }

        return memoHelper(0, profit, weight, capacity, cache);
    }

    public static int memoHelper(int i, List<Integer> profit, List<Integer> weight, int capacity, List<Integer[]> cache) {
        if (i == profit.size()) {
            return 0;
        }
        if (cache.get(i)[capacity] != -1) {
            return cache.get(i)[capacity];
        }

        cache.get(i)[capacity] = memoHelper(i + 1, profit, weight, capacity, cache);

        int newCap = capacity - weight.get(i);
        if (newCap >= 0) {
            int p = profit.get(i) + memoHelper(i + 1, profit, weight, newCap, cache);
            cache.get(i)[capacity] = Math.max(cache.get(i)[capacity], p);  
        }
        return cache.get(i)[capacity];
    } 

    
    // Bottom-Up Solution -> Time: O(n * m), Space: O(n * m), where n is the number of items & m is the capacity
    public static int dp(List<Integer> profit, List<Integer> weight, int capacity) {
        int N = profit.size(), M = capacity;
        List<Integer[]> dp = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            dp.add(row, new Integer[M + 1]);
            Arrays.fill(dp.get(row), 0);
        }

        for (int i = 0; i < N; i++) {
            dp.get(i)[0] = 0;
        }
        for (int c = 0; c <= M; c++) {
            if (weight.get(0) <= c) {
                dp.get(0)[c] = profit.get(0);
            } 
        }

        for (int i = 1; i < N; i++) {
            for (int c = 1; c <= M; c++) {
                int skip = dp.get(i-1)[c];
                int include = 0;
                if (c - weight.get(i) >= 0) {
                    include = profit.get(i) + dp.get(i-1)[c - weight.get(i)];
                }
                dp.get(i)[c] = Math.max(include, skip);
            }
        }
        return dp.get(N-1)[M];
    }

    public static int optimizedDp(List<Integer> profit, List<Integer> weight, int capacity) {
        int N = profit.size(), M = capacity;
        Integer[] dp = new Integer[M+1];
        Arrays.fill(dp, 0);

        for (int c = 0; c <= M; c++) {
            if (weight.get(0) <= c) {
                dp[c] = profit.get(0);
            } 
        }

        for (int i = 1; i < N; i++) {
            Integer[] curRow = new Integer[M+1];
            Arrays.fill(curRow, 0);
            for (int c = 1; c <= M; c++) {
                int skip = dp[c];
                int include = 0;
                if (c - weight.get(i) >= 0) {
                    include = profit.get(i) + dp[c - weight.get(i)];
                }
                curRow[c] =  Math.max(include, skip);
            }
            dp = curRow;
        }
        return dp[M];
    }
}




