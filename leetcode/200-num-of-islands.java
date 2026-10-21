// number of DFS we have to do will be our answer
// other way: BFS, DFS, Disjoint set
class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] v = new int[n][m];

        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == '1' && v[i][j] == 0){
                    dfs(grid, v, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int[][] v, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) {
            return;
        }
        if (v[r][c] == 1 || grid[r][c] == '0') {
            return;
        }

        v[r][c] = 1;
        dfs(grid, v, r+1, c);
        dfs(grid, v, r-1, c);
        dfs(grid, v, r, c+1);
        dfs(grid, v, r, c-1);
    }
}

// good for reference
class Solution2 {
    class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    int id = cellId(i, j, n);
                    if (grid[i][j] == '1') {
                        parent[id] = id;
                        ++count;
                    }
                    rank[id] = 0;
                }
            }
        }

        public int find(int i) { // path compression
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';

                    // could be done using directions loop
                    int current = cellId(r, c, nc);
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(current, cellId(r - 1, c, nc));
                    }
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        uf.union(current, cellId(r + 1, c, nc));
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(current, cellId(r, c - 1, nc));
                    }
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(current, cellId(r, c + 1, nc));
                    }
                }
            }
        }

        return uf.getCount();
    }

    private static int cellId(int row, int col, int columnCount) {
        return row * columnCount + col;
    }
}