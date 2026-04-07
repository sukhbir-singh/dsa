import java.util.*;
class Solution {
    public String alienOrder(String[] words) {
        // there can be duplicate words in list
        Set<String> set = new HashSet<>();
        for (String s: words) {
            set.add(s);
        }

        Set<Character> allChars = new HashSet<>();
        for (String s: words) {
            for (char c: s.toCharArray()) {
                allChars.add(c);
            }
        }

        Map<Character, List<Character>> adj = getList(words);

        if (set.size() == 1) {
            for (String s: set) {
                Set<Character> chars = adj.keySet();
                List<Character> chList = new ArrayList<>(chars);
                Collections.sort(chList);
                String s1 = "";
                for (char c: chList) {
                    s1 += c;
                }
                return s1;
            }
        }


        int n = words.length;
        Map<Character, Integer> deg = new HashMap<>();
        for (var entry : adj.entrySet()) {
            char ch = entry.getKey();
            List<Character> list = entry.getValue();
            for (char c : list) {
                deg.put(c, deg.getOrDefault(c, 0) + 1);
            }
        }

        Queue<Character> q = new LinkedList<>();
        for (char ch : adj.keySet()) {
            if (deg.getOrDefault(ch, 0) == 0) {
                q.add(ch);
            }
        }

        List<Character> res = new ArrayList<>();
        while (!q.isEmpty()) {
            char ch = q.remove();
            res.add(ch);
            for (char c: adj.getOrDefault(ch, new ArrayList<>())) {
                int existingDegree = deg.get(c);
                deg.put(c, existingDegree - 1);
                if (existingDegree == 1) {
                    q.add(c);
                }
            }
        }

        // remember this case for cyclic graph
        if (res.size() != allChars.size()) {
            return "";
        }

        String s = "";
        for (char c : res) {
            s += c;
        }

        return s;
    }

    private Map<Character, List<Character>> getList(String[] words) {
        Map<Character, List<Character>> mp = new HashMap<>();
        for (int i=0; i<words.length-1; i++) {
            // i and i+1
            String w1 = words[i];
            String w2 = words[i+1];

            if (w1.equals(w2)) {
                continue;
            }

            int len = Math.min(w1.length(), w2.length());
            if (w1.length() > w2.length() && w2.equals(w1.substring(0,len))) {
                return new HashMap<>();
            }

            
            for (int j=0; j<len; j++) {
                char ch1 = w1.charAt(j);
                char ch2 = w2.charAt(j);

                // ch1 -> ch2
                if (ch1 != ch2) {
                    List<Character> existingList = mp.getOrDefault(ch1, new ArrayList<>());
                    existingList.add(ch2);
                    mp.put(ch1, existingList);
                    break;
                }
            }
        }

        Set<Character> allChars = new HashSet<>();
        for (String s: words) {
            for (char c: s.toCharArray()) {
                allChars.add(c);
                if (!mp.containsKey(c)) {
                    mp.put(c, new ArrayList<>());
                }
            }
        }
        
        return mp;
    }
}