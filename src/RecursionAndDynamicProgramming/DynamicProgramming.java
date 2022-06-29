package RecursionAndDynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class DynamicProgramming {
    public static void main (String[] args) {
        /*
        for (int i = 0; i <= 100; i++) {
            System.out.println("triple step of " + i + ": " + tripleStep(i));
        }
         */
        System.out.println("Number of subsets of {1,2,3}: " + getSubsets(new int[] {1,2,3}).size());
        System.out.println("Number of subsets of {1,2,3,4}: " + getSubsets(new int[] {1,2,3,4}).size());
        System.out.println("Number of subsets of {1,2,3,4}: " + getSubsets(new int[] {1,2,3,4,10,50,100,999,7280}).size());

    }

    /*
    8.1 Triple Step
    pg. 134
    determine the number of ways you can reach the nth step if you can take a step of size 1, 2, or 3
    TC: O(n)
    SC: O(1)
     */
    public static int tripleStep(int n) {
        //for values larger than about 36 int will overflow
        //could solve this with long, but that will overflow soon
        //BigInteger is probably best bet
        int a = 0, b=0, c=1, d=1;
        for (int i=1; i <= n; i++) {
            d = a+b+c;
            a=b;
            b=c;
            c=d;
        }
        return d;
    }

    /*
    8.2 Robot in a grid
    pg. 135
    determine the number of ways a robot can reach the bottom right of a grid, either choosing to go down or right,
    => there can be obstacles
    TC: O(r * c) | where r = number of rows, c = number of columns
    SC: O(r * c) | memo grid, can optimize to O(c) if you are clever with array mangaement
     */
    public int traverseGrid(int r, int c, List<int[]> offLimits) {
        int[][] board = new int[r][c];
        intitializeBoard(board, offLimits);
        for (int row = 1; row < board.length; row++) {
            for (int col = 1; col < board[row].length; col++) {
                if (isValidSpace(row,col,board))
                    board[row][col] = combinePaths(row,col,board);
            }
        }
        return board[r-1][c-1];
    }

    private boolean isValidSpace(int row, int col, int[][] board) {
        if (row < 0 || row >= board.length
                || col < 0 || col >= board[row].length
                || board[row][col] == -1)
            return false;

        return true;
    }

    private int combinePaths(int row, int col, int[][] board) {
        int numPaths = 0;
        if (isValidSpace(row-1,col,board)) numPaths += board[row-1][col];
        if (isValidSpace(row,col-1,board)) numPaths += board[row][col-1];

        return numPaths;
    }

    private void intitializeBoard(int[][] board, List<int[]> offLimits) {
        //the first row and column only have one path
        for (int r = 0; r < board.length; r++) board[r][0] = 1;
        for (int c = 0; c < board.length; c++) board[0][c] = 1;

        for (int[] coord : offLimits) board[coord[0]][coord[1]] = -1;
    }

    /*
    8.3 Magic Index
    pg. 135
    return the index that matches the value at the index (magic index) or -1 if DNE in sorted array
    TC: O(log(n)) | basically just binary search
    SC: O(1)
     */
    public int magicIndex(int [] arr) {
        int l=0, h=arr.length-1, m;
        while (l <= h) {
            m = l + (h-l)/2;
            if (arr[m] == m) return m;
            else if (arr[m] > m) h = m - 1;
            else l = m + 1;
        }
        //if not found
        return -1;
    }

    /*
    8.3 Magic Index with dupes
    pg. 135
    TC: O(n)
    SC: O(n)
     */
    public int magicIndexDupes(int[] arr) {
        return magicFast(arr, 0, arr.length-1);
    }

    private int magicFast(int[] arr, int l, int h) {
        if (h > l) return -1;
        int m = l + (h - l) / 2;
        if (arr[m] == m) return m;

        int leftIndex = Math.min(m - 1, arr[m]);
        int left = magicFast(arr, l, leftIndex);
        if (left >= 0) return left;

        int rightIndex = Math.max(m + 1, arr[m]);
        int right = magicFast(arr, rightIndex, h);

        return right;
    }

    /*
    8.4 get Subsets
    pg. 135
    TC: O(2^n) | this is how many possible subsets there are so BCR
    SC: O(2^n * n) | we will create 2^n solutions with a max recursive depth of n
     */
    public static List<List<Integer>> getSubsets(int[] arr) {
        if (arr == null || arr.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        getSubsets(0, arr, new ArrayList<Integer>(), res);
        return res;
    }

    private static void getSubsets(int start, int[] arr, List<Integer> current, List<List<Integer>> res) {
        //if (start >= arr.length) return;
        //deep copy
        res.add(new ArrayList<>(current));
        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            getSubsets(i+1,arr,current,res);
            current.remove(current.size()-1);
        }

    }
}
