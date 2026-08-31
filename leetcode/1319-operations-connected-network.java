class DisjointSet {
    private int[] parent;
    private int[] rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
    }

    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }

        // one thing i implemented wrongly - rank will only increase if both have same rank
        // If ranks are equal, pick one to be parent and increment its rank
        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
            rank[pb]++;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }
        return true;
    }

    public int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        int pa = parent[a];
        parent[a] = find(pa);
        return parent[a];
    }
    
    public int totalSets() {
        int c = 0;
        for (int i=0; i<parent.length; i++) {
            if (i == parent[i]) {
                c++;
            }
        }
        return c;
    }
}

class Solution {
    public int makeConnected(int n, int[][] connections) {
        int totalEdges = connections.length;
        if (totalEdges < n-1) { // spanning tree concept
            return -1;
        }

        DisjointSet ds = new DisjointSet(n);
        for (int[] c : connections) {
            ds.union(c[0], c[1]);
        }

        // count total sets
        int sets = ds.totalSets();
        return sets - 1;
    }
}