package RecursionAndDynamicProgramming;

import java.util.List;

public class DynamicProgramming {
    public static void main (String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println("triple step of " + i + ": " + tripleStep(i));
        }
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
}
