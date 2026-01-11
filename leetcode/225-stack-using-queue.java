import java.util.*;
class MyStack {
    private Queue<Integer> q;
    private int size;

    public MyStack() {
        q = new LinkedList<>();
        size = 0;
    }
    
    // O(n)
    public void push(int x) {
        q.add(x);
        size++;
        moveElementsToBack();
    }
    
    public int pop() {
        size--;
        return q.remove();
    }
    
    public int top() {
        return q.peek();
    }

    private void moveElementsToBack() {
        int sz = size - 1;
        while (sz > 0) {
            q.add(q.remove());
            sz--;
        }
    }
    
    public boolean empty() {
        return size == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */