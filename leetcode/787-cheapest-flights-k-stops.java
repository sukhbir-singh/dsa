import java.util.*;

// learning - dont assume 0 is always src
// since direct edges list is given bellman ford is more simpler in this case. you dont need to create graph.
// Note order by cost in priority queue can result in wrong answer.
class Solution {
    private record Edge(int node, int dist){}; // for graph
    private record EdgeWithStop(int stops, int dist, int node){}; // for priority queue

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] flight: flights) {
            graph.get(flight[0]).add(new Edge(flight[1], flight[2]));
        }

        // edge case - if no nodes are there from src
        if (graph.get(src).size() == 0) {
            return -1;
        }

        // applying dijkstra
        Queue<EdgeWithStop> pq = new PriorityQueue<>((e1, e2) -> e1.stops() - e2.stops());
        pq.add(new EdgeWithStop(0,0,src));

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while(!pq.isEmpty()) {
            EdgeWithStop es = pq.remove();
            if (es.stops() > k) {
                continue;
            }

            for (Edge e : graph.get(es.node())) {
                int newDist = es.dist() + e.dist();
                if (dist[e.node()] > newDist) {
                    dist[e.node()] = newDist;
                    pq.add(new EdgeWithStop(es.stops() + 1, newDist, e.node()));
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}

// using bellman ford
// Important Note - standard Bellman–Ford → no copy needed. This flight-limited variant → use the copy to enforce the edge limit.
class Solution2 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Distance from source to all other nodes.
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Run only K+1 times since we want shortest distance in K hops
        // applying all edges relaxation by number of stop like first with 0 stops will be updated, then 1 stops edges will be updated and so on.
        for (int i = 0; i <= k; i++) {
            // Create a copy of dist vector.
            int[] temp = Arrays.copyOf(dist, n);
            for (int[] flight : flights) {
                if (dist[flight[0]] != Integer.MAX_VALUE) {
                    temp[flight[1]] = Math.min(temp[flight[1]], dist[flight[0]] + flight[2]);
                }
            }
            // Copy the temp vector into dist.
            dist = temp;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
};
