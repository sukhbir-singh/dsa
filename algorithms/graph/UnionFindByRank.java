package algorithms.graph;

/**
 * Highly optimized Disjoint Set Union (DSU) using Union by Rank and Path Compression.
 */
public class UnionFindByRank {
    private final int[] parent;
    private final int[] rank;

    // 1. Initialize the structure with 'n' elements
    public UnionFindByRank(int n) {
        parent = new int[n];
        rank = new int[n]; // Initialized to 0 by default in Java
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each element is its own parent initially
        }
    }

    // 2. Find operation with Path Compression
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Path Compression: Flattens the structure on the way back up
        return parent[i] = find(parent[i]);
    }

    // 3. Union operation by Rank
    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        // They already belong to the same set
        if (rootI == rootJ) {
            return false; 
        }

        // Attach the tree with lower rank under the root of the tree with higher rank
        if (rank[rootI] < rank[rootJ]) {
            parent[rootI] = rootJ;
        } else if (rank[rootI] > rank[rootJ]) {
            parent[rootJ] = rootI;
        } else {
            // If ranks are equal, pick one to be parent and increment its rank
            parent[rootJ] = rootI;
            rank[rootI]++;
        }
        return true;
    }

    // Optional: Check if two elements are connected
    public boolean connected(int i, int j) {
        return find(i) == find(j);
    }
}
