class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            
            int prev = cnt;
            if (ch == '(') {
                cnt++;
            } else if (ch == ')') {
                cnt--;
            }

            if ((prev == 0 && cnt == 1) || (prev == 1 && cnt == 0)) {
                // skip
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}