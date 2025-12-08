class Solution {
    public int findKthPositive(int[] arr, int k) {
        int pos = -1; // minimum position after which miss is there in array
        int left = 0, right = arr.length-1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            int totalMiss = arr[mid] - (mid + 1);
            if (totalMiss < k) {
                pos = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // right side of pos will have to missing answer
        // totalMiss = arr[] - (pos + 1)

        if (pos == -1) {
            // it means that missing is even before first position
            return k;
        }

        int currentMiss = arr[pos] - (pos + 1);
        int remainingMiss = k - currentMiss;
        return arr[pos] + remainingMiss;
    }
}