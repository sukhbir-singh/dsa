import java.util.*;
// This below implemented solution is not correct according to the asked question because the algorithm is taking O(logN) time insted of O(1) time.
// Instead of this - right approach is to use one stack with pair, or 2 stacks or 2 stacks (one with pair). References: Leetcode solution.
class MinStack {
    private class Node {
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

    private Node head;
    private Queue<Integer> pq;
    private int size;

    public MinStack() {
        pq = new PriorityQueue<>();
        size = 0;
    }
    
    public void push(int val) {
        size++;
        pq.add(val);  // O(logn)

        Node n = new Node(val);
        n.next = head;
        head = n;
    }
    
    public void pop() {
        size++;
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

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */