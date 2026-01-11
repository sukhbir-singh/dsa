import java.util.*;
// Lots of good edge cases
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] nge = nextGreaterOrEquals(height);
        int[] pge = prevGreaterOrEquals(height);
        //System.out.println(Arrays.toString(nge));
        //System.out.println(Arrays.toString(pge));

        int[] water = new int[height.length];

        int ind = 0;
        // iterate left to right
        while (ind < n) {
            if (nge[ind] == -1 || height[ind] == 0) {
                ind++;
                continue;
            }

            // iterate till you reach the nge
            int curH = height[ind];
            int nextPos = nge[ind];
            while (ind < nextPos) {
                water[ind] += curH - height[ind];
                ind++;
            }
        }

        // System.out.println("after left to right iteration : " + Arrays.toString(water));

        // iterate right to left
        ind = n - 1;
        while (ind >= 0) {
            if (pge[ind] == -1 || height[ind] == 0) {
                ind--;
                continue;
            }

            // iterate till you reach the nge
            int curH = height[ind];
            int nextPos = pge[ind];

            if (curH == height[nextPos]) {
                ind = nextPos;
                continue;
            }

            while (ind > nextPos) {
                water[ind] = Math.max(water[ind], curH - height[ind]);
                ind--;
            }
        }

        int ans = 0;
        for (int num: water) {
            ans += num;
        }
        return ans;
    }

    private int[] nextGreaterOrEquals(int[] h) {
        int n = h.length;
        int[] nge = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i=n-1; i>=0; i--) {
            while (!st.isEmpty() && h[i] > h[st.peek()]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return nge;
    }

    private int[] prevGreaterOrEquals(int[] h) {
        int n = h.length;
        int[] pge = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i=0; i<n; i++) {
            while (!st.isEmpty() && h[i] > h[st.peek()]) {
                st.pop();
            }
            pge[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return pge;
    }
}

// Easier solution using prefixMax and suffixMax
class Solution2 {
    public int trap(int[] height) {
        int len = height.length;
        int leftMax[] = new int[len];
        int rightMax[] = new int[len];
        
        int mx = 0;
        for(int i=0;i<len;i++) {
            leftMax[i] = mx;
            mx = Math.max(mx, height[i]);
        }
        
        mx = 0;
        for(int i=len-1;i>=0;i--) {
            rightMax[i] = mx;
            mx = Math.max(mx, height[i]);
        }
        
        int sum = 0;
        for(int i=0;i<len;i++){
            int minBoundary = Math.min(leftMax[i], rightMax[i]);
            if (height[i]<minBoundary) {
                sum += minBoundary-height[i];
            }
        }
        
        return sum;
    }
}