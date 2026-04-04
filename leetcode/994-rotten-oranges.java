import java.util.*;
class Solution {
    // basically we have to calculate maximum distance of fresh orange from a rotten orange
    // multi source BFS
    class Point {
        public int r;
        public int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public String toString() {
            return "("+r+", "+c+")";
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }
        @Override
        public int hashCode() {
            return java.util.Objects.hash(r, c);
        }
    }

    public int orangesRotting(int[][] grid) {
        int[][] dist = new int[grid.length][grid[0].length];
        bfs(grid, dist);
        
        // check if any fresh orange remaining
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1 && dist[i][j] == 0) {
                    return -1;
                }
            }
        }

        int mx = 0;
        for (int i=0; i<dist.length; i++) {
            for (int j=0; j<dist[0].length; j++) {
                mx = Math.max(mx, dist[i][j]);
            }
        }
        return mx;
    }

    private void bfs(int[][] grid, int[][] dist) {
        Queue<Point> q = new LinkedList<>();
        Set<Point> v = new HashSet<>();

        // add all sources i.e. rotten oranges
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    Point p = new Point(i,j);
                    q.add(p);
                    v.add(p);
                }
            }
        }

        // System.out.println("q1 => "+q);

        while (!q.isEmpty()) {
            Point p = q.remove();
            // System.out.println(p +" having dist " + dist[p.r][p.c]);
            // System.out.println("q2 => "+q);

            List<Point> nb = getAdjs(p, grid.length, grid[0].length);
            for (Point pn: nb) {
                if (!v.contains(pn) && grid[pn.r][pn.c] == 1) {
                    q.add(pn);
                    v.add(pn);
                    dist[pn.r][pn.c] = dist[p.r][p.c] + 1;
                }
            }
            // System.out.println("q3 => "+q);
            // System.out.println("v => "+v);
        }
    }

    private List<Point> getAdjs(Point p, int n, int m) {
        List<Point> list = new ArrayList<>();

        // left
        Point p1 = new Point(p.r, p.c-1);
        if (isValid(p1, n, m)) {
            list.add(p1);
        }

        // right
        Point p2 = new Point(p.r, p.c+1);
        if (isValid(p2, n, m)) {
            list.add(p2);
        }

        // top
        Point p3 = new Point(p.r-1, p.c);
        if (isValid(p3, n, m)) {
            list.add(p3);
        }
        
        // bottom
        Point p4 = new Point(p.r+1, p.c);
        if (isValid(p4, n, m)) {
            list.add(p4);
        }

        return list;
    }

    private boolean isValid(Point p, int n, int m) {
        return (p.r >=0 && p.r < n) && (p.c >=0 && p.c < m);
    }
}

// This is much better solution - just ignore the Pair class (its not present by default in java)
// otherwise lots of things to learn
// Interestingly since we are setting value to 2 - we don't need visited array explicitely
class Solution2 {
    public int orangesRotting(int[][] grid) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();

        // Step 1). build the initial set of rotten oranges
        int freshOranges = 0;
        int ROWS = grid.length, COLS = grid[0].length;

        for (int r = 0; r < ROWS; ++r)
            for (int c = 0; c < COLS; ++c)
                if (grid[r][c] == 2)
                    queue.offer(new Pair(r, c));
                else if (grid[r][c] == 1)
                    freshOranges++;

        // Mark the round / level, _i.e_ the ticker of timestamp
        queue.offer(new Pair(-1, -1));

        // Step 2). start the rotting process via BFS
        int minutesElapsed = -1;
        int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // # Good Approach

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.poll();
            int row = p.getKey();
            int col = p.getValue();
            if (row == -1) {
                // We finish one round of processing
                minutesElapsed++;
                // to avoid the endless loop
                if (!queue.isEmpty())
                    queue.offer(new Pair(-1, -1));
            } else {
                // this is a rotten orange
                // then it would contaminate its neighbors
                for (int[] d : directions) {
                    int neighborRow = row + d[0];
                    int neighborCol = col + d[1];
                    if (neighborRow >= 0 && neighborRow < ROWS && 
                        neighborCol >= 0 && neighborCol < COLS) {
                        if (grid[neighborRow][neighborCol] == 1) {
                            // this orange would be contaminated
                            grid[neighborRow][neighborCol] = 2;
                            freshOranges--;  // # Useful for end comparision
                            // this orange would then contaminate other oranges
                            queue.offer(new Pair(neighborRow, neighborCol));
                        }
                    }
                }
            }
        }

        // return elapsed minutes if no fresh orange left
        return freshOranges == 0 ? minutesElapsed : -1;
    }
}
