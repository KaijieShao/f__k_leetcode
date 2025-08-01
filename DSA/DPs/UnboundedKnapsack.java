package DSA.DPs;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class UnboundedKnapsack {

    // Scenario:
    // Given a set of items, each with a weight and a profit/value, you have a bag with a fixed weight capacity
    // You can pick any item any number of times (including zero) -> Max profit while not exceed bag's capacity
    // - Key difference from 0/1 Knapsack: Items can be picked multiple times (unbounded supply)

    // Brute force Solution: Time: O(2^n), Space: O(n), where n is the number of items
    public static int dfs(List<Integer> profit, List<Integer> weight, int capacity) {
        return dfsHelper(0, profit, weight, capacity);
    }

    public static int dfsHelper(int i, List<Integer> profit, List<Integer> weight, int capacity) {
        if (i == profit.size()) {
            return 0;
        }

        int maxProfit = dfsHelper(i + 1, profit, weight, capacity);

        int newCap = capacity - weight.get(i);
        if (newCap >= 0) {
            int p = profit.get(i) + dfsHelper(i, profit, weight, newCap); // Yes! You can pick it again! (no +1)
            maxProfit = Math.max(maxProfit, p);
        }
        return maxProfit;
    }



    // Memoization Solution: Time: O(n * m), Space: O(n * m), where n is the number of items & m is the capacity
    public static int memoization(List<Integer> profit, List<Integer> weight, int capacity) {
        int N = profit.size(), M = capacity;

        List<Integer[]> cache = new ArrayList<>();
        for (int row = 0; row < N; row++) {    
            cache.add(row, new Integer[M + 1]);
            Arrays.fill(cache.get(row), -1);    
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

        cache.get(i)[capacity] = memoHelper(i + 1, profit, weight, capacity, cache);

        int newCap = capacity - weight.get(i);
        if (newCap >= 0) {
            int p = profit.get(i) + memoHelper(i, profit, weight, newCap, cache); // You can pick it again!
            cache.get(i)[capacity] = Math.max(cache.get(i)[capacity], p);  
        }
        return cache.get(i)[capacity];
    } 



    // Bottom-Up Solution: Time: O(n * m), Space: O(n * m), where n is the number of items & m is the capacity
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
                dp.get(0)[c] = (c / weight.get(0)) * profit.get(0);
            } 
        }
        // For a given capacity c, it calculates how many times item 0 fits: c / weight.get(0) (integer division)
        // Multiplies by the profit of item 0
        // This gives the maximum profit you can get using only item '0' for capacity 'c'

        for (int i = 1; i < N; i++) {
            for (int c = 1; c <= M; c++) {
                int skip = dp.get(i-1)[c];
                int include = 0;
                if (c - weight.get(i) >= 0) {
                    include = profit.get(i) + dp.get(i)[c - weight.get(i)];
                }
                dp.get(i)[c] = Math.max(include, skip);
            }
        }
        return dp.get(N-1)[M];
    }



    // Memory Optimized Bottom-Up Solution: Time: O(n * m), Space: O(m)
    public static int optimizedDp(List<Integer> profit, List<Integer> weight, int capacity) {
        int N = profit.size(), M = capacity;
        Integer[] dp = new Integer[M+1];
        Arrays.fill(dp, 0);

        // Here, you do not need to separately fill the first line (row) of the table in this optimized approach

        for (int i = 1; i < N; i++) {
            Integer[] curRow = new Integer[M+1];
            Arrays.fill(curRow, 0);
            for (int c = 1; c <= M; c++) {
                int skip = dp[c];
                int include = 0;
                if (c - weight.get(i) >= 0) {
                    include = profit.get(i) + curRow[c - weight.get(i)]; 
                }
                // Unbounded: curRow[c - weight.get(i)] allows you to reuse the same item multiple times 
                // 0/1: you would use the previous row: prevRow[c - weight.get(i)] (each one picked at most once)

                curRow[c] =  Math.max(include, skip);
            }
            dp = curRow;
        }
        return dp[M];
    }
}

