
# Priority Queue

- add(): O(log n) — Inserting a new element may require "bubbling up" to the correct position.
- remove(): O(log n) — Removing the head requires "bubbling down" the new root to maintain heap order.contains(Object): O(n) — Requires a linear search through the underlying array. 
- peek(): O(1) - Root Access: The highest-priority element (the minimum in a min-heap or maximum in a max-heap) is always stored at the root of the heap. Direct Retrieval: Because the root is always at a fixed position (typically index 0 or 1 in the underlying array), the peek() operation simply returns that element without modifying the heap, which takes constant time.

