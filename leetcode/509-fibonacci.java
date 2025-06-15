import java.util.*;
class Solution {
    private Map<Integer, Integer> cache = new HashMap<>();

    public int fib(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        if (n<=1) return n;
        int result = fib(n-1) + fib(n-2);
        cache.put(n, result);
        return result;
    }
}