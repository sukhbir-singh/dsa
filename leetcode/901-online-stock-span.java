import java.util.*;

// Improvement: we can also use single stack with pair of value {index, value}
class StockSpanner {
    private Stack<Integer> sti; // for finding previous greater index
    private Stack<Integer> stv; // for finding previous greater value
    private int index;

    public StockSpanner() {
        index = -1;
        sti = new Stack<>();
        stv = new Stack<>();
    }
    
    public int next(int price) {
        index++;
        while (!stv.isEmpty() && stv.peek() <= price) {
            stv.pop();
            sti.pop();
        }

        // if not found, then taking -1 for considering one element before first element
        int res = sti.isEmpty() ? -1 : sti.peek();
        stv.push(price);
        sti.push(index);
        return index - res;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */