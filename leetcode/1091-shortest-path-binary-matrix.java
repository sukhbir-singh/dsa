import java.util.*;

// used simple BFS to solve this. Carried distance along with position in queue. Other way could be to take 2d array for keeping distance values of different positions.
// Optimization could be using Dijkstra's algorithm -> that is, priority queue with storing (distance, point) as entries.
class Solution {
    private record Pos(int x, int y){};
    private record Item(Pos p, int dist){};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (grid[0][0] == 1 || grid[n-1][m-1] == 1) {
            return -1;
        } else if (n == 1 && m == 1) {
            return 1;
        }

        // taking visited array, to avoid modifying original grid array
        int visited[][] = new int[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                visited[i][j] = grid[i][j];
            }
        }

        // queue - (position, distance)
        Queue<Item> q = new LinkedList<>();
        q.add(new Item(new Pos(0, 0), 1));
        visited[0][0] = 1;

        int[][] adj = {
            {1,0}, {0,1}, {-1,0}, {0,-1},
            {1,1}, {-1,-1}, {1,-1}, {-1,1}
        };

        int ans = -1;
        
        while (!q.isEmpty()) {
            Item it = q.remove();

            for (int[] nb: adj) {
                int x = it.p().x() + nb[0];
                int y = it.p().y() + nb[1];

                if (x < 0 || y < 0 || x > n-1 || y > m-1) {
                    continue;
                }

                if (x == n-1 && y == m-1 && visited[x][y] == 0) {
                    if (ans == -1) {
                        ans = it.dist() + 1;
                    } else {
                        ans = Math.min(ans, it.dist() + 1);
                    }
                }

                if (visited[x][y] == 0) {
                    q.add(new Item(new Pos(x, y), 1+it.dist()));
                    visited[x][y] = 1;
                }
            }
        }

        return ans;
    }
}