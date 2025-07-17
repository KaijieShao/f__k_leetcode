package CODE.Greedy.M_Merge_Triplets_to_Form_Target;

// Given a list of integer 'triplets' and a 'target' triplet (FYI: triplets[i] = [ai, bi, ci])
// You can repeatedly merge any two different triplets by taking the element-wise max and updating one of them.
// Can you perform these merges so that at least one of the triplets in your list becomes identical to the 'target' triplet?
// - Return true if possible, otherwise false.

public class Solution {

    // Greedy (Optimal)

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean x = false, y = false, z = false;
        // 'x', 'y', 'z' are boolean flags
        // All three must be true to guarantee you can merge triplets to reach the target

        // You can only merge by taking element-wise max, so you can’t reduce any value in a triplet.
        // To form the target, you need:
        // At least one triplet that can provide each exact value in each position of the target (without exceed other position)

        // The code does not require a single triplet to match the entire target.
        // Instead:
        // x checks if any triplet can supply target[0] in the first position  (with other positions not exceeding target).
        // y checks if any triplet can supply target[1] in the second position (with other positions not exceeding target).
        // z checks if any triplet can supply target[2] in the third position  (with other positions not exceeding target).

        // If all three flags are true, it means there are triplets to form target by merging

        for (int[] t : triplets) {
            x |= (t[0] == target[0] && t[1] <= target[1] && t[2] <= target[2]);
            // x |= ( ... ) is a bitwise OR assignment -> Equivalent to: x = x || ( ... ) for booleans in Java
            //              First element matches target[0]
            //              Second and third elements are ≤ corresponding targets

            y |= (t[0] <= target[0] && t[1] == target[1] && t[2] <= target[2]);
            z |= (t[0] <= target[0] && t[1] <= target[1] && t[2] == target[2]);

            if (x && y && z) {
                return true;
            }
            // If all three flags are true, it means there are triplets to form target by merging
        }
        return false;
        // If loop completes and not all flags are true, return false (not possible)
    }
}

