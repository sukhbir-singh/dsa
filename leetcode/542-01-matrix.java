import java.util.*;
// Using record simplified a problem drastically
class Solution {
    private record Point(int r, int c){}

    // For distances we have to use BFS algo
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] v = new int[n][m];
        int[][] d = new int[n][m];

        Queue<Point> q = new LinkedList<>();

        // add 0s to src
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (mat[i][j] == 0) {
                    v[i][j] = 1;
                    q.add(new Point(i, j));
                }
            }
        }

        int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

        while (!q.isEmpty()) {
            Point p = q.remove();
            for (int[] dir: directions) {
                int row = p.r() + dir[0];
                int col = p.c() + dir[1];

                // validity
                if (row >= 0 && row < n && col >= 0 && col < m) {
                    if (v[row][col] == 0 && mat[row][col] == 1) { // visit + 1
                        Point pn = new Point(row, col);
                        q.add(pn);
                        v[row][col] = 1;
                        d[row][col] = 1 + d[p.r()][p.c()];
                    }
                }
            }
        }

        return d;
    }
}