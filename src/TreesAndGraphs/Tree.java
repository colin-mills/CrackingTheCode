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
}
