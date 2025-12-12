class Solution {
    public String longestCommonPrefix(String[] strs) {
        String ans = "";
        if (strs.length == 0) {
            return ans;
        } else if (strs.length == 1) {
            return strs[0];
        }

        int p = 0;
        while (p < strs[0].length()) {
            char ch = strs[0].charAt(p);

            boolean same = true;
            for (String s: strs) {
                if (p >= s.length() || s.charAt(p) != ch) {
                    same = false;
                    break;
                }
            }

            if (!same) {
                return strs[0].substring(0, p);
            } else {
                p++;
            }
        }

        return strs[0].substring(0, p);
    }
}