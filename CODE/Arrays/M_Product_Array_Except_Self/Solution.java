package CODE.Arrays.M_Product_Array_Except_Self;

public class Solution {
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;    
        // Get the length of the input array and stores it in the variable n
        int[] res = new int[n]; 
        // Create a static integer array with n as its capacity (length)

        res[0] = 1;
        // If you used 0, all products would become 0, which is incorrect for this problem        

        // First loop: left to right, builds prefix products
        for (int i = 1; i < n; i++) { 
        // Starts at i = 1 because index 0 = 1, which already has no left-side elements
            res[i] = res[i - 1] * nums[i - 1]; 
            // res[i-1] is the prefix product so far (product of all elements left of i-1)
            // nums[i-1] is the element just before the current i
            // Multiplying them gives the product of everything to the left of i
        }

        // After the first loop:
        // 'nums' contains the original numbers (input array).
        // 'res' contains the prefix products (product of all elements to the left of index i)

        // Prefix handles elements before i.
        // Postfix handles elements after i.
        // Multiplying both gives the product of all elements except nums[i] as required by the problem

        int postfix = 1;
        // Handles the rightmost boundary, just as res[0] = 1 handles the leftmost boundary

        // Second loop: right to left, multiplies prefix by postfix products
        for (int i = n - 1; i >= 0; i--) {
        // Visit every index from the end to the start, so that every res[i] is updated with correct postfix product

            res[i] *= postfix;    
            // res[i] = (product of all elements left of i) * (product of all elements right of i)            
            postfix *= nums[i];  
             // Update postfix so it’s ready for the next index (to the left) in the loop
        }

        return res;
        // Product of all elements except nums[i], is stored back into res[i]        
    }
}
