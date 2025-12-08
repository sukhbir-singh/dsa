package utils;

public class DoubleNumbers {
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
