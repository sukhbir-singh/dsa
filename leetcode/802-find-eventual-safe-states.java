import java.util.*;
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // create reverse graph for detecting cycles using kahn's algorithm
        int n = graph.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i<n; i++) {
            adj.add(new ArrayList<>());
        }

        int[] ind = new int[n]; // indegree
        for (int i=0; i<n; i++) {
            for (int j=0; j<graph[i].length; j++) {
                adj.get(graph[i][j]).add(i);  // do this carefully
                ind[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i=0; i<n; i++) {
            if (ind[i] == 0) {
                q.add(i);
            }
        }

        // remaining nodes are part of a cycle
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.remove();
            ans.add(node);
            for (int nb: adj.get(node)) {
                ind[nb]--;
                if (ind[nb] == 0) {
                    q.add(nb);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }
}