package DSA.DPs;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ZeroOneKnapsack {
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
            int p = profit.get(i) + dfsHelper(i + 1, profit, weight, newCap);
            maxProfit = Math.max(maxProfit, p);
        }
        return maxProfit;
    }

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