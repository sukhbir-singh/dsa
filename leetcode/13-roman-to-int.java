import java.util.*;
// good observation: if next character is larger than previous, in that case substract and add to result
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> mp = new HashMap<>();
        mp.put('I', 1);
        mp.put('V', 5);
        mp.put('X', 10);
        mp.put('L', 50);
        mp.put('C', 100);
        mp.put('D', 500);
        mp.put('M', 1000);

        int ans = 0;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (i+1 < s.length()) {
                char nch = s.charAt(i+1);
                if (mp.get(nch) > mp.get(ch)) {
                    ans += mp.get(nch) - mp.get(ch);
                    i++;
                    continue;
                }
            }
            ans += mp.get(ch);
        }
        return ans;
    }
}