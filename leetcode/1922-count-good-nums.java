class Solution {
    public int countGoodNumbers(long n) {
        long mod = 1000000007;

        // 5^ even places are (n+1)/2
        long evenNums = power(5, (n+1)/2);
        // 4^ odd places are n/2
        long oddNums = power(4, n/2);

        return (int)((evenNums * oddNums) % mod);
    }

    private long power(long x, long y) {
        long result = 1;
        long mod = 1000000007;

        while (y != 0) {
            if (y % 2 == 1) {
                result = (result * x) % mod;
                y--;
            } else {
                x = (x * x) % mod;
                y = y/2;
            }
        }

        return result;
    }
}