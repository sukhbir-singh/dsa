
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

Character.isDigit(ch) -> To check if character is digit or not.

- (Very Important) In Java, -5%2 == -1 and not 1. Please remember this.

- In m^n power question, remember to handle case when power is zero.

- Maths: In a string of digits, there are n/2 odd places and (n+1)/2 even places. both with floor value.

- In java, one good way to write big integers those are powers of 10 is by using e. But remember that it will always return double.
Shortcut for remembering e = 1o^

So in this Example:-

1e9 has value: 1 x 10^9 (one billion, or 1,000,000,000). Data Type: By default, it is a double.
10e9 has value: 10 x 10^9 (ten billion, or 10,000,000,000).

So if question asked -  answer modulo 10^9 + 7 => You should do ans % (1e9 + 7)


