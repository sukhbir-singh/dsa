import java.util.*;
class Solution {
    // Monotonic Stack Question
    // Lots of edge cases - needs to be implemented carefully
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for (int s: asteroids) {
            if (s > 0) {
                // This case was very important
                // It came after careful observation
                // This makes rest of the cases simple.
                st.push(s);
                continue;
            }

            // negative will collide with previous positives
            while (!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(s)) {
                st.pop();
            }

            // check if s has to be added or not
            if (st.isEmpty() || st.peek() < 0) {
                st.push(s);
            } else if (st.peek () == Math.abs(s)) {
                st.pop(); // both will be destroyed
            }
        }

        int[] arr = new int[st.size()];
        int index = st.size() - 1;
        while (!st.isEmpty()) {
            arr[index--] = st.pop();
        }
        return arr;
    }
}