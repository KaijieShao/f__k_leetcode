package DSA.DPs;


import java.util.Arrays;

public class LCS {
    
    // Scenario:
    // Longest Common Subsequence: Given two sequences (like strings), find the longest subsequence present in both
    // Why it was needed? -> To quantify and compare similarity between sequences

    // Brute Force Solution -> Time: O(2^(n + m)), Space: O(n + m)
    public static int dfs(String s1, String s2) {
        return dfsHelper(s1, s2, 0, 0);                 // Start from index 0 for both strings
    }

    public static int dfsHelper(String s1, String s2, int i1, int i2) {
        if (i1 == s1.length() || i2 == s2.length()) {
            return 0;                                         // Reached the end of at least one string -> DONE!
        } 

        if (s1.charAt(i1) == s2.charAt(i2)) {                 // If current characters match: 
            return 1 + dfsHelper(s1, s2, i1 + 1, i2 + 1);     // Count this match (add 1) and move to next chars
        } else {                                              // If they don't match:
            return Math.max(dfsHelper(s1, s2, i1 + 1, i2),    // Skip the current char in s1 and check the rest
                            dfsHelper(s1, s2, i1, i2 + 1));   // Skip the current char in s2 and check the rest
        }                                                     // Picks the maximum result between the two options
    }



    // Memoization Solution -> Time: O(n * m), Space: O(n + m)
    public static int memoization(String s1, String s2) {
        int N = s1.length(), M = s2.length();                 // Table: N as rows, M as cols
        int[][] cache = new int[N][M];
        for (int[] row: cache) {
            Arrays.fill(row, -1);
        }
        return memoHelper(s1, s2, 0, 0, cache);
    }

    public static int memoHelper(String s1, String s2, int i1, int i2, int[][] cache) {
        if (i1 == s1.length() || i2 == s2.length()) {
            return 0;
        }
        if (cache[i1][i2] != -1) {
            return cache[i1][i2];
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            cache[i1][i2] = 1 + memoHelper(s1, s2, i1 + 1, i2 + 1, cache);
        } else {
            cache[i1][i2] = Math.max(memoHelper(s1, s2, i1 + 1, i2, cache), 
                                     memoHelper(s1, s2, i1, i2 + 1, cache));
        }
        return cache[i1][i2];
    }



    // Bottom-up Approach -> Time: O(n * m), Space: O(n + m)
    public static int dp(String s1, String s2) {
        int N = s1.length(), M = s2.length();
        int[][] dp = new int[N+1][M+1];                 // Create a 2D array that also includes the boundaries 

        for (int i = 0; i < N; i++) {                   // Loop through each cell (every character)
            for (int j = 0; j < M; j++) {               
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i+1][j+1] = 1 + dp[i][j];        // Add 1 to found, and then move to next character
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                    // Picks the max LCS length possible by skipping one character from either string at this step
                    // dp[i][j+1]: Skip s1[i] (move down in the DP table)
                    // dp[i+1][j]: Skip s2[j] (move right in the DP table)
                }
            }
        }
        return dp[N][M];
    }



    // Memory Optimized Bottom-up Approach -> Time: O(n * m), Space: O(m)
    public static int optimizedDp(String s1, String s2) {
        int N = s1.length(), M = s2.length();
        int[] dp = new int[M + 1];                     // M represents the width (number of columns)

        for (int i = 0; i < N; i++) {                  // Loop through each row (every character in s1)
            int[] curRow = new int[M + 1];             // 'curRow' is current row; 'dp' is previous row
            for (int j = 0; j < M; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    curRow[j+1] = 1 + dp[j];           // Adds 1 for match, updates next cell, advances both string
                } else {
                    curRow[j+1] = Math.max(dp[j + 1], curRow[j]);
                    // Finds the maximum LCS length by skipping a character from either string:
                    // dp[j+1]: skip current character in s1
                    // curRow[j]: skip current character in s2
                }
            }
            dp = curRow;
        }
        return dp[M];
    }
}

