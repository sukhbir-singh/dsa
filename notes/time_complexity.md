
Very Important Article: https://leetcode.com/discuss/post/6199077/time-complexity-of-common-algorithms-and-tv22/


# Priority Queue

- add(): O(log n) — Inserting a new element may require "bubbling up" to the correct position.

- remove(): O(log n) — Removing the head requires "bubbling down" the new root to maintain heap order.contains(Object): O(n) — Requires a linear search through the underlying array. 

- peek(): O(1) - Root Access: The highest-priority element (the minimum in a min-heap or maximum in a max-heap) is always stored at the root of the heap. Direct Retrieval: Because the root is always at a fixed position (typically index 0 or 1 in the underlying array), the peek() operation simply returns that element without modifying the heap, which takes constant time.


# Dynamic Programming
Time complexity = number of states × work per state.


# Graph Algorithms

| Algorithm | Worst-Case Time Complexity | Space Complexity | Core Use Case / Characteristics |
| --- | --- | --- | --- |
| Breadth-First Search (BFS) | `O(V + E)` | `O(V)` | Level-by-level traversal; shortest path on unweighted graphs. |
| Depth-First Search (DFS) | `O(V + E)` | `O(V)` | Deep traversal; connectivity, backtracking, maze generation. |
| Topological Sort | `O(V + E)` | `O(V)` | Linear ordering of vertices for Directed Acyclic Graphs (DAGs). |
| Dijkstra's Algorithm | `O((V + E) log V)` | `O(V)` | Single-source shortest path (non-negative weights) via Min-Priority Queue. |
| Bellman-Ford Algorithm | `O(V * E)` | `O(V)` | Single-source shortest path; handles negative weights and detects negative cycles. |
| Floyd-Warshall Algorithm | `O(V^3)` | `O(V^2)` | All-pairs shortest path; uses dynamic programming with a distance matrix. |
| Prim's Algorithm | `O((V + E) log V)` | `O(V)` | Minimum Spanning Tree (MST); grows tree from a root node via Min-Heap. |
| Kruskal's Algorithm | `O(E log E)` or `O(E log V)` | `O(V + E)` | Minimum Spanning Tree (MST); sorts edges and merges components with Union-Find. |
| Kosaraju's / Tarjan's | `O(V + E)` | `O(V)` | Finding Strongly Connected Components (SCCs) in directed graphs. |
