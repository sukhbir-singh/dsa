import java.util.*;

// applied floyd warshall for finding shortest path from different source nodes to all other nodes
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] graph = new int[n][n];
        for (int i=0; i<n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }

        for (int i=0; i<edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            int wt = edges[i][2];
            graph[from][to] = wt;
            graph[to][from] = wt;
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                for (int k=0; k<n; k++) {
                    if (graph[j][i] == Integer.MAX_VALUE || graph[i][k] == Integer.MAX_VALUE) {
                        continue;
                    }
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println("");
        }

        // finding answer
        int minCount = Integer.MAX_VALUE, minCity = -1;
        for (int i=0; i<n; i++) {
            int temp = 0;
            for (int j=0; j<n; j++) {
                if (graph[i][j] <= distanceThreshold && graph[i][j] > 0) {
                    temp++;
                }
            }
            if (temp <= minCount) {
                minCount = temp;
                minCity = i;
            }
        }

        return minCity;
    }
}