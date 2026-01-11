package collections;
import java.util.*;

public class StackQueue {
    public static void main(String[] args) {
        Stack<Integer> st= new Stack<>();
        st.push(3);
        st.push(5);
        System.out.println("Stack: " + st);
        System.out.println(st.pop());
        System.out.println("Stack: " + st);

        System.out.println();

        Queue<Integer> q= new LinkedList<>();
        q.add(12);
        q.add(15);
        System.out.println("Queue: " + q);
        System.out.println(q.remove());
        System.out.println("Queue: " + q);
    }
}
