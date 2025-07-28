package DSA.Backtracking;


import java.util.List;
import java.util.ArrayList;

public class Permutation {

    // Scenario (i.e., input: [1, 2]):
    // Subset (all possible groups, any size, order doesn't matter): [], [1], [2], [1, 2]
    // Combination (choose k = 2 elements, order doesn't matter): [1, 2]
    // Permutation (all possible orderings, use all elements, NOT specify k): [1, 2], [2, 1]

    // Q: Given a distinct list of integers, return all possible distinct permutations of them.

    // Time: O(n^2 * n!)
    public static List<List<Integer>> permutationsRecursive(int[] nums) {
        return helper(0, nums);                              // Begin from the first position (index 0)
    }

    public static List<List<Integer>> helper(int i, int[] nums) {
        if (i == nums.length) {                                // Hit once 'per' recursion path
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());                        // Not a placeholder, build as recursion unwinds
            return res;
        }


        // Strategy:
        // Let's think of how the problem can be broken down into subproblems.
        // 1. We want all permutations of {1, 2, 3}
        // 2. We can generate permutations without including the 1. This would be {2, 3} and {3, 2}
        // 3. To include 1, we can then insert 1 at each index of {2, 3} and {3, 2}
        // 4. Resulted permutations would be {1, 2, 3}, {2, 1, 3}, {2, 3, 1} and {1, 3, 2}, {3, 1, 2}, {3, 2, 1}

        // The above approach can be applied recursively:
        // 1. Recursively call the 'helper' func to generate all permutations without including element at index i
        // 2. Then use nested loops to insert element at index i in to each position in each generated permutation 


        List<List<Integer>> resPerms = new ArrayList<>();      // Stores all permutations for the given 'nums'
        List<List<Integer>> perms = helper(i + 1, nums);       // perms contains all permutations after position i

        for (List<Integer> p : perms) {
            for (int j = 0; j < p.size() + 1; j++) {           // Both ends are included: 0(front) and size(end)
                List<Integer> pCopy = new ArrayList<>();       // Prep: inserts at EVERY possible position of pCopy
                pCopy.addAll(p);                               // 1st COPY current permutation
                pCopy.add(j, nums[i]);                         // 2nd Insert nums[i] at position 'j'
                resPerms.add(pCopy);                           // Add the new permutation to the result
            }
        }
        return resPerms; 
    }
    

    // Time: O(n^2 * n!)
    public static List<List<Integer>> permutationsIterative(int[] nums) {
        List<List<Integer>> perms = new ArrayList<>();         
        perms.add(new ArrayList<>());                          // Add the initial empty list '[[]]' as start point

        for (int n : nums) {                                   // Outer:  for each number in 'nums'
            List<List<Integer>> nextPerms = new ArrayList<>(); // Prepare to build the next set of permutations
            for (List<Integer> p : perms) {                    // Middle: for each existing permutation
                for (int i = 0; i < p.size() + 1; i++) {       // Inner:  for every possible position in this permu
                    List<Integer> pCopy = new ArrayList<>();   
                    pCopy.addAll(p);                           
                    pCopy.add(i, n);                           
                    nextPerms.add(pCopy);                      
                }  
            }
            perms = nextPerms;                                 // After finish all insertions, refresh 'perms'
        }
        return perms;
    }    
}


