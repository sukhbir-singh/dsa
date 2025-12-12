import java.util.*;

class Solution {
    private class Pair implements Comparable<Pair> {
        public char ch;
        public int freq;
        public Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        public int compareTo(Pair a) {
            return a.freq - this.freq;
        }
    }

    public String frequencySort(String s) {
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i<s.length(); i++) {
            char ch = s.charAt(i);
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> e : mp.entrySet()) {
            pq.add(new Pair(e.getKey(), e.getValue()));
        }

        StringBuilder sb = new StringBuilder();
        while (pq.size() > 0) {
            Pair p = pq.remove();
            int f = p.freq;
            while (f>0) {
                sb.append(p.ch);
                f--;
            }
        }

        return sb.toString();
    }
}