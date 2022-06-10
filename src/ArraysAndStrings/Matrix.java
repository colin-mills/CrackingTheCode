/*
1.7 Rotate Matrix
1.8 Zero Matrix
pg. 91
 */

package ArraysAndStrings;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    //TC: O(n) //we might have to touch every element
    //SC: O(n^1/2) //our arrays could have n^1/2 elements if every row or column has a zero
    public static void zeroMatrix(int[][] box) {
        List<Integer> rows = new ArrayList<> ();
        List<Integer> cols = new ArrayList<> ();

        for(int i=0; i < box.length; i++) {
            for (int j=0; j < box[i].length; j++) {
                if (box[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        if (rows.size() == box.length || cols.size() == box[0].length) {
            box = new int[box.length][box[0].length];
            return;
            //early exit
            //This should be slightly faster than going through each row and column more than once
        }
        for (int row: rows) {
            for (int j=0; j < box[row].length; j++) box[row][j] = 0;
        }
        for (int col: cols) {
            for (int i=0; i < box.length; i++) box[i][col] = 0;
        }
    }

    //TC: O(n) -- we will touch every element or almost every
    //SC: O(1)
    public static void rotateMatrix(int[][] box) {
        int layer = 0;
        if (box == null || box.length < 2) return; //early exit

        while (layer < box.length/2) {
            //for each layer
            rotateLayer(layer, box);
            layer++;
        }
    }
    private static void rotateLayer (int layer, int[][] box) {
        int[] right = new int [] {layer,layer};
        int[] down = new int [] {layer, box[layer].length - (layer + 1)};
        int [] left = new int [] {box.length - (layer + 1), box[layer].length - (layer + 1)};
        int [] up = new int [] {box.length - (layer + 1), layer};

        while (right[1] < box.length - layer) {
            //for each value in this layer
            swap(right,down,box);
            swap(right,left,box);
            swap(right,up,box);
            //now we increment each array in this layer
            right[1]++;
            down[0]++;
            left[1]--;
            up[0]--;
        }
    }

    private static void swap (int[] coord1, int[] coord2, int[][] box) {
        int temp = box[coord2[0]][coord2[1]];
        box[coord2[0]][coord2[1]] = box[coord1[0]][coord1[1]];
        box[coord1[0]][coord1[1]] = temp;
    }
}
