package DSA.Backtracking;


import java.util.List;
import java.util.ArrayList;

public class Combination {

    // Scenario:
    // Solve problem of counting all distinct groups of a specified size from a set, ignoring order of selection
    // Subsets get you all possible sizes (from empty up to n), but Combinations require you to specify the size k
    // In Permutations, order matters (“ABC” ≠ “BAC”); In Combinations, order does NOT matter (“ABC” = “BAC”)

    // Q: Return all possible combinations of size = k, choosing from values between 1 and n.

    // Trivial: O(k * 2^n)
    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> combs = new ArrayList<>();
        helper(1, new ArrayList<>(), combs, n, k);      // 1 to n (inclusive), new Empty list of each combination
        return combs;
    }

    public static void helper(int i, List<Integer> curComb, List<List<Integer>> combs, int n, int k) {
        if (curComb.size() == k) {
            combs.add(new ArrayList<>(curComb));          // Size reaches k -> add a copy to result
            return;
        }
        if (i > n) {
            return;                                       // Out of bounds (no more numbers to use, recursion ends)
        }
        curComb.add(i);                                   // With it
        helper(i + 1, curComb, combs, n, k);

        curComb.remove(curComb.size() - 1);               // Without it
        helper(i + 1, curComb, combs, n, k);
    }


    // Optimal: O(k * C(n, k))
    public static List<List<Integer>> combinations2(int n, int k) {
        List<List<Integer>> combs = new ArrayList<>();
        helper2(1, new ArrayList<>(), combs, n, k);
        return combs;
    }

    public static void helper2(int i, List<Integer> curComb, List<List<Integer>> combs, int n, int k) {
        if (curComb.size() == k) {
            combs.add(new ArrayList<>(curComb));
            return;
        }
        if (i > n) {
            return;
        }
        for (int j = i; j < n + 1; j++) {                 // Try every possible next numbers from 'i' to 'n'
            curComb.add(j);                               // Include number 'j' in the current combination
            helper2(j + 1, curComb, combs, n, k);         // Explores all possible next number for curComb
            curComb.remove(curComb.size() -1);            // Backtrack: remove 'j' and try next
        }
    }
}

