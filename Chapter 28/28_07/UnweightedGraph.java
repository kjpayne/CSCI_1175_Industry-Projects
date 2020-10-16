/*
 * Author: Kaden Payne
 * Date: 10/15/2020
 * 
 * UnweightedGraph using methods from Graph
 */
import java.util.*;
/**
 *
 * @author kjpay
 * @param <V>
 */
public class UnweightedGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();
    
    //Constructors
    public UnweightedGraph() {
        
    }
    public UnweightedGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++) {
            addVertex(vertices[i]);
        }
        
        createAdjacencyLists(edges, vertices.length);
    }
    public UnweightedGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }
        
        createAdjacencyLists(edges, vertices.size());
    }
    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V)(new Integer(i)));
        }
        
        createAdjacencyLists(edges, numberOfVertices);
    }
    public UnweightedGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V)(new Integer(i)));
        }
        
        createAdjacencyLists(edges, numberOfVertices);
    }
    
    //Create abjacency lists
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
    }
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for (Edge edge: edges) {
            addEdge(edge.u, edge.v);
        }
    }
    
    //Returns size of vertices
    @Override
    public int getSize() {
        return vertices.size();
    }
    
    //Return vertices
    @Override
    public List<V> getVertices() {
        return vertices;
    }
    
    //Returns vertex
    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }
    
    //Returns index of vertex
    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }
    
    //Returns list of neighbors of vertex
    @Override
    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e: neighbors.get(index)) {
            result.add(e.v);
        }
        
        return result;
    }
    
    //Returns degree of vertex
    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }
    
    //Prints edges
    @Override
    public void printEdges() {
        for (int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e: neighbors.get(u)) {
                System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ") ");
            }
            System.out.println();
        }
    }
    
    //Clears graph
    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }
    
    //Adds vertex
    @Override
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        else {
            return false;
        }
    }
    
    //Adds edge
    public boolean addEdge(Edge e) {
        if (e.u < 0 || e.u > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.u);
        }
        
        if (e.v < 0 || e.v > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.v);
        }
        
        if (!neighbors.get(e.u).contains(e)) {
            neighbors.get(e.u).add(e);
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v));
    }
    
    //DFS tree
    @Override
    public SearchTree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        
        boolean[] isVisited = new boolean[vertices.size()];
        
        dfs(v, parent, searchOrder, isVisited);
        
        return new SearchTree(v, parent, searchOrder);
    }
    private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(v);
        isVisited[v] = true;
        
        for (Edge e : neighbors.get(v)) {
            if (!isVisited[e.v]) {
                parent[e.v] = v;
                dfs(e.v, parent, searchOrder, isVisited);
            }
        }
    }
    
    //BFS tree
    @Override
    public SearchTree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        
        java.util.LinkedList<Integer> queue = new java.util.LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v);
        isVisited[v] = true;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrder.add(u);
            for (Edge e: neighbors.get(u)) {
                if (!isVisited[e.v]) {
                    queue.offer(e.v);
                    parent[e.v] = u;
                    isVisited[e.v] = true;
                }
            }
        }
        
        return new SearchTree(v, parent, searchOrder);
    }
    
    //Returns a cycle
    public List<Integer> getACycle() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            list.add(i);
        }
        boolean[] isVisited = new boolean[vertices.size()];
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        while (list.size() > 0) {
            //int v = list.get(list.size() - 1);
            int v = list.get(0);
            //System.out.println("v = " + v);
            Stack<Integer> stack = new Stack<>();
            stack.push(v);
            isVisited[v] = true;
            list.remove(new Integer(v));
            while (!stack.isEmpty()) {
                int x = stack.peek();
                //System.out.println("x = " + x);
                if (neighbors.get(x).size() == 0) {
                    stack.pop();
                }
                else {
                    for (int i = neighbors.get(x).size() - 1; i >= 0; i--) {
                        /*System.out.println("size = " + neighbors.get(x).size());
                        System.out.println("size - 1 = " + (neighbors.get(x).size() - 1));
                        System.out.println("i = " + i);*/
                        Edge e = neighbors.get(x).get(i);
                        System.out.println("e.v = " + e.v);
                        if (!isVisited[e.v]) {
                            System.out.println("First if");
                            parent[e.v] = x;
                            stack.push(e.v);
                            isVisited[e.v] = true;
                            //System.out.println("list.size before = " + list.size());
                            list.remove(new Integer(e.v));
                            //System.out.println("list.size after = " + list.size());
                            neighbors.get(x).remove(i);
                            break;
                        }
                        else if (parent[x] != e.v) {
                            System.out.println("Second if");
                            List<Integer> list1 = new ArrayList<>();
                            list1.add(e.v);
                            while (x != e.v && x != -1) {
                                //System.out.println("x = " + x);
                                list1.add(x);
                                x = parent[x];
                            }
                            return list1;
                        }
                        else {
                            System.out.println("Else");
                            neighbors.get(x).remove(i);
                        }
                    }
                }
            }
        }
        return list;
    }
    
    //Inner Class SearchTree
    public class SearchTree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;
        
        //Constructor
        public SearchTree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }
        
        //Returns root
        public int getRoot() {
            return root;
        }
        
        //Returns parent of vertex
        public int getParent(int v) {
            return parent[v];
        }
        
        //Returns list of search order
        public List<Integer> getSearchOrder() {
            return searchOrder;
        }
        
        //Returns number of vertices
        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }
        
        //Returns path from root to vertex
        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();
            
            do {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);
            
            return path;
        }
        
        //Prints path
        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }
}

class Edge {
    int u;
    int v;
    
    //Constructor
    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
    
    @Override
    public boolean equals(Object o) {
        return u == ((Edge)o).u && v == ((Edge)o).v;
    }
}
