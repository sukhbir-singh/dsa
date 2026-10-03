import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheWithLinkedHashMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private final int capacity;
    private final Map<K, V> cache;

    public LRUCacheWithLinkedHashMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        this.capacity = capacity;
        // accessOrder = true: iteration order is least-recently accessed to most-recently accessed
        this.cache = new LinkedHashMap<>(capacity, LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRUCacheWithLinkedHashMap.this.capacity;
            }
        };
    }

    public V get(K key) {
        return cache.get(key);
    }

    public void put(K key, V value) {
        cache.put(key, value);
    }

    public int size() {
        return cache.size();
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    public static void main(String[] args) {
        LRUCacheWithLinkedHashMap<Integer, String> lru = new LRUCacheWithLinkedHashMap<>(3);

        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");
        System.out.println(lru);

        lru.get(1);
        System.out.println(lru);

        lru.put(4, "D");
        System.out.println(lru);
        System.out.println("Key 2: " + lru.get(2));
    }
}
