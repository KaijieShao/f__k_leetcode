package DSA.DPs;

public class Bottom_Up {
    static int counter = 0;

    public static int fib(int n) { 
            int[] fibList = new int[n + 1]; 
            fibList[0] = 0; 
    
            if (n > 0) 
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

// Fib of 7 = 13
// Counter: 6
// Fib of 20 = 6765
// Counter: 19


