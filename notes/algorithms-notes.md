
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

# Time complexity of DFS/BFS
O(E+N)

>> Important Learning
If edge list is given and you have to find shortest distance from src to all nodes, then prefer Bellman Ford Algorithm. It is much simpler to implement and understand.



# Moore's voting algorithm

Imagine a battlefield where different numbers represent different armies. 
When two different numbers meet, they fight and eliminate each other (1-to-1 cancellation).
Since the true majority element makes up more than half of the entire population, it can fight all other elements combined and still have at least one survivor standing at the end.

- Realisation: In lots of array question, you dont need to save prefix sum entire array. you can just loop and calculate total sum till now and apply the algo.


# Shell Sort
- Sort array by taking multiple iterations. first floor(n/2) distance elements are compared and swaped and then n/4, ...
- This algo is better than insertion sort


>> Revise merge sort and quick sort - these are some of the most important algorithms




