package algorithms.graph;
import java.util.*;
// Method 1: visited + InStack arrays
public class CycleDetectionDirectedGraph {
    public boolean dfs(int node, List<List<Integer>> adj, boolean[] visit, boolean[] inStack) {
        // If the node is already in the stack, we have a cycle.
        if (inStack[node]) {
            return true;
        }
        if (visit[node]) {
            return false;
        }
        // Mark the current node as visited and part of current recursion stack.
        visit[node] = true;
        inStack[node] = true;
        for (int neighbor : adj.get(node)) {
            if (dfs(neighbor, adj, visit, inStack)) {
                return true;
            }
        }
        // Remove the node from the stack.
        inStack[node] = false;
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] visit = new boolean[numCourses];
        boolean[] inStack = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, adj, visit, inStack)) {
                return false;
            }
        }
        return true;
    }
}

// Method 2: Using 3 state color single array instead of visited + InStack (2 arrays)
class CycleDetectionDirectedGraph2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] preq : prerequisites) {
            adj.get(preq[1]).add(preq[0]);
        }

        // 0 = unvisited, 1 = visiting, 2 = visited (done)
        int[] color = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (color[i] == 0 && hasCycle(i, adj, color)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int src, List<List<Integer>> adj, int[] color) {
        if (color[src] == 1) {
            return true; // back-edge -> cycle
        }
        if (color[src] == 2) {
            return false; // already fully processed
        }

        color[src] = 1; // mark as visiting

        for (int next : adj.get(src)) {
            if (hasCycle(next, adj, color)) {
                return true;
            }
        }

        color[src] = 2; // mark as done
        return false;
    }
}