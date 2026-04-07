import java.util.*;
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        
        // create graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i=0; i<numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre: prerequisites) {
            adj.get(pre[1]).add(pre[0]);
            indegrees[pre[0]]++;
        }

        // bfs
        Queue<Integer> q = new LinkedList<>();
        for (int i=0; i<numCourses; i++) {
            if (indegrees[i] == 0) {
                q.add(i);
            }
        }

        int[] ans = new int[numCourses];
        int pointer = 0;
        while (!q.isEmpty()) {
            int node = q.remove();
            ans[pointer++] = node;
            
            // reduce indegress of connected nodes
            for (int nb: adj.get(node)) {
                indegrees[nb]--;
                if (indegrees[nb] == 0) {
                    q.add(nb);
                }
            }
        }

        return pointer != numCourses ? new int[0] : ans;
    }
}