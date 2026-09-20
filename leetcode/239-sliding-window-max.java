import java.util.*;

// good application of monotonic queue
// thinking process is by applying the logic of maintaining highest elements in a datastructure
// could use priority queue, but deque based solution is more optimized
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>(); // we will keep indices and not actual number
        List<Integer> res = new ArrayList<>();

        // create first window
        for (int i=0; i<k; i++) {
            while (!dq.isEmpty() && nums[dq.getLast()] <= nums[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
        }
        res.add(nums[dq.getFirst()]);

        // slide window
        for (int i=k; i<nums.length; i++) {
            if (!dq.isEmpty() && dq.getFirst() == i-k) {
                dq.removeFirst();
            }
            while (!dq.isEmpty() && nums[dq.getLast()] <= nums[i]) {
                dq.removeLast();
            }
            dq.addLast(i);
            res.add(nums[dq.getFirst()]);
        }

        // convert list to result
        int[] arr = new int[res.size()];
        for (int i=0; i<res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}