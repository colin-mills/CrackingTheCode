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
        private String name;
        private ArrayList<Project> children = new ArrayList<>();
        private HashMap<String, Project> map = new HashMap<>();
        private int dependencies = 0;

        Project(String name) {
            this.name = name;
        }

        public void addNeighbor(Project node) {
            if (!map.containsKey(node.getName())) {
                children.add(node);
                map.put(node.getName(), node);
                node.incrementDependencies();
            }
        }

        public void incrementDependencies() { dependencies++; }
        public void decrementDependencies() { dependencies--; }

        public String getName() { return name; }
        public ArrayList<Project> getChildren() { return children; }
        public int getNumberDependencies() { return dependencies; }

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

        public void addEdge(String startName, String endName) {
            Project start = getOrCreateNode(startName);
            Project end = getOrCreateNode(endName);
            start.addNeighbor(end);
        }

        public ArrayList<Project> getNodes() { return nodes; }
    }
}
