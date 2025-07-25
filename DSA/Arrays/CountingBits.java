package DSA.Arrays;

// A 'bit' is the most fundamental unit (0 OR 1) that a computer uses to represent data (1 byte = 8 bits)
// Each 'integer' occupies 4 bytes of space, hence the addresses are 4 bytes apart
// Each 'character' occupies 1 byte of space, hence the addresses are 1 byte apart

public class CountingBits {

    public static void operations() {
    // Demonstrating how 'bitwise' operators work helps you understand how bits are manipulated at a low level 

        int n = 1 & 1;       // AND: requires both operands to be 1 for the result 'n' to be 1

        n = 1 | 0;           // OR: returns 1 if either bit is 1

        n = 0 ^ 1;           // XOR: n becomes 1, because 0 and 1 are different bits
                             //      ^ compares each bit of two numbers -> 0 for same, 1 for different

        n = ~n;              // NOT: turning 0 to 1 and 1 to 0 ('~' is bitwise NOT, '!' is logical NOT)
        
        n = 1;               // Sets n to 1 (0001 in binary)
        n = n << 1;          // Left shift: 0001 -> 0010 (multiplies by 2)
        n = n >> 1;          // Right shift: 0010 -> 0001 (divides by 2)
       
        return;              
   }


   public static int countBits(int n) {
   // Counts the number of 1s (set bits) in the binary representation of 'n'
   // i.e., if n = 5 (binary 101), countBits(5) returns 2 (since there are two 1s in 101)

       int count = 0;             
       while (n > 0) {             // This simply checks if 'n' is still greater than 0
       // The binary representation is used implicitly when you do bitwise operations like 'n & 1'
       // Java (and most languages) store numbers in binary internally, so you can work with bits directly

           if ((n & 1) == 1) {     // Only checks if the rightmost bit of 'n' is 1
               count++;            // If yes, increment the count
           }
           n = n >> 1;             // Shifts all bits in n one position to the right
       }
       return count;               
   }
}



