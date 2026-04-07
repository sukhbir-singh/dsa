package algorithms.graph;

// Kahn's Algorithm
// Class that contains the topological sort logic
class Solution {

    // Function to perform BFS-based topological sort
    public int[] topologicalSort(int V, java.util.ArrayList<java.util.ArrayList<Integer>> adj) {
        // Create an array to store the in-degree of each vertex
        int[] indegree = new int[V];

        // Loop over all vertices to calculate in-degree
        for (int i = 0; i < V; i++) {
            // Loop over all adjacent vertices of current vertex
            for (int it : adj.get(i)) {
                // Increase in-degree of connected vertex
                indegree[it]++;
            }
        }

        // Create a queue to store vertices with in-degree zero
        java.util.Queue<Integer> q = new java.util.LinkedList<>();

        // Loop through all vertices
        for (int i = 0; i < V; i++) {
            // If in-degree is zero, add it to queue
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Array to store the topological order
        int[] topo = new int[V];
        // Index to track position in topo array
        int idx = 0;

        // Process vertices in queue
        while (!q.isEmpty()) {
            // Remove vertex from queue
            int node = q.poll();

            // Add it to the topological order
            topo[idx++] = node;

            // Loop through adjacent vertices of the current node
            for (int it : adj.get(node)) {
                // Reduce in-degree of connected vertex
                indegree[it]--;
                // If in-degree becomes zero, push it to queue
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }

        // Return the topological ordering
        return topo;
    }
}

// Driver class
public class TopoBFS {
    public static void main(String[] args) {
        // Number of vertices
        int V = 6;

        // Create adjacency list
        java.util.ArrayList<java.util.ArrayList<Integer>> adj = new java.util.ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new java.util.ArrayList<>());
        }

        // Adding edges
        adj.get(5).add(0);
        adj.get(5).add(2);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        // Create Solution object
        Solution obj = new Solution();

        // Call the function to get topological sort
        int[] ans = obj.topologicalSort(V, adj);

        // Print the result
        for (int val : ans) {
            System.out.print(val + " ");
        }
    }
}
