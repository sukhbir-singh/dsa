import java.util.*;
// Important question
class Solution {
    public int maximalRectangle(char[][] matrix) {
        // prefix matrix
        int[][] pm = new int[matrix.length][matrix[0].length];

        for (int i=0; i<matrix[0].length; i++) {
            int sum = 0;
            for (int j=0; j<matrix.length; j++) {
                sum += matrix[j][i] - '0';
                if (matrix[j][i] - '0' == 0) {
                    sum = 0;
                }
                pm[j][i] = sum;
            }
        }

        // now send each prefix matrix's row to find maximum histogram
        int mx = 0;
        for (int i=0; i<pm.length; i++) {
            int area = findMaxHistogram(pm[i]);
            mx = Math.max(mx, area);
        }

        return mx;
    }

    // This is important standard question - remember this
    private int findMaxHistogram(int[] nums) {
        System.out.println("processing: " + Arrays.toString(nums));
        Stack<Integer> st = new Stack<>();
        int mx = 0;

        // solving this by previous and next smaller elements
        // finding them dynamically in one iteration for each element
        for (int i=0; i<nums.length; i++) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                int elementIndex = st.pop();
                int pse = st.isEmpty() ? -1 : st.peek();
                int nse = i;

                // important: area is width * height
                int area = (nse - pse - 1) * nums[elementIndex];
                System.out.println(nums[elementIndex] + " has area " + area);
                mx = Math.max(mx, area);
            }

            st.push(i);
        }

        while (!st.isEmpty()) {
            int elementIndex = st.pop();
            int pse = st.isEmpty() ? -1 : st.peek();
            int nse = nums.length;

            // important: area is width * height
            int area = (nse - pse - 1) * nums[elementIndex];
            System.out.println(nums[elementIndex] + " has area " + area);
            mx = Math.max(mx, area);
        }

        return mx;
    }
}
