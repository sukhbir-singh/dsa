// DP solution seems more logical and that we can come up with in interview.
// 2 pointers solutions are little difficult to come up in interview. but braket questions always gives us hint for it.

// possible solutions: top down DP, bottom up DP, 2 stacks, 2 pointers
class Solution {
    public boolean checkValidString(String s) {
        // Range of possible unmatched '(' counts across interpretations of '*' (min = lowest, max = highest).
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
        return min == 0; // min value > 0 means some unmatched open bracket found. example: "(*))("
    }
}

class Solution2 {
    public boolean checkValidString(String s) {
        int openCount = 0;
        int closeCount = 0;
        int length = s.length() - 1;
        
        // Traverse the string from both ends simultaneously
        for (int i = 0; i <= length; i++) {
            // Count open parentheses or asterisks
            if (s.charAt(i) == '(' || s.charAt(i) == '*') {
                openCount++;
            } else {
                openCount--;
            }
            
            // Count close parentheses or asterisks
            if (s.charAt(length - i) == ')' || s.charAt(length - i) == '*') {
                closeCount++;
            } else {
                closeCount--;
            }
            
            // If at any point open count or close count goes negative, the string is invalid
            if (openCount < 0 || closeCount < 0) {
                return false;
            }
        }
        
        // If open count and close count are both non-negative, the string is valid
        return true;
    }
}