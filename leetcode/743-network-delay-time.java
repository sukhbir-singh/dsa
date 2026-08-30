import java.util.*;

// since edge list is given lets try bellman ford algorithm
// important learning: if edge list is given and you have to find shortest distance from src to all nodes, 
// then prefer bellman ford. it is much simpler to implement and understand.
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k-1] = 0;

        // relax n-1 times
        for (int i=0; i<n-1; i++) {
            for (int[] edge: times) {
                int src = edge[0] - 1;
                int dest = edge[1] - 1;
                int wt = edge[2];

                if (dist[src] == Integer.MAX_VALUE) {
                    continue;
                }
                if (dist[src] + wt < dist[dest]) {
                    dist[dest] = dist[src] + wt;
                }

                // System.out.println("after edge update => " + Arrays.toString(dist));
            }

            // System.out.println("after i=" + i +"th iteration => " + Arrays.toString(dist));
        }

        // System.out.println(Arrays.toString(dist));

        int mx = -1;
        for (int d: dist) {
            mx = Math.max(d, mx);
        }

        return mx == Integer.MAX_VALUE ? -1 : mx;
    }
}