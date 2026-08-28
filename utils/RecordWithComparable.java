package utils;
import java.util.*;

public class RecordWithComparable {
    public record Product(String id, String name, double price) implements Comparable<Product> {
        @Override
        public int compareTo(Product p) {
            return (p.price - this.price) > 0 ? 1 : -1;
        }
    }
    
    public static void main(String[] args) {
        var p1 = new Product("a", "A", 12);
        var p2 = new Product("b", "B", 30);
        var p3 = new Product("c", "C", 15);
        
        PriorityQueue<Product> pq = new PriorityQueue<>();
        pq.add(p1);
        pq.add(p2);
        pq.add(p3);
        
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
    }
}
/*
Output:
Finished in 56 ms
Product[id=b, name=B, price=30.0]
Product[id=c, name=C, price=15.0]
Product[id=a, name=A, price=12.0]
*/
