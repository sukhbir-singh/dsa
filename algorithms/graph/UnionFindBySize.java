package algorithms.graph;

/**
 * Highly optimized Disjoint Set Union (DSU) / Union-Find data structure - THIS IS MORE INTUITIVE
 */
public class UnionFindBySize {
    private final int[] parent;
    private final int[] size;

    // 1. Initialize the structure with 'n' elements
    public UnionFindBySize(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each element is its own parent initially
            size[i] = 1;   // Each set initially has a size of 1
        }
    }

    // 2. Find operation with Path Compression
    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Path Compression: Assign the root directly to parent[i]
        return parent[i] = find(parent[i]);
    }

    // 3. Union operation by Size
    public boolean union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        // They already belong to the same set
        if (rootI == rootJ) {
            return false; 
        }

        // Attach the smaller tree under the root of the larger tree
        if (size[rootI] < size[rootJ]) {
            parent[rootI] = rootJ;
            size[rootJ] += size[rootI];
        } else {
            parent[rootJ] = rootI;
            size[rootI] += size[rootJ];
        }
        return true;
    }

    // Optional: Check if two elements are connected
    public boolean connected(int i, int j) {
        return find(i) == find(j);
    }
}
