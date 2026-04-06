import java.util.*;
// multi source BFS
class Solution {
    private record Point(int r, int c){};

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        int[][] v = new int[n][m];
        Queue<Point> q = new LinkedList<>();

        // add all boundary 0s to queue and visit them
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (i == 0 || j == 0 || i == n-1 || j == m-1) {
                    if (board[i][j] == 'O') {
                        v[i][j] = 1;
                        q.add(new Point(i, j));
                    }
                }
            }
        }

        int[][] directions = {{0,1}, {1, 0}, {-1,0}, {0, -1}};

        // bfs traversal
        while (!q.isEmpty()) {
            Point p = q.remove();

            for (int[] d : directions) {
                int row = p.r() + d[0];
                int col = p.c() + d[1];

                if (row >= 0 && col >= 0 && row < n-1 && col < m-1) {
                    if (v[row][col] == 0 && board[row][col] == 'O') {
                        v[row][col] = 1;
                        q.add(new Point(row, col));
                    }
                }
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (v[i][j] == 0 && board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
}