import java.util.*;
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int[] pss = previousSmallerOrEqual(arr);
        int[] ns = nextSmaller(arr);

        //System.out.println("pss: " + Arrays.toString(pss));
        //System.out.println("ns: " + Arrays.toString(ns));

        int mod = (int)(1e9 + 7); // here 1e9 = 10^9
        // int mod = 1000000007; // same
        long total = 0;
        for (int i=0; i<arr.length; i++) {
            int left = i - pss[i];
            int right = ns[i] - i;
            long freq = (left * right)% mod;
            total += (arr[i] * 1L * freq) % mod;
        }

        return (int)(total % mod);
    }

    private int[] nextSmaller(int[] arr) {
        Stack<Integer> st = new Stack<>(); // store indices
        int[] ns = new int[arr.length];
        for (int i=arr.length - 1; i>=0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            ns[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return ns;
    }

    private int[] previousSmallerOrEqual(int[] arr) {
        Stack<Integer> st = new Stack<>(); // store indices
        int[] ps = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }
            ps[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return ps;
    }
}

// Good thought process but it is giving time limit exceeded
class Solution2{
    public int sumSubarrayMins(int[] arr) {
        long sum = 0;
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> l = new LinkedList<>();

        // all elements added at level 1
        for (int n : arr) {
            q.add(n);
            l.add(1);
            sum += n;
        }

        while (!q.isEmpty()) {
            int num = q.remove();
            int level = l.remove();

            if (!q.isEmpty() && level == l.peek()) {
                int mn = Math.min(num, q.peek());
                sum += mn;
                q.add(mn);
                l.add(level + 1);
            }
        }

        return (int)(sum % 1000000007L);
    }
}