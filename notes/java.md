
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

// For dynamic string and characters concatinations. use string builder.

StringBuilder sb = new StringBuilder();
sb.append(ch);
sb.toString();

// java substring method - remember that endIndex is excluded.
substring(int beginIndex, int endIndex)

// Java method reference
The :: syntax is a method reference operator introduced in Java 8. It provides a concise way to refer to a method without executing it immediately or providing its implementation details (which is the purpose of a lambda expression).
The :: operator allows you to treat a method name as a value that can be passed around to methods that expect a functional interface (like the function expected by Comparator.comparingInt()).

Comparator<Movie> yearDescendingComparator = Comparator.comparingInt(Movie::getYear).reversed();

Remember, Strings are Immutable!