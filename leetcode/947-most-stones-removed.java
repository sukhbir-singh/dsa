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

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pb] < rank[pa]) {
            parent[pb] = pa;
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
    
    // this utility method is very useful
    // another better way is to keep totalComponents as instance variable in the class
    // and reduce it whenever we do successful union. so this method can be removed after that.
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
    public int removeStones(int[][] stones) {
        int n = stones.length;
        DisjointSet ds = new DisjointSet(n);

        // matching stones in same row/col
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int[] s1 = stones[i];
                int[] s2 = stones[j];

                if (s1[0] == s2[0] || s1[1] == s2[1]) {
                    ds.union(i, j);
                }
            }
        }

        int sets = ds.totalSets(); // this many stones will be remaining at most
        return n - sets;
    }
}