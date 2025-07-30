package DSA.DPs.Basics;


public class One_Dimension {

    // Brute Force (Plain Recursion):
    public static int bruteForce(int n) {  
        if (n <= 1) {
            return n;
        }
        return bruteForce(n - 1) + bruteForce(n - 2);
    }
    // Wastes time by recalculating the same results repeatedly


    // Memoization (Top-Down):
    public static int memoization(int n, int[] cache) {
        if (n <= 1) {
            return n;
        }
        if (cache[n] != 0) {
            return cache[n];
        }

        cache[n] = memoization(n - 1, cache) + memoization(n - 2, cache);
        return cache[n];
    }
    // Pass cache ensures computed Fib values are saved and reused, so repeated computations are avoided


    // Tabulation (Bottom-Up):
    public static int dp(int n) {
        if (n < 2) { 
            return n;
        }

        int[] dp = {0, 1};         // Fixed-size array (length 2) -> initialized to 0 and 1, updated iteratively
        int i = 2;
        while (i <= n) {
            int tmp = dp[1];
            dp[1] = dp[0] + dp[1];
            dp[0] = tmp;
            i++;
        }
        return dp[1];
    }
    // Solves smallest subproblems first, updates until it gets the largest (nth) Fibonacci number
}


