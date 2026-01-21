class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int[] ps = new int[cardPoints.length];
        ps[0] = cardPoints[0];
        for (int i=1; i<k; i++) {
            ps[i] = ps[i-1] + cardPoints[i];
        }

        int[] ss = new int[cardPoints.length];
        ss[cardPoints.length - 1] = cardPoints[cardPoints.length - 1];
        for (int i=1; i<k; i++) {
            int index = cardPoints.length - 1 - i;
            ss[index] = ss[index+1] + cardPoints[index];
        }

        //System.out.println(Arrays.toString(ps));
        //System.out.println(Arrays.toString(ss));

        // now just takes two points and find the maximum sum of two points
        // i represents number of elements picked from left side and remaining picked from right side
        int mx = 0;
        for (int i = 0; i<=k; i++) {
            int leftIndex = i-1;
            int rightIndex = cardPoints.length - 1 - (k-1) + i;

            int sum = 0;
            if (leftIndex >= 0 && leftIndex < cardPoints.length) {
                sum += ps[leftIndex];
            }
            if (rightIndex >= 0 && rightIndex < cardPoints.length) {
                sum += ss[rightIndex];
            }
            //System.out.println("("+leftIndex+", "+rightIndex+") having sum = " + sum );
            mx = Math.max(mx, sum);
        }
        
        return mx;
    }
}
