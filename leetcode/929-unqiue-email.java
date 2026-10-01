import java.util.*;

class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> st = new HashSet<>();
        for (String s: emails) {
            st.add(processEmail(s));
        }
        return st.size();
    }
    
    private String processEmail(String email) {
        int atP = 0;
        for (int i=0; i<email.length(); i++) {
            char ch = email.charAt(i);
            if (ch == '@') {
                atP = i;
                break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<atP; i++) {
            char ch = email.charAt(i);
            if (ch == '.') {
                // skip
            } else if (ch == '+') {
                break;
            } else {
                sb.append(ch);
            }
        }
        
        // append remaining email
        sb.append(email.substring(atP));
        return sb.toString();
    }
}