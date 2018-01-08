package mi;

import mi.utilities.Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

// This class represents a directed graph using adjacency list
// representation
public class Find_Strongly_Connected_Graph_Components {
    // A recursive function to print DFS starting from v
    private static void DFSUtil(Graph g, int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int n : g.adj[v])
            if (!visited[n])
                DFSUtil(g, n, visited);
    }

    private static void fillOrder(Graph g, int v, boolean visited[], Stack stack) {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (int n : g.adj[v])
            if (!visited[n])
                fillOrder(g, n, visited, stack);

        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(v);
    }

    // The main function that finds and prints all strongly
    // connected components
    private static void printSCCs(Graph g) {
        Stack stack = new Stack();

        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[g.V];

        // Fill vertices in stack according to their finishing
        // times
        for (int i = 0; i < g.V; i++)
            if (visited[i] == false)
                fillOrder(g, i, visited, stack);

        // Create a reversed graph
        g = g.getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < g.V; i++)
            visited[i] = false;

        // Now process all vertices in order defined by Stack
        while (!stack.empty()) {
            // Pop a vertex from stack
            int v = (int) stack.pop();

            // Print Strongly connected component of the popped vertex
            if (!visited[v]) {
                DFSUtil(g, v, visited);
                System.out.println();
            }
        }
    }

    // Driver method
    public static void main(String args[]) {
        // Create a graph given in the above diagram
        Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.printDFS();

        System.out.println("Following are strongly connected components " +
                "in given graph ");
        printSCCs(g);
    }
}
// This code is contributed by Aakash Hasija