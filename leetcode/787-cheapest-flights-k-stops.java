import java.util.*;

// learning - dont assume 0 is always src
// since direct edges list is given bellman ford is more simpler in this case. you dont need to create graph.
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