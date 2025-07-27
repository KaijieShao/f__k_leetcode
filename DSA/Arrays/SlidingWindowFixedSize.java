package DSA.Arrays;


import java.util.HashSet;

public class SlidingWindowFixedSize {

    // Scenario:
    // Detect if any two equal elements exist within a fixed sliding window of size 'k' in an array.
    // Formally: check if there are indices i and j such that nums[i] = nums[j] and |i - j| ≤ k

    public static boolean closeDuplicatesBruteForce(int[] nums, int k) { // Brute-force: O(n * k)
        for (int L = 0; L < nums.length; L++) {                          // Loop over all possible start indices
            for (int R = L + 1; R < Math.min(nums.length, L + k); R++) { // L + 1: Avoid comparing same element 
            // 1. Stops before it reaches the lesser of 'end of the array'
            // 2. OR 'L + K' might be beyond the array's end, so stop R at whichever comes first

                if (nums[L] == nums[R]) {                                
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean closeDuplicates(int[] nums, int k) {  // Sliding Window: O(n)
        HashSet<Integer> window = new HashSet<>();              // Stores elements WITHIN the current window
        int L = 0;                                              

        for (int R = 0; R < nums.length; R++) {                
            if (R - L + 1 > k) {                                // Window size > maxSize 'k'
                window.remove(nums[L]);                         // Remove far-left (oldest) element from window
                L++;                                            
            }
            if (window.contains(nums[R])) {                     // FIRST check if right pointer ALREADY exists
                return true;                                    
            }
            window.add(nums[R]);                                // Add to HashSet for future checks
        }
        return false;                                           // Loop finished without finding duplicates 
    }
}


