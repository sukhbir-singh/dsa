import java.util.*;
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        recurse(n, n, 0, 0, "", list);
        return list;
    }

    private void recurse(int l, int r, int lc, int rc, String s, List<String> list) {
        if (l == 0 && r == 0) {
            list.add(s);
            return;
        } else if (l < 0 || r < 0) {
            return;
        }

        if (lc == rc) {
            if (l > 0) {
                recurse(l-1, r, lc+1, rc, s+"(", list);
            }
        } else {
            if (l > 0) {
                recurse(l-1, r, lc+1, rc, s+"(", list);
            }
            if (r > 0) {
                recurse(l, r-1, lc, rc+1, s+")", list);
            }
        }
    }
}

