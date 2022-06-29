package RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Recursion {

    /*
    8.5 Recursive multiply
    pg. 134
    multiple two numbers together in the least number of operations without *
    TC: O(log(s)) //where s is the smaller number
    SC: O(log(s)) //depth of recursive stack
     */
    public static int minProduct(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        return minProductHelper(smaller, bigger);
    }

    private static int minProductHelper(int smaller, int bigger) {
        if (smaller == 0) return 0;
        if (smaller == 1) return bigger;

        int s = smaller >> 1; //divide by 2
        int halfProd = minProductHelper(s, bigger);
        return (smaller % 2 == 0) ?
                halfProd + halfProd : //if even
                halfProd + halfProd + bigger; //if odd
    }

    /*
    8.7 Permutations without dupes
    pg. 135
    Get all the permutation of an array of ints
    TC: O(n!) | Since we have to calculate all permutation we will have to calculate n! perms
    SC: O(n! n) | Store n! results with a max call stack of n
     */
    public List<List<Integer>> getPermutations(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        getPermutationsHelper(new HashSet<>(), new ArrayList<>(), res, arr);
    }

    private void getPermutationsHelper(HashSet<Integer> set, ArrayList<Integer> curr,
                                       ArrayList<ArrayList<Integer>> res, int[] arr) {
        if (curr.size() == arr.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i=0; i<arr.length; i++) {
            if (set.contains(arr[i])) continue;
            set.add(arr[i]);
            curr.add(arr[i]);
            getPermutationsHelper(set,curr,res,arr);
            set.remove(arr[i]);
            curr.remove(arr[i]);
        }
    }
}
