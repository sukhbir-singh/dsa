import java.util.*;
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> mp = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            int count = mp.getOrDefault(ch, 0);
            mp.put(ch, count + 1);
        }

        for (int i=0; i<t.length(); i++) {
            char ch = t.charAt(i);
            int count = mp.getOrDefault(ch, -1);
            if (count == -1) return false;
            
            if (count == 1) {
                // remove if it is last occurence. otherwise after this loop we have to iterate over the map again.
                mp.remove(ch);
            } else {
                mp.put(ch, count - 1);
            }
        }

        return true;
    }
}