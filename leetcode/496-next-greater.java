import java.util.*;
// This is a standard question of monotonic stack. Refer Striver's video for solution.
// one good observation i reliased after implementation is that we do not need to keep the index.
// This question can be solved by iterating in either directions on array.
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<int[]> st = new Stack<>();
        Map<Integer, Integer> mp = new HashMap<>(); // keeping index positions
        
        int grt[] = new int[nums2.length];
        for (int i = 0; i<nums2.length; i++) {
            grt[i] = -1;
            mp.put(nums2[i], i);
        }

        st.push(new int[]{ nums2[0], 0 });
        // i dont like approch is moving from left to right for finding next greater
        // based on my understanding i feel, moving right to left is right approach for finding NGE
        for (int i = 1; i<nums2.length; i++) {
            int[] top = st.peek();

            if (nums2[i] <= top[0]) {
                st.push(new int[]{ nums2[i], i });
            } else {
                // remove and set all nums smaller than current num
                while (!st.isEmpty()) {
                    top = st.peek();
                    if (top[0] < nums2[i]) {
                        grt[top[1]] = nums2[i];
                        st.pop();
                    } else {
                        break;
                    }
                }

                // add current num to stack
                st.push(new int[]{ nums2[i], i });
            }
        }

        int res[] = new int[nums1.length];
        for (int i = 0; i<nums1.length; i++) {
            int index = mp.get(nums1[i]);
            res[i] = grt[index];
        }

        return res;
    }
}

// iterating in right direction for finding next greater
// i feel this is much more intuitive and thus better
class Solution2 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>(); // should store indices
        Map<Integer, Integer> mp = new HashMap<>(); // keeping index positions
        
        int grt[] = new int[nums2.length];
        for (int i = 0; i<nums2.length; i++) {
            grt[i] = -1;
            mp.put(nums2[i], i);
        }

        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && nums2[st.peek()] <= nums2[i]) {
                st.pop();
            }
            if (!st.isEmpty()) {
                grt[i] = nums2[st.peek()];
            }
            st.push(i);
        }

        int res[] = new int[nums1.length];
        for (int i = 0; i<nums1.length; i++) {
            int index = mp.get(nums1[i]);
            res[i] = grt[index];
        }

        return res;
    }
}
