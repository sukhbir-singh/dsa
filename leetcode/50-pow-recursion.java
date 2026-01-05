class Solution {
    // handling negative number is little tricky
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        } else if (n == -1) {
            return 1.0/x;
        }
        
        double ans;
        // remember that negative number modulus is negative in java
        if (Math.abs(n) %2 == 1) {
            int half = n > 0 ? (n-1)/2 : (n+1)/2;
            ans = myPow(x, half);

            double m = n > 0 ? x : (1.0/x);
            return ans * ans * m;
        } else {
            ans = myPow(x, n/2);
            return ans * ans;
        }
    }
}

// Simpler solution
class Solution2 {
    public double myPow(double x, int n) {
        if (n==0) {
            return 1;
        } else if (n==1) {
            return x;
        } else if (n == -1) {
            return 1.0/x;
        }
        
        double ans = myPow(x, n/2);
        ans = ans*ans;
        if (Math.abs(n%2) == 1) {
            ans = n>0? ans*x : ans*1.0/x;
        }
        return ans;
    }
}
