import java.util.*;
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int p1 = 0, p2 = 0;
        int c = 0;
        while (p1 != g.length && p2 != s.length) {
            if (s[p2] >= g[p1]) {
                p1++;
                p2++;
                c++;
            } else {
                p2++;
            }
        }

        return c;
    }
}