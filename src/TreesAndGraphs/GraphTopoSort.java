package TreesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;

/*
    4.7 Build Order
    pg. 110
    Given a list of projects and their dependencies, output a list of order of projects, if it is possible
    TC: O(P + D) | where P is the number of projects and D is the number of dependency pairs
    SC: O(P) | we must create a graph of size P
     */
public class GraphTopoSort {
    /**********************
     * Private subclasses
     **********************/
    private class Project {
        String name;

        Project(String name) {
            this.name = name;
        }
    }
    private class graph {
        //list of vertices in the graph
        private ArrayList<Project> nodes = new ArrayList<>();
        //hash map for constant time retrieval of nodes by name
        private HashMap<String,Project> map = new HashMap<>();

        public Project getOrCreateNode(String name) {
            if (!map.containsKey(name)) {
                //create node
                Project node = new Project(name);
                nodes.add(node);
                map.put(name,node);
            }
            return map.get(name);
        }


    }
}
