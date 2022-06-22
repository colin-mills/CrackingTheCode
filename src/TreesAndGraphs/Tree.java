package TreesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree <T> {

    /**********************
     * Private sub-classes
     **********************/
    private static class Node<T> {
        T val;
        Node left = null;
        Node right = null;

        Node (T val) {
            this.val = val;
        }
    }
    /*
    4.2 Minimal Tree
    pg. 109
    create a binary search tree with minimal height from sorted array
    TC: O(n) //BCR because we have to touch every node
    SC: O(log(n)) // max height of recursive stack
     */
    public Node constructFromSortedArray(T[] arr) {
        if (arr == null) return null;
        Node root = getNextNode(0, arr.length-1, arr);
        return root;
    }

    private Node getNextNode(int l, int h, T[] arr) {
        if (l>h) return null; //base case
        int mid = l + (h-l) / 2; //solving for mid this way avoids overflow
        Node root = new Node(arr[mid]);
        root.left = getNextNode(l, mid-1, arr);
        root.right = getNextNode(mid+1, h, arr);
        return root;
    }

    /*
    4.3 List of Depths
    pg. 109
    create a List of Lists, where each sublist is one layer of the tree
    TC: O(n) //BCR because we have to touch every node
    SC: O(n) // max height of recursive stack is log(n) but we return n values
     */
    public List<List<Node>> getDepthLists(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        List<List<Node>> res = new ArrayList<>();
        queue.offer(root);
        Node current = null;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Node> list = new ArrayList<>();

            for (int i=0; i<size; size++) {
                current = queue.poll();
                list.add(current);
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
            res.add(list);
        }
        return res;
    }

    /*
    4.4 Check Balanced
    pg. 109
    check if a tree is balanced, where unbalanced is defined as any subtree depth differing by more than 1
    TC: O(n) //BCR because we have to touch every node
    SC: O(1)
     */
    public boolean isBalanced(Node root) {
        //technically a null tree is balanced
        //a null tree is defined to have a depth of -1, and is the only subtree
        // so there cannot be a difference of more than 1
        if (root == null) return true; //base case
        int minMax[] = new int[] {-1,-1};
        traverseTree(root,0,minMax);
        return getBalance(minMax) < 2;
    }

    private int getBalance(int[] minMax) {
        return minMax[1] - minMax[0];
    }

    private void traverseTree(Node root, int depth, int[] minMax) {
        if (root == null) {
            //otherwise we will never initialize min
            if (minMax[0] == -1) minMax[0] = depth;

            //set new max or min
            if (depth < minMax[0]) minMax[0] = depth;
            else if (depth > minMax[1]) minMax[1] = depth;
            return;
        }
        //early exit
        if(minMax[0] != -1 && minMax[1] != -1 && getBalance(minMax) > 1) return;

        //otherwise we traverse
        //pre-order since we evaluated the root first
        traverseTree(root.left, depth+1, minMax);
        traverseTree(root.right, depth+1,minMax);
    }
}
