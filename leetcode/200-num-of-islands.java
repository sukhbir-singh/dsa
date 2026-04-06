// number of DFS we have to do will be our answer
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