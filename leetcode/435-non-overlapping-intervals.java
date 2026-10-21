import java.util.*;
// standard question - overlapping intervals
// current solution: sort by end time and remove conflicting intervals.
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

// another solution: sort by start time and resolve conflict by choosing minimum endTime between two intervals.
// second one is more intuitive and easier to explain
class Solution2 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (arr1, arr2) -> arr1[0] - arr2[0]);

        int prev = 0;
        int ans = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[prev][1]) { // conflict
                ans++;
                if (intervals[i][1] < intervals[prev][1]) {
                    prev = i; // pick one having smaller end time
                }
            } else {
                prev = i;
            }
        }

        return ans;
    }
}
