// definitely hard problem: thought process and implementation both are tough
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 > len2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0, right = len1;
        // bucket size - number of elements on each side
        int bucketSize = (len1+len2+1)/2;

        double ans = 0.0;
        // remember that we are doing binary search on count of numbers to
        // pick from first array
        while (left <= right) {
            int mid1 = (left + right)/2; // count of numbers
            int mid2 = bucketSize - mid1; // remaining count

            int l1 = Integer.MIN_VALUE; // left in first array
            int l2 = Integer.MIN_VALUE; // left in second array
            int r1 = Integer.MAX_VALUE; // right in first array
            int r2 = Integer.MAX_VALUE; // right in second array

            // these are actual positions in arrays
            l1 = mid1-1 >= 0 ? nums1[mid1-1] : l1;
            r1 = mid1 < len1 ? nums1[mid1] : r1;
            l2 = mid2-1 >= 0 ? nums2[mid2-1] : l2;
            r2 = mid2 < len2 ? nums2[mid2] : r2;

            if (l1 <= r2 && l2 <= r1) {
                // we will get the answer
                if ((len1 + len2) %2 == 0) {
                    return 1.0*(Math.max(l1, l2) + Math.min(r1, r2))/2;
                } else {
                    return 1.0*Math.max(l1, l2);
                }

            } else if (l1 > r2) {
                right = mid1 - 1;
            } else {
                left = mid1 + 1;
            }
        }
        return ans;
    }
}