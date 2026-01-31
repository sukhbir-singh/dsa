import java.util.*;
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // sort by endTime
        Arrays.sort(intervals, (arr1, arr2) -> arr1[1] - arr2[1]);

        int curEnd = Integer.MIN_VALUE;
        int ans = 0;
        for (int i=0; i<intervals.length; i++) {
            if (curEnd > intervals[i][0]) {
                ans++;
            } else {
                curEnd = intervals[i][1];
            }
        }

        return ans;
    }
}