import java.util.*;
// DFS implementation was much shorter and simpler.
class Solution {
    public int findCircleNum(int[][] isConnected) {
        // we have to loop over visited array because disconnected graph is given
        int n = isConnected.length;
        boolean[] visited = new boolean[n];

        int count = 0;
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                bfs(i, isConnected, visited);
                count++;
                //System.out.println("after i="+i+", visited: "+Arrays.toString(visited));
            }
        }
        return count;
    }

    private void bfs(int s, int[][] isConnected, boolean[] visited) {
        int n = isConnected.length;
        Queue<Integer> q = new LinkedList<>();
        // visit and add in queue
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            Integer node = q.poll();
            for (int i=0; i<n; i++) {
                // In adj matrix, 2 checks will be needed: connection and visited Or Not
                if (isConnected[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}