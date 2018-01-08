package mi.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    public int V;   // No. of vertices
    public List<Integer>[] adj; //Adjacency List

    //Constructor
    public Graph(int v) {
        V = v;
        adj = new List[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    //Function to add an edge into the graph
    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    public Graph getTranspose() {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++) {
            for (int u : adj[v])
                g.adj[u].add(v);
        }
        return g;
    }

    private void dfs(int v, boolean[] visited){
        if (visited[v])
            return;
        visited[v] = true;
        System.out.print(v + " -> ");
        for (int i : adj[v]){
            dfs(i, visited);
        }
    }

    public void printDFS() {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]){
                dfs(i, visited);
                System.out.println();
            }
        }
    }
}

