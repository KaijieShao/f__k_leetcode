package DSA.DPs;

public class Top_down {

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

// Fib of 7 = 13

// Counter: 13

// Fib of 20 = 6765

// Counter: 39


