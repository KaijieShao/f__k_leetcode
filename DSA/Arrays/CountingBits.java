package DSA.Arrays;

// A bit is the most fundamental unit that a computer uses to represent data (1 byte = 8 bits)
// Each integer occupies 4 bytes of space, hence the addresses are 4 bytes apart
// Each character occupies 1 byte of space, hence the addresses are 1 byte apart

public class CountingBits {

    // Demonstrate how bitwise operators work -> Helps you understand how bits are manipulated at a low level

    public static void operations() {
        // AND
        int n = 1 & 1;       // Bitwise AND: requires both operands to be 1 for the result to be 1

        // OR 
        n = 1 | 0;           // Bitwise OR:  returns 1 if either bit is 1

        // XOR
        n = 0 ^ 1;           
        // XOR (^) compares each bit of two numbers:
        // If the bits are different at a position, XOR gives 1 for that bit.
        // If the bits are the same, XOR gives 0 for that bit.

        // NOT (negation)
        n = ~n;              // Bitwise NOT: turning 0s to 1s and 1s to 0s ('~' is bitwise NOT, '!' is logical NOT)
        
        // Bit Shifting
        n = 1;               // Set n to 1 (binary 0001)
        n = n << 1;          // Left shift:  0001 << 1 gives 0010 (multiplies by 2)
        n = n >> 1;          // Right shift: 0010 >> 1 gives 0001 (divides by 2)
       
        return;              // End of operations method
   }


   public static int countBits(int n) {
   // receives an integer 'n' and returns the number of 1-bits (set bits) in n, not n itself
   // For example, if n = 5 (binary 101), countBits(5) returns 2 (since there are two 1s in 101).

       int count = 0;              // Initialize counter for 1 bits
       while (n > 0) {             // Loop until all bits are traversed (n becomes 0)
           if ((n & 1) == 1) {     // Detects if the 'current' bit being checked and 'rightmost' bit both are '1'
               count++;            // If yes, increment the count
           }
           n = n >> 1;             // Shift bits right by 1 
       }
       return count;               // Return total count of 1 bits
   }
}



