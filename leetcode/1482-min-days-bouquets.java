class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int left = 1, right = findMax(bloomDay);
        if ((long)m*(long)k > (long)bloomDay.length) {
            return -1;
        }

        int ans = (int)right;
        while (left <= right) {
            int mid = left + (right - left)/2;
            boolean possible = calc(bloomDay, mid, m, k);

            if (!possible) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }

    private int findMax(int[] bloomDay) {
        int max = bloomDay[0];
        for (int n: bloomDay) {
            max = Math.max(n, max);
        }
        return max;
    }

    private boolean calc(int[] bloomDay, int days, int m, int k) {
        boolean[] flow = new boolean[bloomDay.length];
        for (int i = 0; i<bloomDay.length; i++) {
            flow[i] = bloomDay[i] <= days ? true : false;
        }
        //System.out.println("\ndays: "+ days);
        //System.out.println(Arrays.toString(flow));

        long adjs = 0;
        long cur = 0;
        for (boolean f: flow) {
            if (f) {
                cur++;
                if (cur == k) {
                    adjs++;
                    cur = 0;
                }
            } else {
                cur = 0;
            }
        }

        //System.out.println("adjs: "+ adjs);
        return adjs >= m;
    }
}