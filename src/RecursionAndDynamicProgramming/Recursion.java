package RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.RandomAccess;

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
    public ArrayList<ArrayList<Integer>> getPermutations(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        getPermutationsHelper(new HashSet<>(), new ArrayList<>(), res, arr);
        return res;
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

    /*
    8.8 Permutations with dupes
    pg. 135
    Get all the permutation of an array of ints when there are dupes
    TC: O(n!) | Since we have to calculate all permutation we will have to calculate n! perms
    SC: O(n! n) | Store n! results with a max call stack of n
     */
    public ArrayList<ArrayList<Integer>> getPermutationsDupes(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        getPermutationsHelperDupes(new boolean[arr.length], new ArrayList<>(), res, arr);
        return res;
    }

    private void getPermutationsHelperDupes(boolean[] visited, ArrayList<Integer> curr,
                                       ArrayList<ArrayList<Integer>> res, int[] arr) {
        if (curr.size() == arr.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i=0; i <arr.length; i++) {
            if (visited[i] || i > 0 && arr[i-1] == arr[i] && !visited[i-1]) continue;
            curr.add(arr[i]);
            visited[i] = true;
            getPermutationsHelperDupes(visited,curr,res,arr);
            visited[i] = false;
            curr.remove(arr[i]);
        }
    }

    /*
    8.9 Parens
    pg. 136
    Get all the valid parenthesis for n sets
    TC: O(2^n-1)
    SC: O(2^n-1)
     */
    public ArrayList<String> getParens(int n) {
        if (n==0) return new ArrayList<String>();
        ArrayList<String> res = new ArrayList<>();
        getParens(n,0,res,new StringBuilder());
        return res;
    }

    private void getParens(int n, int balance, List<String> res, StringBuilder curr) {
        if (balance < 0 && balance > n*2) return;
        if (balance == 0 && curr.length() == n*2) {
            res.add(curr.toString());
            return;
        }
        char[] validOptions = new char[] {'(',')'};
        for (char ch : validOptions) {
            int weight = ch == '(' ? 1 : -1;
            curr.append(ch);
            getParens(n,balance + weight, res, curr);
            curr.deleteCharAt(curr.length()-1);
        }
    }

    /*
    8.12 Eight Queens
    pg. 136
    Get all the valid solutions for placing 8 queens on the board
    TC: technically constant since there is no variable n that is changing
    SC: technically constant since there is no variable n that is changing
     */
    public ArrayList<Integer[]> eightQueens() {
        ArrayList<Integer[]> res = new ArrayList<>();
        placeQueens(0, new Integer[8], res);
        return res;
    }

    private void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> res) {
        if (row == columns.length) {
            res.add(columns.clone());
            return;
        }
        for (int col = 0; col < columns.length; col++) {
            if (isValid(columns,row,col)) {
                columns[row] = col;
                placeQueens(row + 1, columns, res);
            }
        }

    }

    private boolean isValid(Integer[] columns, int row, int col) {
        for (int row2 =0; row2<row; row2++) {
            int column2 = columns[row2];
            //noq check if this can't work

        }
    }
}
