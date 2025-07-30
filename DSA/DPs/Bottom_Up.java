package DSA.DPs;


public class Bottom_Up {

    // Scenario:
    // Root problem requirements (for any DPs): 1) Overlapping subproblems, 2) Optimal substructure
    // Top-Down (Memoization): Solve big problem first, break into subproblems recursively, store results
    // Bottom-Up (Tabulation): Solve smallest subproblems first, iteratively build up solutions to bigger problems

    static int counter = 0;

    // O(n - 1) = O(n) time
    public static int fib(int n) { 
        int[] fibList = new int[n + 1]; 
        fibList[0] = 0; 
        fibList[1] = 1; 
    
        for (int index = 2; index <= n; index++) { 
            counter++; 
            fibList[index] = fibList[index - 1] + fibList[index - 2]; 
        }
        return fibList[n]; 
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println("\nFib of " + n + " = " + fib(n));
        System.out.println("\nCounter: " + counter);
    }
}

