/*
 * Author: Kaden Payne
 * Date: 10/14/2020
 * 
 * UnwieghtedGraph that implements Graph methods
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
        for (int i = 0; i < numberOfVertices; i++)  {
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
    
    //DFS nonrecurs method
    public SearchTree dfsNonRecurs(int v) {
        //System.out.println("v = " + v);
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        boolean[] isVisited = new boolean[vertices.size()];
        List<Integer> searchOrder = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        
        stack.push(v);
        searchOrder.add(v);
        isVisited[v] = true;
        
        while (!stack.isEmpty()) {
            int x = stack.peek();
            //System.out.println("x = " + x);
            if (neighbors.get(x).size() == 0) {
                stack.pop();
            }
            else {
                for (int i = neighbors.get(x).size() - 1; i >= 0; i--) {
                    //System.out.println("i = " + i);
                    Edge e = neighbors.get(x).get(i);
                    //System.out.println("e = " + e);
                    neighbors.get(x).remove(i);
                    
                    if (!isVisited[e.v]) {
                        parent[e.v] = x;
                        stack.push(e.v);
                        isVisited[e.v] = true;
                        searchOrder.add(e.v);
                        break;
                    }
                }
            }
        }
        
        return new SearchTree(v, parent, searchOrder);
    }
    
    //Crating adjacency list
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
    
    //Getting size of graph
    @Override
    public int getSize() {
        return vertices.size();
    }
    
    //Returning a list of vertices
    @Override
    public List<V> getVertices() {
        return vertices;
    }
    
    //Returning specified vertex
    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }
    
    //Returning index of specifiied vertex
    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }
    
    //Returning neighbors of specified vertex
    @Override
    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for (Edge e: neighbors.get(index)) {
            result.add(e.v);
        }
        
        return result;
    }
    
    //Returning degree of specified vertex
    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }
    
    //Printing edges of graph
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
    
    //Clearing graph
    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }
    
    //Adding vertex
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
    
    //Adding edge
    @Override
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
    
    //DFS
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
    
    //Inner class search tree
    public class SearchTree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;
        
        //Constructors
        public SearchTree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }
        
        //Returning root
        public int getRoot() {
            return root;
        }
        
        //Returning parent
        public int getParent(int v) {
            return parent[v];
        }
        
        //Returning searchorder
        public List<Integer> getSearchOrder() {
            return searchOrder;
        }
        
        //Returning number of vertices
        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }
        
        //Retuning path from root to vertices
        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();
            
            do {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);
            
            return path;
        }
        
        //Printing path
        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " + vertices.get(index) + ": ");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
        }
        
        //Printing tree
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
