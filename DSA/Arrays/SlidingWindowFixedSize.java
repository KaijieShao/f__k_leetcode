package DSA.Arrays;

import java.util.HashSet;

// Sliding Window Fixed Size: query all contiguous subarrays (or "windows") of a fixed length in an array/string.

public class SlidingWindowFixedSize {
    
    // Brute-force approach
    public static boolean closeDuplicatesBruteForce(int[] nums, int k) {
        // 'nums': an array of integers
        // 'k':    a distance
        //  Returns true if there are two identical numbers within k indices of each other.
    
        for (int L = 0; L < nums.length; L++) {
        // Outer: iterates through each element of the array using an index 'L' (representing the left side of our comparison).
    
            for (int R = L + 1; R < Math.min(nums.length, L + k); R++) {
            // Inner: iterates through the elements to the right of 'L'.
            // It starts at 'L + 1' to avoid comparing an element with itself.

            // 'R < Math.min(nums.length, L + k)' : stops before it reaches the lesser of
            // 1. the end of the array 
            // 2. L + K might be beyond the array's end, so stop R at whichever comes first.
    
                if (nums[L] == nums[R]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Sliding Window Approach
    public static boolean closeDuplicates(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();
        // A HashSet stores the elements within our current "window."
        // It's used because it offers very fast (O(1) average time) checks to see if an element already exists.
    
        int L = 0;
        // Initializes the left pointer 'L' of our sliding window to the start of the array.
    
        for (int R = 0; R < nums.length; R++) {
            if (R - L + 1 > k) {
            // This checks if the current window size (calculated as R - L + 1) has exceeded the max size 'k'.
    
                window.remove(nums[L]);
                // If the window is too large, we remove the element at the far left of the window from our HashSet.
    
                L++;
            }

            if (window.contains(nums[R])) {
            // Before add a new element, this checks if the element at the right pointer ('nums[R]') is already in our HashSet.
    
                return true;
                // If it is, we've found a duplicate within the 'k'-sized window, so we immediately return true.
            }
            window.add(nums[R]);
            // If no duplicate was found, the current element is added to the HashSet for future checks.
        }
        return false;
        // If the loop finishes without finding any duplicates in any window, we can be sure none exist, and we return false.
    }
}

