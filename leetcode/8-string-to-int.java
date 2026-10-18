// push for cleaner solution
// DFA solution is something you can use in other solutions as well
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

//// Deterministic Finite Automaton (DFA) based solution is also very interesting
//// Note - This is going to take time in implementation, so dont prefer it
enum State {
    q0,
    q1,
    q2,
    qd,
}

class StateMachine {
    // Store current state value.
    private State currentState;
    // Store result formed and it's sign.
    private int result, sign;

    public StateMachine() {
        currentState = State.q0;
        result = 0;
        sign = 1;
    }

    // Transition to state q1.
    private void toStateQ1(char ch) {
        sign = (ch == '-') ? -1 : 1;
        currentState = State.q1;
    }

    // Transition to state q2.
    private void toStateQ2(int digit) {
        currentState = State.q2;
        appendDigit(digit);
    }

    // Transition to dead state qd.
    private void toStateQd() {
        currentState = State.qd;
    }

    // Append digit to result, if out of range return clamped value.
    private void appendDigit(int digit) {
        if (
            (result > Integer.MAX_VALUE / 10) ||
            (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)
        ) {
            if (sign == 1) {
                // If sign is 1, clamp result to Integer.MAX_VALUE.
                result = Integer.MAX_VALUE;
            } else {
                // If sign is -1, clamp result to Integer.MIN_VALUE.
                result = Integer.MIN_VALUE;
                sign = 1;
            }

            // When the 32-bit int range is exceeded, a dead state is reached.
            toStateQd();
        } else {
            // Append current digit to the result.
            result = result * 10 + digit;
        }
    }

    // Change state based on current input character.
    public void transition(char ch) {
        if (currentState == State.q0) {
            // Beginning state of the string (or some whitespaces are skipped).
            if (ch == ' ') {
                // Current character is a whitespaces.
                // We stay in same state.
                return;
            } else if (ch == '-' || ch == '+') {
                // Current character is a sign.
                toStateQ1(ch);
            } else if (Character.isDigit(ch)) {
                // Current character is a digit.
                toStateQ2(ch - '0');
            } else {
                // Current character is not a space/sign/digit.
                // Reached a dead state.
                toStateQd();
            }
        } else if (currentState == State.q1 || currentState == State.q2) {
            // Previous character was a sign or digit.
            if (Character.isDigit(ch)) {
                // Current character is a digit.
                toStateQ2(ch - '0');
            } else {
                // Current character is not a digit.
                // Reached a dead state.
                toStateQd();
            }
        }
    }

    // Return the final result formed with it's sign.
    public int getInteger() {
        return sign * result;
    }

    // Get current state.
    public State getState() {
        return currentState;
    }
}

class SolutionUsingDFA {
    public int myAtoi(String s) {
        StateMachine Q = new StateMachine();

        for (int i = 0; i < s.length() && Q.getState() != State.qd; ++i) {
            Q.transition(s.charAt(i));
        }

        return Q.getInteger();
    }
}
