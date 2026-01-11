import java.util.*;
class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();
        for (int i=0; i<num.length(); i++) {
            if (st.isEmpty()) {
                st.push(num.charAt(i));
                continue;
            }

            while (!st.isEmpty() && k>0 && num.charAt(i) - '0' < st.peek() - '0') {
                st.pop();
                k--;
            }

            st.push(num.charAt(i));
        }

        // check if all k elements are remove or not
        while (k > 0) {
            st.pop();
            k--;
        }

        if (st.size() == 0) {
            return "0";
        }

        // reverse and also remove prefix 0s
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        sb.reverse();
        String res = sb.toString();

        int index = 0;
        while (index < res.length()) {
            if (res.charAt(index) == '0') {
                index++;
            } else {
                break;
            }
        }

        res = res.substring(index);
        return res.length() == 0 ? "0" : res;
    }
}