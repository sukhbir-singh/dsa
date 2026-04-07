package algorithms.graph;
import java.util.*;

// Class containing the solution logic
class Solution {
    // Function to perform DFS
    void dfs(int node, ArrayList<ArrayList<Integer>> adj, int[] vis, Stack<Integer> st) {
        // Mark the current node as visited
        vis[node] = 1;

        // Explore all neighbors of this node
        for (int it : adj.get(node)) {
            // If the neighbor is not visited, recursively perform DFS
            if (vis[it] == 0) {
                dfs(it, adj, vis, st);
            }
        }

        // After visiting all neighbors, push this node into the stack
        st.push(node);
    }

    // Function to perform Topological Sort
    ArrayList<Integer> topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
        // Create a visited array to mark visited vertices
        int[] vis = new int[V];

        // Stack to store vertices in finishing order
        Stack<Integer> st = new Stack<>();

        // Perform DFS from each unvisited vertex
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, adj, vis, st);
            }
        }

        // Prepare the result array
        ArrayList<Integer> ans = new ArrayList<>();
        while (!st.isEmpty()) {
            ans.add(st.pop());
        }

        // Return the topological ordering
        return ans;
    }
}

// Driver class
public class TopoDFS {
    public static void main(String[] args) {
        // Number of vertices
        int V = 6;

        // Create adjacency list for the graph
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding edges
        adj.get(5).add(0);
        adj.get(5).add(2);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        // Create object of Solution class
        Solution obj = new Solution();

        // Get the topological order
        ArrayList<Integer> res = obj.topoSort(V, adj);

        // Print result
        System.out.print("Topological Sort: ");
        for (int node : res) {
            System.out.print(node + " ");
        }
    }
}
