import java.util.*;
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);

        List<int[]> result = new ArrayList<>();
        int[] prev = new int[] {intervals[0][0], intervals[0][1]};

        for (int i=1; i<intervals.length; i++) {
            if (prev[1] >= intervals[i][0]) {
                prev[0] = Math.min(prev[0], intervals[i][0]);
                prev[1] = Math.max(prev[1], intervals[i][1]);
            } else {
                result.add(prev);
                prev = intervals[i];
            }
        }

        // if prev and last element are seperate then push only last
        // as prev must have already pushed
        // otherwise merge and push
        result.add(prev);

        int[][] arr = new int[result.size()][2];
        for (int i=0; i<result.size(); i++) {
            arr[i] = result.get(i);
        }

        return arr;
    }
}