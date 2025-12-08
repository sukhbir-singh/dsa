
double scientificValue = 1e6; // Represents 1 * 10^6
double result = Math.pow(10, 6);


// checking if divisible numbers are perfectly divisible or not

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

// Example Usage:
System.out.println(isPerfectDivision(10.0, 5.0)); // true
System.out.println(isPerfectDivision(1.0, 0.1));   // true (handles precision error)
System.out.println(isPerfectDivision(10.0, 3.0)); // false

