import java.util.*;
// Learning: there is not need to store indexes of elements. Stack<Integer> should be sufficient.
// Instead of this - just store the indices of the elements in the stack and that will represent both index as well as element.
// Travering from right to left is better in this question.
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int[] gre = new int[nums.length];
        for (int i = 0; i<nums.length; i++) {
            gre[i] = -1;
        }
        
        Stack<int[]> st = new Stack<>();
        st.push(new int[]{ nums[0], 0 });

        // 2 iterations are needed
        for (int k = 1; k<2*nums.length; k++) {
            int i = k % nums.length;

            if (st.peek()[0] >= nums[i]) {
                st.push(new int[]{ nums[i], i });
                continue;
            }

            // if current num is greater
            while (!st.isEmpty() && st.peek()[0] < nums[i]) {
                int[] e = st.pop();
                gre[e[1]] = nums[i];
            }

            st.push(new int[]{ nums[i], i });
        }

        return gre;
    }
}