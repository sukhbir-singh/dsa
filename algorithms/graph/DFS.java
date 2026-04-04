package algorithms.graph;
import java.util.*;
class DFS
{
    public static void dfs(int node, boolean vis[], List<List<Integer>> adj, ArrayList<Integer> storeDfs) {
        storeDfs.add(node);
        // marking current node as visited
        vis[node] = true;

        // getting neighbour nodes
        for(Integer it: adj.get(node)) {
            if(vis[it] == false) {
                dfs(it, vis, adj, storeDfs);
            }
        }
    }

    public static ArrayList<Integer> dfsOfGraph(int V, List<List<Integer>> adj)
    {
        ArrayList<Integer> storeDfs = new ArrayList<>();

        // boolean array to keep track of visited vertices
        boolean vis[] = new boolean[V+1];

        // If you are starting from node 2, then i should start from 2
        // remember to this: because graph can be disconnected
        for(int i = 1; i<=V; i++) {
            if(!vis[i]) {
                dfs(i, vis, adj, storeDfs);
            }
        }

        return storeDfs;
    }

    public static void main(String args[]) {
        List<List<Integer>> adj = new ArrayList<>();

        // adding new arraylists to 'adj' to add neighbour nodes
        for (int i = 0; i < 6; i++) {
            adj.add(new ArrayList < > ());
        }

        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(1).add(4);
        adj.get(1).add(5);
        adj.get(2).add(4);
        adj.get(2).add(1);
        adj.get(3).add(1);
        adj.get(4).add(1);
        adj.get(4).add(2);
        adj.get(5).add(1);


        List <Integer> ans = dfsOfGraph(5, adj);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}
