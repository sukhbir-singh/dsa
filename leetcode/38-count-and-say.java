class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        
        // we have to say previous answer
        String prev = countAndSay(n-1);
        StringBuilder sb = new StringBuilder();

        char ch = prev.charAt(0);
        int count = 1;
        for (int i=1; i<prev.length(); i++) {
            char nextCh = prev.charAt(i);
            if (ch == nextCh) {
                count++;
            } else {
                sb.append(count);
                sb.append(ch-'0');
                count = 1;
                ch = nextCh;
            }
        }

        sb.append(count);
        sb.append(ch-'0');
        return sb.toString();
    }
}