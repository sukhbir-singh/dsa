package algorithms.graph;

import java.util.*;

// Using Kahn's algo
public class CycleDetectionDirectedBFS {
    
    // Function to detect cycle in a directed graph using Kahn's Algorithm
    public static boolean isCyclic(int vertices, List<List<Integer>> adj) {
        int[] inDegree = new int[vertices];
        
        // 1. Calculate in-degree of every vertex
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++;
            }
        }
        
        // 2. Add all vertices with in-degree 0 to the BFS queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // 3. Process nodes using BFS
        int visitedCount = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visitedCount++; // Increment count of nodes included in topological order
            
            // Reduce in-degree for all neighboring nodes
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                
                // If in-degree becomes 0, add it to the queue
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        // 4. If visited count doesn't match total vertices, a cycle exists
        return visitedCount != vertices;
    }

    public static void main(String[] args) {
        int vertices = 4;
        List<List<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Creating a cyclic graph: 0 -> 1 -> 2 -> 3 -> 1
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(1); // This edge creates the cycle

        System.out.println("Graph contains cycle? " + isCyclic(vertices, adj));
    }
}
