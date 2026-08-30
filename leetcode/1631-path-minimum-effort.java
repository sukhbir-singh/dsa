import java.util.*;

// good question of shortest distance - using dijkstra = PQ + Distance Array
// thing to note here is that we have to carry effort from source to destination and remember the max value
class Solution {
    private record Item(int e, int x, int y){};

    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int[][] efforts = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(efforts[i], Integer.MAX_VALUE);
        }
        efforts[0][0] = 0;

        PriorityQueue<Item> pq = new PriorityQueue<>((i1, i2) -> i1.e() - i2.e());
        pq.add(new Item(0, 0, 0));

        int[][] dirs = {
            {1,0}, {0,1}, {0,-1}, {-1,0}
        };

        while (!pq.isEmpty()) {
            Item it = pq.remove();

            for (int i=0; i<dirs.length; i++) {
                int x = it.x() + dirs[i][0];
                int y = it.y() + dirs[i][1];

                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }

                int diff = Math.abs(heights[x][y] - heights[it.x()][it.y()]);
                int newEffort = Math.max(it.e(), diff); // effort till this point
                if (efforts[x][y] > newEffort) {
                    efforts[x][y] = newEffort;
                    pq.add(new Item(newEffort, x, y));
                }
            }
        }

        return efforts[n-1][m-1];
    }
}