import java.util.*;
// observations:
// maybe you can avoid visited array, original image can be used as visited array
// using directions is really important and makes it easy
// Keep Point class simple, just use it for pushing point to queue. Not for Set, etc.
class Solution {
    class Point {
        public int r;
        public int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int r = image.length;
        int c = image[0].length;
        int originalColor = image[sr][sc];
        
        Queue<Point> q = new LinkedList<>();
        int[][] v = new int[r][c];

        v[sr][sc] = 1;
        q.add(new Point(sr, sc));
        image[sr][sc] = color;

        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        
        while (!q.isEmpty()) {
            Point p = q.remove();
            
            for (int[] d: directions) {
                Point p1 = new Point(p.r + d[0], p.c + d[1]);

                // check point validity
                if (p1.r >=0 && p1.r < r && p1.c >=0 && p1.c < c) {
                    // unvisited and same color
                    if (v[p1.r][p1.c] == 0 && image[p1.r][p1.c] == originalColor) {
                        v[p1.r][p1.c] = 1;
                        q.add(p1);
                        image[p1.r][p1.c] = color;
                    }
                }
            }
        }

        return image;
    }
}