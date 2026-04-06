import java.util.*;
// Tough qustion to think and implement
// but teaches some good optimization lessons
// Prefer mutation-based neighbor generation (like Solution1) instead of full distance scan.
class Solution1 {
    private Map<String, List<String>> adj = new HashMap<>();
    private List<List<String>> ans = new ArrayList<>();
    private List<String> cur = new ArrayList<>();

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>();
        for (String w : wordList) {
            words.add(w);
        }
        words.remove(beginWord);

        // run BFS for creating adj list
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        Set<String> inQueue = new HashSet<>();
        inQueue.add(beginWord); // kind of global visited array to use before putting in queue.

        // this is a smart way to visit nodes level by level
        while(!q.isEmpty()) {
            List<String> levelVisited = new ArrayList<>();

            for (int i=q.size()-1; i>=0; i--) {
                String curWord = q.remove();

                List<String> nbs = getNextWords(curWord, words);
                for (String nb: nbs) {
                    levelVisited.add(nb); // Very Important: This is some serious optimization, dont' underestimate this

                    List<String> temp = adj.getOrDefault(nb, new ArrayList<>());
                    temp.add(curWord); // remember this is reverse link
                    adj.put(nb, temp);

                    if (!inQueue.contains(nb)) {
                        inQueue.add(nb);
                        q.add(nb);
                    }
                }
            }

            // remove all nodes visited in this level from set
            // this needs to be done for avoiding visit to same word again in some other path later
            for (String v: levelVisited) {
                words.remove(v);
            }
        }

        // print graph for debugging
        // System.out.println(adj);

        // run backtracking DFS for creating all possible paths
        cur.add(endWord);
        backtracking(endWord, beginWord);

        return ans;
    }

    private void backtracking(String src, String des) {
        if (des.equals(src)) {
            List<String> newList = new ArrayList<>(cur);
            Collections.reverse(newList);
            ans.add(newList);
            return;
        }

        // go over the neigbours
        for (String nb: adj.getOrDefault(src, new ArrayList<>())) {
            cur.add(nb);
            backtracking(nb, des);
            cur.remove(cur.size()-1);
        }
    }

    // This is slow. It is resulting in O(N.L) time complexity
    // Instead of this, you should prefer char atoz based generation. It will result in O(26.L) time complexity
    private List<String> getNextWords(String cur, Set<String> words) {
        List<String> list = new ArrayList<>();
        for (String word: words) {
            int d = distance(cur, word);
            if (d == 1) {
                list.add(word);
            }
        }
        return list;
    }

    private int distance(String s1, String s2) {
        int d = 0;
        for (int i=0; i<s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                d++;
            }
        }
        return d;
    }
}
