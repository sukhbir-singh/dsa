class Solution {
    // possible solutions: top down DP, bottom up DP, 2 stacks, 2 pointers
    public boolean checkValidString(String s) {
        int min = 0, max = 0;
        for (int i = 0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                min++;
                max++;
            } else if (ch == ')') {
                min--;
                max--;
                // in case of range values - we do not decrease min to less than zero
                // example range should convert to [0,2] -> [0,1]
                min = Math.max(0, min);
            } else if (ch == '*') {
                // after considering all three possibilities - range would be min-1, max+1
                min--;
                max++;
                min = Math.max(0, min);
            }

            if (max < 0) {
                return false;
            }
        }
        return min == 0;
    }
}