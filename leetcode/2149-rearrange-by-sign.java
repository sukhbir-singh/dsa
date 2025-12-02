class Solution {
    public int[] rearrangeArray(int[] nums) {
        int[] nums1 = new int[nums.length];

        int pIndex = 0, nIndex = 1;
        for (int i=0; i<nums.length;i++) {
            if (nums[i] >= 0) {
                nums1[pIndex] = nums[i];
                pIndex += 2;
            } else {
                nums1[nIndex] = nums[i];
                nIndex +=2;
            }
        }

        return nums1;
    }
}