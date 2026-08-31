import java.util.*;

class DisjointSet {
    private int[] parent;
    private int[] rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
    }

    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return false;
        }

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pb] < rank[pa]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
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
    
    public int totalSets() {
        int c = 0;
        for (int i=0; i<parent.length; i++) {
            if (i == parent[i]) {
                c++;
            }
        }
        return c;
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        Map<String, Integer> mp = new HashMap<>(); // email -> index

        for (int i=0; i<n; i++) {
            List<String> account = accounts.get(i);

            for (int j=1; j<account.size(); j++) {
                String email = account.get(j);

                if (mp.containsKey(email)) {
                    ds.union(i, mp.get(email));
                } else {
                    mp.put(email, i);
                }
            }
        }

        // System.out.println(mp);
        // System.out.println("sets: " + ds.totalSets());

        List<List<String>> merged = new ArrayList<>();  // map would be better here. index -> list
        for (int i=0; i<n; i++) {
            merged.add(new ArrayList<>());
        }

        for (int i=0; i<n; i++) {
            int parent = ds.find(i);
            
            List<String> account = accounts.get(i);
            for (int j=1; j<account.size(); j++) {
                String email = account.get(j);
                merged.get(parent).add(email);
            }
        }

        // sort and merge each list
        for (int i=0; i<n; i++) {
            List<String> temp = merged.get(i);
            Set<String> st = new HashSet<>(temp);
            temp = new ArrayList<>(st);
            Collections.sort(temp);
            merged.set(i, temp);
        }

        // System.out.println("merged: " + merged);

        List<List<String>> res = new ArrayList<>();
        for (int i=0; i<n; i++) {
            if (merged.get(i).size() > 0) {
                List<String> list = new ArrayList<>();
                list.add(accounts.get(i).get(0)); // name
                list.addAll(merged.get(i));
                res.add(list);
            }
        }

        return res;
    }
}

// Improved version of Solution
// Uses TreeSet - which takes care of both sorting and deduplications
// used map and entry set based iterations
class Solution2 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DisjointSet ds = new DisjointSet(accounts.size());
        Map<String, Integer> emailOwner = new HashMap<>();

        // Connect accounts sharing an email.
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                Integer owner = emailOwner.putIfAbsent(email, i);

                if (owner != null) {
                    ds.union(i, owner);
                }
            }
        }

        // Group unique, sorted emails by their final root.
        Map<Integer, Set<String>> emailsByRoot = new HashMap<>();

        for (Map.Entry<String, Integer> entry : emailOwner.entrySet()) {
            int root = ds.find(entry.getValue());

            emailsByRoot
                .computeIfAbsent(root, key -> new TreeSet<>())
                .add(entry.getKey());
        }

        // Build the result.
        List<List<String>> result = new ArrayList<>();

        for (Map.Entry<Integer, Set<String>> entry : emailsByRoot.entrySet()) {
            int root = entry.getKey();
            List<String> mergedAccount = new ArrayList<>();

            mergedAccount.add(accounts.get(root).get(0));
            mergedAccount.addAll(entry.getValue());
            result.add(mergedAccount);
        }

        return result;
    }
}