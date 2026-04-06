import java.util.*;
// biggest mistake i keep on making in this query is to use DFS becaues of branches. This is completely wrong.
// We have to find minimum distance. So please prefer using BFS.
// Set here is working as visited set
// Other solutions: Preprocessing can be done for optimization, Bidirectional BFS to reduce overall time
class Solution {
    private record Pair(String word, int dist){};

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>();
        for (String w : wordList) {
            words.add(w);
        }
        words.remove(beginWord);

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));

        while(!q.isEmpty()) {
            Pair p = q.remove();
            if (p.word().equals(endWord)) {
                return p.dist();
            }

            List<String> adWords = getNextWords(p.word(), words);
            for (String word: adWords) {
                q.add(new Pair(word, p.dist() + 1));
                words.remove(word);
            }
        }

        return 0;
    }

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