class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int i=0; i<n; i++) {
            color[i] = -1;
        }

        for (int i=0; i<n; i++) {
            if (color[i] == -1) {
                if (!dfs(graph, color, i, 0)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;

        for (int n: graph[node]) {
            if (colors[n] == color) {
                return false;
            }

            if (colors[n] == -1) {
                boolean res = dfs(graph, colors, n, 1-color);
                if (!res) {
                    return false;
                }
            }
        }
        return true;
    }
}