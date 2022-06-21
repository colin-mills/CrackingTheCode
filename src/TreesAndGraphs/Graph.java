package TreesAndGraphs;

import java.util.HashSet;
import java.util.List;

public class Graph {
    /**********************
     * Private sub-classes
     **********************/
    private static class Node {
        List<Node> neighbors;

        Node (List<Node> neighbors) {
            this.neighbors = neighbors;
        }
    }
    /*
    4.1 Route Between Node
    pg. 109
    Write algorithm to determine if there is a path between a and b
    TC: O(n)
    SC: O(n) // because of visited HashSet
     */
    public boolean routeExists(Node a, Node b) {
        if (a == null || b == null) return false;
        return isValidPath(a,b, new HashSet<>());
    }

    private boolean isValidPath(Node a, Node b, HashSet<Node> nodeHashSet) {
        if (a == null || nodeHashSet.contains(a)) return false;
        if (a == b) return true;
        boolean foundPath;

        for (Node next: a.neighbors) {
            foundPath = isValidPath(next,b,nodeHashSet);
            if (foundPath) return true;
        }
        //if not found
        return false;
    }
}
