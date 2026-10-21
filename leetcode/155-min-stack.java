import java.util.*;
// This below implemented solution is not correct according to the asked question because the algorithm is taking O(logN) time insted of O(1) time.
// Instead of this - right approach is to use one stack with pair (value + min), or 2 stacks or 2 stacks (one with pair (min+count)). References: Leetcode solution.

// Two stack approach is also very good. Two ways to implement it: in second stack, add min element only when more minimum is observed, not always.
// so size of original stack and stack with minimum stack will not be same. But if you notice you will end up add duplicate values of minimums in 
// that stack. Better way would be to use pair in this minimums stack which will record - min_value with frequency.
class MinStack {
    private Node head;
    private Queue<Integer> pq; // for storing minimums value

    public MinStack() {
        pq = new PriorityQueue<>();
    }
    
    public void push(int val) {
        pq.add(val);  // O(logn)

        Node n = new Node(val);
        n.next = head;
        head = n;
    }
    
    public void pop() {
        int val = head.val;
        pq.remove(val);  // O(logn)
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return pq.peek();  // O(1)
    }
}

class Node {
    public int val;
    public Node next;

    public Node(int val) {
        this.val = val;
    }
    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

class MinStack2 {
    private Stack<int[]> stack; // better to use queue instead of direct stack

    public MinStack2() {
        stack = new Stack<>();
    }

    public void push(int x) {
        int currentMin = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek()[1];
        stack.push(new int[] { x, Math.min(x, currentMin) });
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}