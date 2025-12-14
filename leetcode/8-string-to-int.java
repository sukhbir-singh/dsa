class Solution {
    public int myAtoi(String s) {
        long ans = 0;
        int len = s.length();
        if (len == 0) return 0;

        int start = 0;
        while (start < len && s.charAt(start) == ' ') {
            start++;
        }

        if (start >= len) {
            return 0;
        }

        int sign = s.charAt(start) == '-' ? -1 : 1;
        boolean notStartedProcessingNums = false;

        for (int i=start; i<len; i++) {
            char ch = s.charAt(i);
            if (i == start && (ch == '-' || ch == '+')) {
                continue;
            }
            if (ch == '0' && !notStartedProcessingNums) {
                continue;
            }
            if (ch >= '0' && ch <= '9') {
                notStartedProcessingNums = true;
                int num = ch - '0';
                
                if (sign == 1 && (ans * 10 + num) >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (sign == -1 && (ans * 10 + num) >= ((long)Integer.MAX_VALUE + 1L)) {
                    return Integer.MIN_VALUE;
                }

                ans = ans * 10 + num;
            } else {
                return sign * (int)ans;
            }

            
        }
        
        return sign * (int)ans;
    }
}

// more cleaner solution.
// observe how you use temp value and find result and then compare it with outbound range.
class Solution2 {
    public String removeWhiteSpaces(String s) {
        int start = 0;
        int end = s.length()-1;
        while(start<end && s.charAt(start) == ' ') {
            start++;
        }
        while(start<end && s.charAt(end) == ' ') {
            end--;
        }
        return s.substring(start, end+1);
    }

    public int myAtoi(String s) {
        int ans = 0;
        int sign = 1;
        
        // this also reduces lots of edge cases
        // instead of this you can also use pointers
        String str = removeWhiteSpaces(s);
        int len = str.length();

        for(int i=0;i<len;i++) {
            char ch = str.charAt(i);
            if ((ch == '+' || ch == '-') && (i == 0)) {
                sign = ch == '-' ? -1 : 1;
            } else if (ch >= '0' && ch <= '9') {
                
                // range check
                Long temp = ((long)ans)*10 + (ch-'0');
                temp = temp*sign;
                if (temp >= Integer.MAX_VALUE || temp <= Integer.MIN_VALUE) {
                    ans = temp >= Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    break;
                }
                
                ans = ans*10 + (ch-'0');
            } else {
                break;
            }
        }
       
        return ans*sign;
    }
}