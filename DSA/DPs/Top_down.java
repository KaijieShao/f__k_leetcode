package DSA.DPs;


public class Top_down {

    // Scenario:
    // Problems that require dynamic programming (DP) usually have:
    // 1. Overlapping subproblems: repeated subproblems get solved multiple times i.e., fib(2) recalculated 2 times
    // 2. Optimal substructure: combine optimal parts to get the optimal whole

    // Top-Down: Starts from the main problem (fib(n)) and recursively solves subproblems
    // Memoization: Stores results of subproblems (memo[n]) to avoid redundant calculations

    static Integer[] memo = new Integer[100];
    static int counter = 0;

    public static int fib(int n) {
        counter++;

         if (memo[n] != null) {              
            return memo[n]; 
         }

         if (n == 0 || n == 1) { 
           return n; 
        }
        
        memo[n] = fib(n - 1) + fib(n - 2); 
    
        return memo[n]; 
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println("\nFib of " + n + " = " + fib(n));
        System.out.println("\nCounter: " + counter);
    }
}


