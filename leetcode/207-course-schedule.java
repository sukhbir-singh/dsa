import java.util.*;
// Approach 1: using 2 arrays - visited + InBranch
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] preq: prerequisites) {
            adj.get(preq[1]).add(preq[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] branch = new boolean[numCourses];
        for (int i=0; i<numCourses; i++) {
            if (!visited[i]) {
                if (isCycle(i, adj, visited, branch)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCycle(int src, List<List<Integer>> adj, boolean[] visited, boolean[] branch) {
        if (branch[src]) {
            return true; // cycle detected
        }
        if (visited[src]) {
            return false;
        }
        
        visited[src] = true;
        branch[src] = true;

        for (int node : adj.get(src)) {
            if (isCycle(node, adj, visited, branch)) {
                return true;
            }
        }

        branch[src] = false;
        return false;
    }
}

// Approach 2: Using 3 state coloring
class Solution2 {
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