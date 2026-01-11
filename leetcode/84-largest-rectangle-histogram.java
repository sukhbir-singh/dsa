import java.util.*;
class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] ps = calculatePreviousSmaller(heights);
        int[] ns = calculateNextSmaller(heights);

        int mx = 0;
        for (int i=0; i<heights.length; i++) {
            int currentRange = ns[i] - ps[i] - 1;
            //System.out.println("range for " + heights[i] + " is " + currentRange);
            mx = Math.max(mx, currentRange * heights[i]);
        }

        return mx;
    }

    private int[] calculatePreviousSmaller(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int[] ps = new int[heights.length];

        for (int i = 0; i<heights.length; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            ps[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return ps;
    }

    private int[] calculateNextSmaller(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int[] ns = new int[heights.length];

        for (int i = heights.length-1; i>=0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            ns[i] = st.isEmpty() ? heights.length : st.peek();
            st.push(i);
        }

        return ns;
    }
}