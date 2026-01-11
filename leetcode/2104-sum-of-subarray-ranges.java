import java.util.*;
// Good question for practise as it covers lots of different concepts and implementations.
class Solution {
    public long subArrayRanges(int[] nums) {
        long minSubArraySum = findMinSubArraySum(nums);
        long maxSubArraySum = findMaxSubArraySum(nums);
        //System.out.println("minSubArraySum: " + minSubArraySum + ", maxSubArraySum: "+ maxSubArraySum);
        return maxSubArraySum - minSubArraySum;
    }

    // This uses PGEE and NGE
    private long findMaxSubArraySum(int[] nums) {
        long total = 0;
        int[] pgee = previousGreaterOrEqual(nums);
        int[] nge = nextGreater(nums);
        // System.out.println("pgee: " + Arrays.toString(pgee));
        // System.out.println("nge: " + Arrays.toString(nge));

        for (int i=0; i<nums.length; i++) {
            long freq = (i-pgee[i]) * (nge[i]-i);
            total += freq * nums[i];
        }

        return total;
    }

    private int[] previousGreaterOrEqual(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] pgee = new int[nums.length];
        
        for (int i=0; i<nums.length; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
                st.pop();
            }
            pgee[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return pgee;
    }

    private int[] nextGreater(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[nums.length];
        
        for (int i=nums.length-1; i>=0; i--) {
            while (!st.isEmpty() && nums[st.peek()] <= nums[i]) {
                st.pop();
            }
            nge[i] = st.isEmpty() ? nums.length : st.peek();
            st.push(i);
        }

        return nge;
    }

    // This uses PSEE and NS
    private long findMinSubArraySum(int[] nums) {
        long total = 0;
        int[] psee = previousSmallerOrEqual(nums);
        int[] ns = nextSmaller(nums);
        // System.out.println("psee: " + Arrays.toString(psee));
        // System.out.println("ns: " + Arrays.toString(ns));

        for (int i=0; i<nums.length; i++) {
            long freq = (i-psee[i]) * (ns[i]-i);
            total += freq * nums[i];
        }

        return total;
    }

    private int[] previousSmallerOrEqual(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] psee = new int[nums.length];
        
        for (int i=0; i<nums.length; i++) {
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) {
                st.pop();
            }
            psee[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        return psee;
    }

    private int[] nextSmaller(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] ns = new int[nums.length];
        
        for (int i=nums.length-1; i>=0; i--) {
            while (!st.isEmpty() && nums[st.peek()] >= nums[i]) {
                st.pop();
            }
            ns[i] = st.isEmpty() ? nums.length : st.peek();
            st.push(i);
        }

        return ns;
    }
}