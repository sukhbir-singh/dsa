import java.util.*;

// good application of monotonic queue
// thinking process is by applying the logic of maintaining highest elements in a datastructure
// could use priority queue, but deque based solution is more optimized
// In this implementation, we have used "monotonic decreasing queue"
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
/*
>> Example:
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation: 
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Deque trace (front -> back):
i | Value | Deque indices | Deque values | Window max
--|-------|---------------|--------------|-----------
0 | 1     | [0]           | [1]          | -
1 | 3     | [1]           | [3]          | -
2 | -1    | [1, 2]        | [3, -1]      | 3
3 | -3    | [1, 2, 3]     | [3, -1, -3]  | 3
4 | 5     | [4]           | [5]          | 5
5 | 3     | [4, 5]        | [5, 3]       | 5
6 | 6     | [6]           | [6]          | 6
7 | 7     | [7]           | [7]          | 7
*/
