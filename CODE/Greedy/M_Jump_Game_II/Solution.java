package CODE.Greedy.M_Jump_Game_II;

// Find the minimum number of jumps needed to move from the first index to the last index of the array.
// - At each index i, you can jump up to nums[i] steps forward.
// - You want to reach the last index in as few jumps as possible.

public class Solution { 

    // Breadth First Search (Greedy)

    public int jump(int[] nums) {
        int res = 0, l = 0, r = 0;
        // res: counts the number of jumps needed to reach the end.
        // l:   left boundary of the current "level" (current range of indices).
        // r:   right boundary of the current "level" (max index reachable with res jumps so far).
        
        while (r < nums.length - 1) {
        // Controls the number of jumps ("levels").
        // Each iteration means taking one jump.
        // Continues until the current reachable range (r) covers the last index.

            int farthest = 0;
            // r: The right boundary of the current "level" (farthest index to reach with current number of jumps taken so far)
            // farthest: The farthest index you can reach with one more jump from anywhere in the current level

            // 'farthest' ensures you always pick the jump that gets you closest to the end.
            //            Without farthest, you wouldn’t know the safest/longest jump to take from the current set of indices.
            //            It enables a greedy approach, always planning the next jump to maximize your reach.

            for (int i = l; i <= r; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }
            // Scans all indices in the current jump range (from l to r).
            // For each index, calculates the farthest position reachable in the next jump.
            // Updates farthest with the max possible next reach.

            l = r + 1;
            // Move left boundary to one past the current right boundary (start of next "level").

            r = farthest;
            // Update right boundary to the farthest index reachable in the next jump.

            res++;
            // Increment the jump count, since we've made another jump to the next level.
        }
        return res;
        // Return the minimum number of jumps needed.
    }
}



