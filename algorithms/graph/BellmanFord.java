package algorithms.graph;

import java.util.Arrays;

/*
 * Time: O(V * E + V); up to V-1 relaxation passes and one negative-cycle check.
 * Auxiliary space: O(V) for distances.
 */
public class BellmanFord {
    /**
     * Edges are {from, to, weight} triples in a directed graph with vertices 0..n-1.
     * Unreachable vertices have distance Long.MAX_VALUE.
     *
     * @throws IllegalArgumentException if a negative-weight cycle is reachable from src
     */
    public long[] bellmanFord(int n, int[][] edges, int src) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : edges) {
                int from = edge[0], to = edge[1], weight = edge[2];
                if (dist[from] != Long.MAX_VALUE && dist[from] + weight < dist[to]) {
                    dist[to] = dist[from] + weight;
                }
            }
        }

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            if (dist[from] != Long.MAX_VALUE && dist[from] + weight < dist[to]) {
                throw new IllegalArgumentException("Negative-weight cycle reachable from source");
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int[][] edges = {
            {0, 1, 4},
            {0, 2, 5},
            {1, 2, -2},
            {2, 3, 3}
        };
        BellmanFord solver = new BellmanFord();
        System.out.println(Arrays.toString(solver.bellmanFord(5, edges, 0)));
        // [0, 4, 2, 5, 9223372036854775807]; vertex 4 is unreachable.
    }
}
