package DSA.Arrays;

public class BigO {
    public static void nSquared(int n) { // O(n^2)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i + " " + j);
            }
        }
    }
    // i.e., 00, 01, 02 ... 11, 12, 13 ... 97, 98, 99


    public static void linear(int n) { // O(n)
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }
    }
    // i.e., 0, 1, 2, 3, 4, 5, 6, 7, 8, 9


    public static void divideAndConquer() { // O(log n)
        int n = 100;
        int left = 0;
        int right = n - 1;
        int steps = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            System.out.println("Step " + (steps + 1) + ": mid = " + mid);
            left = mid + 1;
            steps++;
        }
        System.out.println("Total steps: " + steps);
    }
    // i.e., Step 1: mid = 49 ... Step 6: mid = 98 -> Total steps = 6
    

    public static int one(int j) { // O(1)
        return j + j;
    }
    // There is only 1 operation performed, regardless of whether n is 10 or a billion


    public static void dropConstant(int n) { // O(2n) -> O(n)
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }
        for (int j = 0; j < n; j++) {
            System.out.println(j);
        }
    }
    // What matters for efficiency is the 'rate', NOT the constant


    public static void dropNonDominant(int n) { // O(n^2 + n) -> O(n^2)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i + " " + j);
            }
        }
        for (int k = 0; k < n; k++) {
            System.out.println(k);
        }
    }
    // As the input size 'n' increases, the n^2 grows significantly faster -> n becomes insignificant


    public static void differentTerms(int a, int b) { 
        for (int i = 0; i < a; i++) {
            System.out.println(i);
        }
        for (int j = 0; j < b; j++) {
            System.out.println(j);
        }
        // O(a + b)

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                System.out.println(i + " " + j);
            }
        }
        // O(a * b)
    }
}

