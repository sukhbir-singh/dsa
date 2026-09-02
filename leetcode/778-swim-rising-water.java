import java.util.*;

class Solution {
    private record Item(int time, int x, int y){};

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] time = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }
        time[0][0] = grid[0][0]; // for reaching 0th, it will take this much time

        Queue<Item> pq = new PriorityQueue<>((i1, i2) -> i1.time() - i2.time());
        pq.add(new Item(grid[0][0], 0, 0));

        int[][] adj = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        while(!pq.isEmpty()) {
            Item it = pq.remove();

            for (int[] nb: adj) {
                int nx = it.x() + nb[0];
                int ny = it.y() + nb[1];

                if (nx<0 || ny<0 || nx>n-1 || ny>m-1) {
                    continue;
                }

                int reachTime = it.time();
                if (grid[nx][ny] > reachTime) {
                    reachTime = grid[nx][ny];
                }

                if (reachTime < time[nx][ny]) {
                    time[nx][ny] = reachTime;
                    pq.add(new Item(reachTime, nx, ny));
                }
            }
        }

        return time[n-1][m-1];
    }
}