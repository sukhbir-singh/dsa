package utils;

// record is only available in java 16+
// 1. Definition of the 'Point' record
// It automatically creates fields for x and y, 
// a canonical constructor, and accessor methods (point.x() and point.y()).
record Point(int x, int y) {
    // Records can also include custom methods if needed:
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}

// 2. Usage in another class or method
public class RecordExample {
    public static void main(String[] args) {
        // Instantiate a Point using the generated constructor
        Point p1 = new Point(5, 10);
        
        // Access fields using the generated accessor methods
        System.out.println("Point Coordinates: (" + p1.x() + ", " + p1.y() + ")");
        
        // Use the custom method
        System.out.println("Distance from origin: " + p1.distanceFromOrigin());
        
        // Records have a useful toString() method automatically generated
        System.out.println(p1.toString()); // Output: Point[x=5, y=10]
        
        // Records have equals() and hashCode() automatically generated
        Point p2 = new Point(5, 10);
        System.out.println("p1 equals p2? " + p1.equals(p2)); // Output: true
    }
}
