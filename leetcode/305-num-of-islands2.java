import java.util.*;

// learning: if we have to union in 2d array, better to convert each number to positional index and then do union
class DisjointSet {
    private int[] parent;
    private int[] rank;
    
    private int[] land; // added this just to track count correctly and to mark land positions
    private int count;

    public DisjointSet(int n) {
        count = 0;
        parent = new int[n];
        rank = new int[n];
        land = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
    }

    public int getCount() {
        return count;
    }

    public void setLand(int k) {
        if (land[k] == 1) {
            return;
        }

        land[k] = 1;
        count++;
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
        count--;

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
}

class Solution {
    private record Element(int x, int y){};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        DisjointSet ds = new DisjointSet(m*n);

        Set<Element> set = new HashSet<>();
        int[][] adj = { {0,1}, {1,0}, {-1,0}, {0,-1} };

        for (int i=0; i<positions.length; i++) {
            int[] p = positions[i];
            int index = posToNum(p[0], p[1], n); // good thought
            ds.setLand(index);
            
            // checking if any neigbour exists, union with it
            for (int j=0; j<adj.length; j++) {
                int x = p[0] + adj[j][0];
                int y = p[1] + adj[j][1];

                if (x < 0 || y < 0 || x >= m || y >= n) {
                    continue;
                }

                if (set.contains(new Element(x, y))) {
                    ds.union(index, posToNum(x, y, n));
                }
            }
            
            set.add(new Element(p[0], p[1]));
            res.add(ds.getCount());
        }

        return res;
    }

    private int posToNum(int x, int y, int col) {
        return x*col + y;
    }
}