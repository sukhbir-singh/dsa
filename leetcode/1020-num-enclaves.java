// calculating number of 1s, reachable by boundary
// using DFS traversal
class Solution {
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] v = new int[n][m];
        for (int i=0; i<n; i++) {
            if (grid[i][0] == 1 && v[i][0] == 0) {
                dfs(grid, i, 0, v);
            }
            if (grid[i][m-1] == 1 && v[i][m-1] == 0) {
                dfs(grid, i, m-1, v);
            }
        }

        for (int j=0; j<m; j++) {
            if (grid[0][j] == 1 && v[0][j] == 0) {
                dfs(grid, 0, j, v);
            }
            if (grid[n-1][j] == 1 && v[n-1][j] == 0) {
                dfs(grid, n-1, j, v);
            }
        }

        // unvisited and 1s => this sum we have to return
        int count = 0;
        for (int i=0; i<n-1; i++) {
            for (int j=0; j<m-1; j++) {
                if (v[i][j] == 0 && grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c, int[][] v) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return;
        }
        if (v[r][c] == 1 || grid[r][c] == 0) {
            return;
        }

        v[r][c] = 1;
        dfs(grid, r-1, c, v);
        dfs(grid, r+1, c, v);
        dfs(grid, r, c-1, v);
        dfs(grid, r, c+1, v);
    }
}