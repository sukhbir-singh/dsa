import java.util.*;

// naming could be improved
// learning: for calculating number of ways, you have to add ways from different paths
// one more important learning in this question is that you cannot take just one variable to count total ways for final node. You have to count ways for each node because then only, you can add those ways to reach final node.
class Solution {
    private record Entry(long dist, int dest){};
    private int MOD = 1000_000_007;

    public int countPaths(int n, int[][] roads) {
        if (n == 2 && roads.length == 1) {
            return 1;
        }

        List<List<int[]>> adj = new ArrayList<>();
        for (int i=0; i<n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road: roads) {
            adj.get(road[0]).add(new int[]{road[1], road[2]});
            adj.get(road[1]).add(new int[]{road[0], road[2]}); // Important - remember to add this for undirected graphs
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>((e1, e2) -> (int)(e1.dist() - e2.dist()));
        pq.add(new Entry(0, 0));

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        int[] ways = new int[n];
        ways[0] = 1;

        while (!pq.isEmpty()) {
            Entry e = pq.remove();

            if (e.dist() > dist[e.dest()]) { // important
                continue;
            }

            for (int[] ent: adj.get(e.dest())) {
                long newDist = e.dist() + ent[1];
                int newDest = ent[0];

                if (newDist < dist[newDest]) {
                    dist[newDest] = newDist;
                    ways[newDest] = ways[e.dest()];
                    pq.add(new Entry(newDist, newDest));

                } else if (newDist == dist[newDest]) {
                    ways[newDest] = (ways[newDest] + ways[e.dest()])%MOD;
                }
            }
        }

        return (int)ways[n-1];
    }
}

// Better naming example
class Solution2 {
    private static final int MOD = 1_000_000_007;

    private record Entry(long dist, int node) {}

    public int countPaths(int n, int[][] roads) {
        List<List<int[]>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            adj.get(road[0]).add(new int[] {road[1], road[2]});
            adj.get(road[1]).add(new int[] {road[0], road[2]});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        long[] ways = new long[n];
        ways[0] = 1;

        PriorityQueue<Entry> pq =
                new PriorityQueue<>(Comparator.comparingLong(Entry::dist));
        pq.add(new Entry(0, 0));

        while (!pq.isEmpty()) {
            Entry current = pq.remove();
            int node = current.node();

            if (current.dist() > dist[node]) {
                continue;
            }

            for (int[] edge : adj.get(node)) {
                int next = edge[0];
                long newDist = current.dist() + edge[1];

                if (newDist < dist[next]) {
                    dist[next] = newDist;
                    ways[next] = ways[node];
                    pq.add(new Entry(newDist, next));
                } else if (newDist == dist[next]) {
                    ways[next] = (ways[next] + ways[node]) % MOD;
                }
            }
        }

        return (int) ways[n - 1];
    }
}