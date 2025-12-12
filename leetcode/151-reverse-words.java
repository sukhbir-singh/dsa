import java.util.*;
class Solution {
    public String reverseWords(String s) {
        Stack<String> st = new Stack<>();

        int left = 0, right = s.length()-1;
        while (s.charAt(left) == ' ') {
            left++;
        }
        while (s.charAt(right) == ' ') {
            right--;
        }

        int wordStart = -1, wordEnd = -1;
        while (left <= right) {
            if (s.charAt(left) != ' ') {
                if (wordStart == -1) {
                    wordStart = left;
                    wordEnd = left;
                } else {
                    wordEnd = left;
                }
            } else {
                // 2 cases: previous is also empty
                // do nothing if previous char is also empty
                if (s.charAt(left - 1) == ' ') {
                    //
                } else {
                    st.push(s.substring(wordStart, wordEnd+1));
                    wordStart = -1;
                    wordEnd = -1;
                }
            }
            left++;
        }

        if (wordStart != -1) {
            st.push(s.substring(wordStart, wordEnd+1));
        }

        StringBuilder sb = new StringBuilder();
        while (st.size() > 0) {
            sb.append(st.pop());
            
            if (st.size() > 0) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}

// Another good approach
class Solution2 {
    public List<String> getTokens(String s) { // '   hello world  '
        List<String> tokens = new ArrayList<>();
        int len = s.length();

        for(int i=0; i<len; i++){
            int start = i; // first figure out correct start position
            while(start < len && s.charAt(start) == ' ') {
                start++;
            }
            int end = start; // then figure out correct end position
            while(end < len && s.charAt(end) != ' ') {
                end++;
            }
            
            if (start != end) {
                tokens.add(s.substring(start, end));
            }
            i = end;
        }
        return tokens;
    }
    
    public String joinReverse(List<String> tokens) {
        StringBuilder sb = new StringBuilder();
        for(int i=tokens.size()-1;i>=0;i--){
            if (i==0) {
                sb.append(tokens.get(i));
            } else {
                sb.append(tokens.get(i) + " ");
            }
        }
        return sb.toString();
    }

    public String reverseWords(String s) {
        List<String> tokens = getTokens(s);
        System.out.println("tokens: " + tokens);
        return joinReverse(tokens);
    }
}

// Third Approach - I think this is the simplest and most perfect implementation

/*
    StringBuilder word = new StringBuilder();
    while (left <= right) {
        char c = s.charAt(left);

        // it means that word will be pushed as soon as we encounter space after word
        if ((word.length() != 0) && (c == ' ')) {
            st.push(word.toString());
            word.setLength(0);
        } else if (c != ' ') {
            word.append(c);
        }
        left++;
    }
*/
