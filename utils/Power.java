package utils;

public class Power {
    long mod = 1000000007;

    // use fast exponentiation to calculate x^y % mod
    public long power(int x, long y) {
        long ret = 1;
        long mul = x;
        while (y > 0) {
            if (y % 2 == 1) {
                ret = (ret * mul) % mod;
            }
            mul = (mul * mul) % mod;
            y /= 2;
        }

        return ret;
    }
}

