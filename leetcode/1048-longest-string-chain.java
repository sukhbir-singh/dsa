import java.util.*;
class Solution {
    public int longestStrChain(String[] words) {
        int n = words.length;
        if (n <= 1) {
            return n;
        }

        // need to sort words by length
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());
        // System.out.println(Arrays.toString(words));

        int[] len = new int[n];
        for (int i=0; i<n; i++) {
            len[i] = 1;
        }

        int mx = 1;
        for (int i=0; i<n; i++) {
            for (int j=0; j<i; j++) {

                boolean isPred = isPredecessor(words[j], words[i]) && len[j] >= len[i];
                //System.out.println("isPred : " + isPred + " for (" + words[j] + ", " + words[i] + ")");

                if (isPred) {
                    len[i] = len[j] + 1;
                    mx = Math.max(mx, len[i]);
                }
            }
        }

        return mx;
    }

    // another approach is to go character by character and increase both character pointers based on char match
    private boolean isPredecessor(String w1, String w2) {
        int l1 = w1.length(), l2 = w2.length();
        if (l1 + 1 != l2) {
            return false;
        }

        // skipping ith character from w2 string
        boolean match = false;
        for (int i=0; i<w2.length(); i++) {
            String wn = w2.substring(0,i) + w2.substring(i+1);
            if (wn.equals(w1)) {
                match = true;
                break;
            }
        }

        return match;
    }
}