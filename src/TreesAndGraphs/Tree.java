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
        int value;
        Node left = null;
        Node right = null;
        Node parent = null;

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

    /*
    4.5 Validate BST
    pg. 110
    check if a tree is BST
    TC: O(n)
    SC: O(log(n)) //max recursive stack depth
     */
    public boolean isBST(Node root) {
        if (root == null) return false;
        return isBST(root, new int[] {Integer.MIN_VALUE});
    }

    private boolean isBST(Node root, int[] last) {
        if (root == null) return true;
        if (!isBST(root.left,last) || last[0] < root.value) return false;
        //in-order traversal
        last[0] = root.value;
        return isBST(root.right, last);
    }

    //This is a bit cleaner approach to 4.5
    //and lets us have duplicates
    public boolean isBSTMinMaxMethod(Node root) {
        if (root == null) return false;
        return isBST(root, null, null);
    }

    private boolean isBST(Node root, Integer min, Integer max) {
        if (root == null) return true;

        //recurse down left path
        if (!isBST(root.left, min, root.value)) return false;

        //validate current node
        //if min is initialized and is not greater than or equal to min (can be the same as min)
        if (min != null && root.value < min) return false;
        //if max is initialized and is not less than max
        if (max != null && root.value >= max) return false;

        //recurse down right path
        if (!isBST(root.right, root.value, max)) return false;

        //we passed the gauntlet
        return true;
    }
    /*
    4.6 Successor
    pg. 110
    Get the next node in a BST
    TC: O(n) //worse case is last node, which must traverse up entire tree,
        //since there is no guarantee the tree is balanced O(n)
    SC: O(1)
     */
    public Node getNext(Node root) {
        //base case
        if (root == null) return null;

        if (root.right != null) {
            //case 1, there's a right branch
            root = root.right;
            while (root.left != null) root = root.left;
        } else {
            //case 2, return the parent of the last left branch
            while (root.parent != null && root.parent.left != root) root = root.parent;
            //now get the parent
            root = root.parent;
        }
        return root;
    }

    /*
    4.8 Common Ancestor
    pg. 110
    Get the common ancestor of two nodes in a tree
    TC: O(log(n)) //worse case is they both meet at root which would be 4 * log(n)
        //technically it could be  O(n) since there is no guarantee the tree is balanced O(n)
    SC: O(1)
     */
    public Node getCommonAncestor(Node one, Node two) {
        if (one == null || two == null) return null;
        Node current = one;
        int depthOne = 0, depthTwo = 0;
        //find the depth of one
        while (one.parent != null) {
            //early exit if we happen to run into two on the way up
            if (current == two) return current;
            current = current.parent;
            depthOne++;
        }
        current = two;
        while (two.parent != null) {
            //early exit if we happen to run into one on the way up
            //possible this didn't happen in the first loop if one is above 2
            if (current == one) return current;
            current = current.parent;
            depthTwo++;
        }

        while (one != two && depthOne >= 0 && depthTwo >= 0) {
            if (depthOne > depthTwo) {
                one = one.parent;
                depthOne--;
            } else if (depthTwo > depthOne) {
                two = two.parent;
                depthTwo--;
            } else {
                one = one.parent;
                two = two.parent;
            }
        }
        return one;
    }

    /*
    4.9 BST Sequences
    pg. 110
    Get the possible arrays that could have built a given BST
    TC: TBD
    SC: TBD when I understand this problem more
     */
    public ArrayList<LinkedList<Integer>> allSequences(Node node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        if (node == null) {
            //edge case null tree
            result.add(new LinkedList<>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.value);

        //recurse on left and right subtrees
        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        //weave them together on the way up
        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveLists(left,right,weaved,prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    //weave the list together in all the possible ways
    //This works by removing the head from one list, recursing, and then doing the same thing with the other list
    void weaveLists(LinkedList<Integer> left, LinkedList<Integer> right,
                    ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        //if one list is empty add the rest to a cloned prefix for a deep copy
    }
}
