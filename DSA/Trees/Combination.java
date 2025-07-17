package DSA.Trees;

import java.util.List;
import java.util.ArrayList;

public class Combination {

    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> combs = new ArrayList<>();
        helper(1, new ArrayList<>(), combs, n, k);
        return combs;
    }

    public static void helper(int i, List<Integer> curComb, List<List<Integer>> combs, int n, int k) {
        if (curComb.size() == k) {
            combs.add(new ArrayList<>(curComb));
            return;
        }
        if (i > n) {
            return;
        }
        curComb.add(i);
        helper(i + 1, curComb, combs, n, k);

        curComb.remove(curComb.size() - 1);
        helper(i + 1, curComb, combs, n, k);
    }

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
        for (int j = i; j < n + 1; j++) {
            curComb.add(j);
            helper2(j + 1, curComb, combs, n, k);
            curComb.remove(curComb.size() -1);
        }
    }
}
