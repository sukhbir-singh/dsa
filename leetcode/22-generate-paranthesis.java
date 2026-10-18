import java.util.*;
// ideally you should use stringbuilder, but string is also acceptable most of the times.
// another way is Divide and Conquer - answer.add("(" + leftString + ")" + rightString) - this will try all paranthesis pairs over smaller strings
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        recurse(n, n, 0, 0, "", list);
        return list;
    }

    // here l, r = total given counts of left and right paranthesis
    // I think instead of initial 4 parameters we can reduce it to 2
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

// better and more simpler
class Solution2 {
    public List<String> generateParenthesis(int n) {
        List<String> answer = new ArrayList<>();
        backtracking(answer, new StringBuilder(), 0, 0, n);

        return answer;
    }

    private void backtracking(
        List<String> answer,
        StringBuilder curString,
        int leftCount,
        int rightCount,
        int n
    ) {
        if (curString.length() == 2 * n) {
            answer.add(curString.toString());
            return;
        }
        if (leftCount < n) {
            curString.append("(");
            backtracking(answer, curString, leftCount + 1, rightCount, n);
            curString.deleteCharAt(curString.length() - 1);
        }
        if (leftCount > rightCount) {
            curString.append(")");
            backtracking(answer, curString, leftCount, rightCount + 1, n);
            curString.deleteCharAt(curString.length() - 1);
        }
    }
}