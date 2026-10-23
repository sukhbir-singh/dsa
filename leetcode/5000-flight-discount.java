import java.util.*;

class FlightDiscount {

    // Helper class to represent the edges (flights)
    static class Edge {
        int to;
        long price;

        Edge(int to, long price) {
            this.to = to;
            this.price = price;
        }
    }

    // Helper class to store current Dijkstra states in the priority queue
    static class State implements Comparable<State> {
        int city;
        long cost;
        int used; // 0 = discount not used yet, 1 = discount used

        State(int city, long cost, int used) {
            this.city = city;
            this.cost = cost;
            this.used = used;
        }

        @Override
        public int compareTo(State other) {
            // Sort by cost ascending
            return Long.compare(this.cost, other.cost);
        }
    }

    public static long cheapestFlightWithDiscount(int n, int[][] flights, int src, int dst) {
        // Step 1: Build adjacency list graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            int u = flight[0];
            int v = flight[1];
            long price = flight[2];
            graph.get(u).add(new Edge(v, price));
        }

        // Step 2: Initialize distance table
        // dist[u][0] -> cost to reach u without using coupon
        // dist[u][1] -> cost to reach u after using coupon
        long[][] dist = new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // Priority Queue ordered by minimum cost
        PriorityQueue<State> pq = new PriorityQueue<>();
        
        // Start at source city
        dist[src][0] = 0;
        pq.add(new State(src, 0, 0));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int u = curr.city;
            long cost = curr.cost;
            int used = curr.used;

            // If we found a more expensive route to an already optimal state, skip it
            if (cost > dist[u][used]) {
                continue;
            }

            // Early exit: Since it's a min-heap, the first time we pop 'dst' 
            // with a fully verified path, it is the absolute minimum cost.
            if (u == dst) {
                return cost;
            }

            // Traverse neighbors
            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                long price = edge.price;

                // Option 1: Don't use the discount on this leg
                if (cost + price < dist[v][used]) {
                    dist[v][used] = cost + price;
                    pq.add(new State(v, dist[v][used], used));
                }

                // Option 2: Use the discount on this leg (only allowed if we haven't used it yet)
                if (used == 0) {
                    long discountedPrice = price / 2;
                    if (cost + discountedPrice < dist[v][1]) {
                        dist[v][1] = cost + discountedPrice;
                        pq.add(new State(v, dist[v][1], 1));
                    }
                }
            }
        }

        // Check if destination is reachable under either state
        long finalAns = Math.min(dist[dst][0], dist[dst][1]);
        return finalAns == Long.MAX_VALUE ? -1 : finalAns;
    }

    public static void main(String[] args) {
        // Example graph: 4 cities (0 to 3)
        int n = 4;
        int[][] flights = {
            {0, 1, 100},
            {1, 2, 500},
            {2, 3, 100},
            {0, 3, 500}
        };
        int src = 0;
        int dst = 3;

        // Path 0->1->2->3 costs 100 + 500 + 100 = 700. With discount on 500 leg -> 100 + 250 + 100 = 450.
        // Path 0->3 costs 500. With discount on 500 leg -> 250.
        // Expected answer: 250
        System.out.println("Cheapest Price: " + cheapestFlightWithDiscount(n, flights, src, dst));
    }
}
