class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        double left = 0.0, right = findMaxDist(stations)*1.0;
        //System.out.println("left: " + left + " , right:" + right);
        while (right - left > 1e-6) {
            double mid = 1.0*left + (right - left)/2.0;
            boolean isPossible = checkDist(stations, k, mid);
            //System.out.println("Result => ("+left+", "+right+")" + " mid is " + mid + ", isPossible: " + isPossible);
            if (isPossible) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private int findMaxDist(int[] stations) {
        int mx = 0;
        for (int i=1; i<stations.length; i++) {
            mx = Math.max(mx, stations[i]-stations[i-1]);
        }
        return mx;
    }

    private boolean checkDist(int[] stations, int k, double mid) {
        //System.out.println("\ncheckDist called with k="+k+" , mid="+mid);
        int cnt = 0;
        for (int i = 1; i<stations.length; i++) {
            int diff = stations[i] - stations[i-1];
            int numSt = (int)(diff/mid);
            if (isPerfectDivision(diff, mid)) {
                numSt--;
            }
            //System.out.println("isPerfectDivision:  " + isPerfectDivision(diff, mid));
            //System.out.println("numSt: "+numSt);
            cnt += numSt;
            if (cnt > k) {
                return false;
            }
        }
        //System.out.println("required cnt stations: " + cnt);
        return cnt <= k;
    }

    public static boolean isPerfectDivision(double dividend, double divisor) {
        // Check for division by zero or invalid inputs (NaN, Infinity)
        if (divisor == 0.0 || Double.isNaN(dividend) || Double.isNaN(divisor) || Double.isInfinite(dividend) || Double.isInfinite(divisor)) {
            return false; // Or handle as appropriate for your use case
        }

        double remainder = dividend % divisor;
        
        // Define a small tolerance (epsilon)
        double epsilon = 1e-9; // 0.000000001
        
        // Check if the remainder is close enough to zero
        return Math.abs(remainder) < epsilon;
    }
}