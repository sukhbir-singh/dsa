
# Cycle detection in Directed Graphs

In directed graphs, seeing an already visited node is ambiguous.

It could be:
1. A back edge to an ancestor in the current DFS path, which means cycle.
2. An edge to a node that was fully processed earlier from another path, which is not a cycle.

So You should use visted + InStack boolean arrays for this.

And encountering node which is already present in Instack means cycle is detected.

Equivalent alternative to visited + inStack:
use one 3-state color array.

0 = unvisited
1 = visiting (in current stack)
2 = done
Encountering color 1 means cycle.

So your current approach is correct and standard for directed graphs.
