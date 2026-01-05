import java.util.*;
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();

        Map<Integer, String> mp = new HashMap<>();
        mp.put(2, "abc");
        mp.put(3, "def");
        mp.put(4, "ghi");
        mp.put(5, "jkl");
        mp.put(6, "mno");
        mp.put(7, "pqrs");
        mp.put(8, "tuv");
        mp.put(9, "wxyz");

        recurse(digits, 0, "", list, mp);
        return list;
    }

    private void recurse(String digits, int index, String prefix, List<String> list, Map<Integer, String> mp) {
        if (index == digits.length()) {
            list.add(prefix);
            return;
        }

        String cb = mp.get(digits.charAt(index) - '0');
        for (int i=0; i<cb.length(); i++) {
            recurse(digits, index+1, prefix + cb.charAt(i), list, mp);
        }
    }
}