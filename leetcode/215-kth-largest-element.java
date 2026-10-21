import java.util.*;
// other way: QuickSelect algorithm, refere QuickSelect.java file
// count sort can also be used
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>();
        for (int num: nums) {
            heap.add(num);
            if (heap.size() > k) {
                // this will keep heap size smaller and makes overall algorithm efficient
                // this changes overall time complexity to O(n⋅logk)
                heap.remove();
            }
        }
        return heap.peek();
    }
}