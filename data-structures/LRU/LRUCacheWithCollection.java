import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
// Note:-
// 1. Performance: Using list.remove(key) on a standard java.util.LinkedList takes O(N) time because it must search for the element. For true O(1) performance (common in LeetCode 146), developers typically implement a custom Doubly Linked List.
// 2. The "Pro" Way: In production, the standard choice is LinkedHashMap. By passing true for accessOrder in its constructor and overriding removeEldestEntry, you get a built-in, optimized LRU cache.

class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> map;
    private final LinkedList<K> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new LinkedList<>();
    }

    public V get(K key) {
        if (!map.containsKey(key)) return null;
        
        // Mark as recently used: remove from current position and add to front
        list.remove(key); 
        list.addFirst(key);
        return map.get(key);
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            // Update existing: remove old key position to move it to front
            list.remove(key);
        } else if (list.size() >= capacity) {
            // Evict LRU: remove the last element from both list and map
            K lruKey = list.removeLast();
            map.remove(lruKey);
        }
        
        // Add new/updated entry to front
        list.addFirst(key);
        map.put(key, value);
    }
}
