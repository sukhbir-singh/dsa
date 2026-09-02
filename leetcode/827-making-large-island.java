import java.util.*;

// Super Important (Very important)
// In disjoint set problems and implementations, do not directly use parent[i] because
// its possible that two nodes from same group can have differnt immediate parent. 
// So, instead use find(int id) method to find the root of the group for both nodes.
class DisjointSet {
    private int[] parent;
    private int[] size;
    
    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }

        if (size[pa] < size[pb]) {
            parent[pa] = pb;
            size[pb] += size[pa];
        } else {
            parent[pb] = pa;
            size[pa] += size[pb];
        }

        return true;
    }

    public int find(int a) {
        if (a == parent[a]) {
            return a;
        }
        int pa = parent[a];
        parent[a] = find(pa);
        return parent[a];
    }

    public int findMaxSize() {
        int mx = 0;
        for (int i=0; i<size.length; i++) {
            mx = Math.max(size[i], mx);
        }
        return mx;
    }

    public int getSize(int a) {
        int pa = find(a);
        return size[pa];
    }

    // this is new learning for me in this problem
    // we can't just blindly add sizes of all 4 adjacent node's size. instead find all unique parents of all 4 adjacent nodes. 
    // and then add sum of all those.
    public int countTotalAdjSize(List<Integer> ids) {
        Set<Integer> st = new HashSet<>();
        for (int id: ids) {
            st.add(find(id));
        }
        int sum = 0;
        for (int pid: st) {
            sum += size[pid];
        }
        return sum;
    }
}

class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        DisjointSet ds = new DisjointSet(n*m);
        int[][] dir = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        // union all connected 1s
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }

                for (int[] adj: dir) {
                    int nx = i + adj[0];
                    int ny = j + adj[1];

                    if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || grid[nx][ny] == 0) {
                        continue;
                    }

                    int id1 = posToId(i, j, m);
                    int id2 = posToId(nx, ny, m);

                    ds.union(id1, id2);
                }
            }
        }

        int res = ds.findMaxSize();
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                // current element should be 0 and adj should 1 to be processed in this loop
                if (grid[i][j] == 1) {
                    continue;
                }

                List<Integer> nbs = new ArrayList<>();
                for (int[] adj: dir) {
                    int nx = i + adj[0];
                    int ny = j + adj[1];

                    if (nx < 0 || ny < 0 || nx > n-1 || ny > m-1 || grid[nx][ny] == 0) {
                        continue;
                    }

                    int adjId = posToId(nx, ny, m);
                    nbs.add(adjId);
                }

                int sumOfAdjs = 1 + ds.countTotalAdjSize(nbs);
                res = Math.max(sumOfAdjs, res);
            }
        }

        return res;
    }

    private int posToId(int x, int y, int m) {
        return x*m + y;
    }
}