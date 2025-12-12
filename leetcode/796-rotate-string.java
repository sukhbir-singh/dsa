class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() == 1) {
            return s.charAt(0) == goal.charAt(0);
        }

        if (s.length() != goal.length()) {
            return false;
        }

        char ch = s.charAt(0);
        int len = goal.length();
        for (int i=0; i<len; i++) {
            if (goal.charAt(i) == ch) {
                boolean matched = true;

                // here i is the diff
                for (int j=0; j<len; j++) {
                    if (s.charAt(j) != goal.charAt((i + j)%len)) { 
                        matched = false;
                        break;
                    }
                }

                if (matched) {
                    return true;
                }
            }
        }

        return false;
    }
}
// i = 3
// 0th == (3)%5
// 1 == (4)%5
// 2 == (5)%5