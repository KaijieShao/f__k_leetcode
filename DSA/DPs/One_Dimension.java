package DSA.DPs;

public class One_Dimension {

    public static int bruteForce(int n) {
        if (n <= 1) {
            return n;
        }
        return bruteForce(n - 1) + bruteForce(n - 2);
    }

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

    public static int dp(int n) {
        if (n < 2) { 
            return n;
        }

        int[] dp = {0, 1};
        int i = 2;
        while (i <= n) {
            int tmp = dp[1];
            dp[1] = dp[0] + dp[1];
            dp[0] = tmp;
            i++;
        }
        return dp[1];
    }
}
