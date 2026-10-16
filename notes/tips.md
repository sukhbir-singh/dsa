
- when sending array indexes as parameters, better to send actual inclusive indexes. This will help in avoiding out of index errors.

- always calculate mid point using subtraction, otherwise addition can cause out of range for integer.
`int mid = st + (end-st)/2;`

- reverse array algorithm.
// remember that while reversing you have to iterate to only half
for (int i=0; i<totalElements/2; i++) {
    swap(nums, i, nums.length-1-i);
}

- (Very Important) Difference between two elements in cummulative sum array = Sum of elements between those positions in the original array. (left position excluded and right position included)

- Nice article on binary search techniques - https://leetcode.com/problems/binary-search/editorial
Mainly there are three techniques: exact match, upper bound, lower bound.

Update: But Striver's video is better. Very easy implementation and it is consistent as well.
Video link: https://www.youtube.com/watch?v=6zhGS79oQ4k

- In some question, to eliminate the edge cases you can do some preprocessing like moving pointers etc.

- Remember that when dividing if you are expecting the answer to be in double then multiply it by 1.0 beforehand.

- Also integer multiplication or addition can go out of range. So remember to keep things in long while calculating.

- Remember the approaches for rotating an array by fixed positions and rotating string by fixed positions. This is helpful in lots of questions.

- Remember that integer range is from [-2^31, 2^31 - 1]

- Note: Integer.MAX_VALUE + 1 will be a negative value. For correct calculation, you should convert it to long and then use. like this:
(long)Integer.MAX_VALUE + 1

- for checking palindrom in string => expand from centers or start from both edges towards centre (preferred)

- In linkedin list questions related to deleting of nodes, implement separate methods for deleting head, tail and other middle nodes. Because handling of head, tail nodes is little different than common nodes and in kth node deletion, you can use deleteHead and deleteTail method to make things simple.

- While deleting kth node in linkedlist, makes sure that you also set the pointers to null for the deleted node as well. Other it can cause some issues later on while iterating.

- In LL questions, mostly cases are when handling head node and tail node.

- In LL, while implementing insertNode, deleteNode methods - always remember to return the new head. Because in the methods execution - head could have changed.

- In lots of LL questions, you will be given that you never have to insert before Head, otherwise head will be changed. Clarify this before implementing such questions.

- Head can also be null when there is no elements in the LL. So always handle this case at the start of the method. (Remember this - This needs to be done in every solution)

- For reversing a DLL, for each node it is just swapping of next and prev pointers.

- (Important) In linkedlist question, if you want to traverse till mid point of the linked list. Make sure you set the conditions correctly as shown below.

ListNode fast = head;
ListNode slow = head;
// trick: dont let fast jump beyond linked list bounday
while (fast.next != null && fast.next.next != null) {    // With these conditions, slow pointer will stop before the second half of the linked list
    fast = fast.next.next;
    slow = slow.next;
}
return slow;


- In linked list question, if we need to process from last node to the first node then we should reverse the linked list. If interviewer do not want us to reverse the linked list, then next approach we can try is using the recursion.
Example: you may need to pass back carry from recusive call in linked list. That's why, linked list + recursion is useful in lots of questions.


- Important: In linkedlist find middle element method, if you want to go to node just before middle element then set the fast pointer to .next.next so that one step is skipped. Striver taught this in his video. Very good trick. And in lots of questions, you want to go to node just before the middle node. Like delete middle node question, merge sort on linked list question, etc.

private ListNode findMiddle(ListNode head) {
    if (head == null) {
        return null;
    } else if (head.next == null) {
        return head;
    }

    // Important: if we want element before middle - ensure that you take f as next.next
    // trick - start f one step forward
    ListNode s = head, f = head.next.next;
    while (f != null && f.next != null) {
        s = s.next;
        f = f.next.next;
    }
    return s;
}

-  Merge Sort is one of the efficient sorting algorithms that is popularly used for sorting the linked list.

- Reminder: Always think of all the cases before jumping to implementation.

- In Recursion questions, in order to generate all combinations remember to iterate over all elements of input array and then think of logic in the recursion for each element. index will be one of the parameter of recursion.

- In recursion question, whenever possible try to avoid creating lots of deep cloning as it makes the program slow. Instead you can try adding to list and removing to list after the recursion call.
Useful Method: list.removeLast();

- Hint: whenever there is a problem for printing combinations, or subsequences -> the first thing that strikes the mind should be recusion.
Sometimes useful: pick and not-pick method.

- When you have to find unique combination of list of numbers. Maybe you can add list to set so that it keeps only unique sequence. And later convert this set to list.


Good approach for finding unique combinations in arry containing duplicate integers summing to a target:

- (Important) In combination questions on array, for optimization - one very good trick that I learnt is that check if current array element is equals to previous index element. If yes, then do not proceed as it will result in same combination. Otherwise proceed.
So the idea is that if we want unique combinations as result. We should avoid processing same combinations again and again. And loop over entire array for picking each element is better that take or not-take approach.
// Skip duplicate numbers by checking if candidates[i] == candidates[i - 1] for i > index.

- Hint: Whenever in question, you were asked to find unique combinations remember that one good trick is to use Set at the start in main method. And to put combinations in that set and later convert them to list. One more thing: It's important to sort the array initially at the beginning, otherwise same combinations with different sequences will come up. like [1,1,4] , [1,4,1] => Inside set these two are different lists. right but actually logically these are same. That's why sorting the input list is very important.

- My understanding: In each recursion call, you are selecting a number for certain pick, like in first recursion call execution, you are picking first element of the combination, and then in second recursion execution you are picking 2nd element for the combination. In single recursion call if you pick same element, like 1 is selected as first pick and then 1 again selected as first pick and then are recursing then we are doing same work again and again. which can easily be avoided by using condition i > index && arr[i] != arr[i-1]

A simple way to remember it:
- Duplicate numbers can appear together in one combination, but they should not independently start identical branches at the same recursion level.

[]
├── choose first 1
│   └── [1]
│       └── choose second 1
│           └── [1, 1] ✓
│
├── choose second 1
│   └── SKIP
│       It would repeat the branch beginning with the first 1.
│
└── choose 2
    └── [2] ✓

Refer: FindCombinations.java


- This is also very useful in question when we need to keep pair type of things in datastructure like stack.
Stack<int[]> st = new Stack<>();
st.push(new int[]{ nums2[i], i });

>> Remember: 
jshell> Stack<int[]> st = new Stack<>();
st ==> []

jshell> st.push({1,3})
|  Error:
|  illegal start of expression
|  st.push({1,3})
|          ^

jshell> st.push(new int[]{1,3}).  // always try to use this pattern for defining array
$2 ==> int[2] { 1, 3 }


- Nice Trick: I just learnt that if you want to keep track of array position along with integer present there in stack, then no need to store pair of integers representing both of them. Instead just keep the array indices and that will represent both array element and the index at which the number is present.

- (Important) While implementing solutions ensure that you handled all the edges cases - positive, negative, greater, smaller, etc.

- Next greater elements, next smaller elements -> these are very important tools for other questions. These algorithms you should know by heart like DFS, BFS, etc.

- In many questions, when you think how to solve the problem using brute force - you will also get the hint how to solve it optimally in best possible way. So it is a great way to start solving a question.

- Try to make code as clean as possible. Most of the times when code does not looks clean, it introduces lots of unavoidable bugs.

- Remember: In an array, if you have two pointers l and r and are placed at two positions in the array, then the total elements present in the subarray starting from l to r is (r-l+1).

- Note: If you have all alphabet characters, then taking array for storing frequency is also a good option.
int[] freq = new int[26];

- Quickselect is a selection algorithm developed that finds the k-th smallest (or largest) element in an unordered array.
- Good to know: QuickSelect - pick pivot element, sort elements as left, mid and right. (basically partitions array)
- Why QuickSelect is faster than sorting? QuickSelect only continues into the partition containing the required element.

Time complexity: O(n) on average, O(n^2) in the worst case.
n + n/2 + n/4 + n/8 + ... = O(n)

- Counting sort is a non-comparison sorting algorithm. It can be used to sort an array of positive integers. Important note - it uses prefix sum on the count array to find final positions of the elements. This is very interesting use of prefix sum to find actual final positions on the ans array.

- It is also a good practice to iterate couple of examples to understand the way of solution and how it is working. This also gives direction to some patterns that can help in solving the problem.

- Useful: very nice pattern for priority queue: reprocess elements using a temporary list.
In short: temporarily remove values from Priority queue while building one group, keep them in list and then re-add values that still have unused copies so they can be processed in a later group.

Example: LC-846, This pattern appears in the Hand of Straights solution. The task is to split the cards into groups of consecutive numbers.

- One good technique, I learnt is that you just pick one element from the list like the first element of the array and then keep decreasing the number by 1 and find it in the map till the point it exists. In this way, you know the start of the sequence. This approach is helpful in many questions to find start of the sequences. And also help in coming up with most optimal solution.

In short, Start counting upward only from numbers whose predecessor (n - 1) is absent.
Example: LC-128

- Trick: In questions when you have to create a new linked list and return - Always remember to create a one node list first and then keep adding elements to it. In the end, return head.next. In short always keep a dummy node and add other nodes to it.

- Time complexity of heap is O(log n), where n is size of the heap. If we keep constant size of heap then time complexity remains consistent.

- In simple bracket matching question, you can just use an integer counter instead of taking whole stack based approach.

- Sometimes recursively iterating all solutions is also a brute force implementation.
Time complexity for recursion is (num_of_branches)^N

- "As time goes by" simulation is useful in many scheduling questions. Video: https://www.youtube.com/watch?v=AsGzwR_FWok
Idea: See from outside of the railway station, and note down the time when train arrives (+1) and departures (-1), the maximum counter value at any time is the minimum platforms required to accomodate all trains.

- I feel most of the greedy algorithms are intuition based. Think through the examples and you will find a way to solve them.

# Tree
- In Tree questions, what i found is that in most cases, the brute force is to traverse across entire tree and for each node - try to find the height of left child and height of right child. For finding height at that point, you have to traverse whole subtree. so that is what is taking time.

- For calculating diameter of the tree, for each node - you can return -1 from both empty child nodes and then add +2 to it. 
Note: diameter is maximum length between any 2 nodes in the tree.

- TreeInfo is a good variable name to encapsulate the extra informations that you want to send from each child node to the parent nodes.

- For comparing two trees, simulateous traversals in both trees at the same time is very good way to compare.

- Good method naming: if question is isSymmetrical, you name recursion method isSymmetricalHelper

- In tree, level order traversal is one of the important questions and is Frequently used in other questions as well.

- A common mistake in tree problems is confusing whether a map should store integer values or TreeNode references. Choose based on what you need to keep track of: use values when only the data matters, and node references when you need to access or identify the actual nodes.

- I found one more interesting way to implement tree - level order traversal. where you can use just one queue. Keep adding nodes to this queue along with a null entry. Whenever we see a null, it means one row is finished execution. In the loop, add condition for this null. 

- Another way is to use nested loop like for loop instead while loop. because we know that at any point, all nodes present in the queue belongs to particular level. All nodes are there. [This is a very good observation - remember this]

- One good tip: If you are dividing problem into small methods, and if you need to calculate some final list that aggregate result of all these methods. Then good practise is to define an empty list in main method and then pass that list as argument in all the smaller methods. So that they will add data in this list. This is much better that creating new list in each method and then combining.

- Remember: Recursive traveral in tree is much easier to implement as compared to iterative traversal.

- Thinking perspective: you should start seeing nodes in tree in columns for top-bottom view type questions.
Note: vertical line based thinking and traversal for tree is useful in other questions as well.

- For BST, both recursive and iterative approach is good and simple to implement. Because in each iteration you have to go to either side of the node.

- Note: When reversing the condition for falisy, make sure all symbols are reversed including >, <, then will become <= and >=

- BST Inorder property is very useful -> That is, inorder traversal of BST gives us sorted list.

- In BST questions, most of the times - you will be using the binary search property of tree nodes to solve the problem. 

- In BST, in most cases you assume that all values are unique. (Clarify this assumption in interviews.)

- Remember: Two sum problem on sorted array - can be solved easily using two pointer. This is very useful to solve 3sum, 4sum etc.

- Finding the swapped pair in the almost sorted array in O(n) time is an important trick! (Reference: https://leetcode.com/problems/recover-binary-search-tree/description/)
Logic: In sorted array, iterate from left to right. And whenever you find reverse numbers like nums[i] > nums[i+1], then update x and y. update x only once, but keep updating y till you reach end of the array.

The key idea is: keep the first x, but keep updating y until the final inversion. If the swapped elements were next to each other, there would only be one inversion, and that single inversion gives both values.
Example: nums = [1, 6, 3, 4, 5, 2, 7]

- Remember: In BFS, visit node and then add it to the queue. But In DFS, visit node in the first line of recursion method.

- Important: If you have to create adjancency list for a graph from scratch. Remember that after creating list of list of integer, you have to loop and push empty list to the adj list.

- Thought: If you just have to visit all nodes in a connected graph, prefer DFS simply because it is easy and stack is implicit. In BFS you have to code little more and also have to maintain queue data structure.

- V.Important: For Traversing in matrix questions, I think using directions array is a very good approach.

int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 4 side directions
for (int[] d : directions) {
    int neighborRow = row + d[0];
    int neighborCol = col + d[1];
...
}

- The first thing you should think about when it comes to shortest path problems on graphs is BFS.

- Bitwise tough question should give you hint that it might need Trie data structure.

- Very useful XOR property for bitwise,
Check if p1^p2 == currXor, i.e. p1 == currXor^p2   (because p2^p2 = 0 and 0^x = x)

- Trick:
When using record in java, if you want to sort by any particular value, use Collections.sort like this:

record Element(int x, int m, int i){};
Collections.sort(list, (e1, e2) -> e1.m() - e2.m());

- Interview Tip: Practice Overriding Your Brains "Assume" Mode!

- DP Tip:
// In DP, always always try to create 2D DP array for memoization. b/c map based dp is inefficient.
The logic might be correct, but the HashMap with Pair record keys is too slow. Each recursive call creates a new Pair object, and HashMap lookups involve hashing, autoboxing, and GC pressure. With nums.length up to 200 and sum/2 up to 20,000, that's up to 4 million states — manageable with an array, but costly with a HashMap.

- Subset Question:
In subset question, if you have to get two subsets such that all array elements are divided into these two subsets then its easy to just find all possible one subset and remaining elements are obviously parts of second subset. So you can do things asked in question without explicitely finding second subset.


# DP
// Very important: In DP question - take variables in parameter and result should be returned by the recursive method. dont start passing result in parameter, it will start giving cached wrong result.

In memoized DP (top-down): the parameters should define the state (the cache key), and the function should return the answer for that state. Never pass an accumulator (like count) as a parameter — it makes the return value path-dependent, but the cache key doesn't include it, so you get stale/wrong cached results.

The rule: If dp[index][amt] is your cache, then the return value must depend only on index and amt — nothing else. The moment you pass count and bake it into the return value, the cache breaks because different callers reach the same (index, amt) with different count values.

This applies to every top-down memoized DP problem. The pattern is always:

int solve(int ...stateVars) {
    if (memo[stateVars] != UNVISITED) return memo[stateVars];
    // compute answer using ONLY stateVars
    memo[stateVars] = answer;
    return answer;
}

No accumulators, no extra context from the caller — just state in, answer out.

- Note: Why greedy algorithm don't work in most of the cases is because of uniformity. Greedy algo tries to find global maxima, by apply local maxima. Which is not always correct because the dataset is not uniform. That is where we have to try out all possibilities using recusion + DP.

- Remember: In questions when infinite supply is there, and when you do take/notTake then for take do not move index - 1, stay there. like keep passing index in new recursion call.

# More DP Learning:
- In dp if your parameters are index and target sum and if sum is becoming negative in algorithm, then you should consider using larger array size for target index like Integer[][] dp = new Integer[nums.length][2*sum+1];
- Also if ans is becoming zero sometimes then use Integer instead of int because you cannot assign -1 to it initially. And Integer value is null by default which means unset in map.

- Observation: In DP string question, if you think about worst case of maximum answer possible, then you know you can optimize it using longest common subsequence type pattern on that question. Example: if you have to find minimum insertion for making string palindrom, then you know that if you place the reversed string after the input string, it will make the string palindrome (but it uses maximum insertions). So, maximum answer possible is length of string, but by finding the maximum longest subsequence between string and reversed string, you can decrease the answer from the maximum possible answer. This is the nice thought process.

- Take away: For minimum insertions to make a string a palindrome, find its longest palindromic subsequence. The answer is string length - LPS length.

- In String DP questions, when processing or going over the DP array at that time - remember to use 1 based index of string, it is very helpful and in bottom up DP as well. This will save lots of edge cases. otherwise in general top down DP approach - taking string indexes synced with DP arrays works.

- In memorization DP, you always have auxiliary space. For avoiding that, you should move to tabulation DP. Other than auxiliary space, both approaches use DP extra space.

# Graph

- In topo sort using DFS, it is sometimes helpful to use stack for keeping values of topological sortings. Because at the top you have node which is starting node of topo sort. 

Note: In DFS-based topological sort, you push a node on stack after recursively visiting all its neighbors — as the recursive call for that node is finishing. That is the DFS postorder step, which happens during backtracking.

- Remember: Dijkstra algorithm is nothing special but just an BFS algorithm with priority queue where each entry is {distance, point}. So it picks item which is smallest distance first and then process it like finding neighbours, etc.


>> Trick
This is very useful for moving to all directions in grid.
int[][] adj = {
            {1,0}, {0,1}, {-1,0}, {0,-1},
            {1,1}, {-1,-1}, {1,-1}, {-1,1}
        };

# Learnings
- For calculating total ways in a graph, you have to "add" ways from different paths.
- In undirected graphs, you need to add both edges from -> to and to -> from.
- For naming, use word node in graph questions and for adjancy list, better name for variable would be "graph".


# Note: 
- In 2d array questions, if you need to check if a coordinate is already set or not. Better to use a visited 2D array instead of Set. Set will also work and will keep things minimal, but 2D visited array would be much simpler.

- In disjoint data structure questions on 2d array, dont try to make disjoint set data structure itself too complex with external details. Instead just use disjoint set to check if two nodes are already connected or not, other wise connect them if they are adjacent to each other.

# Super Important (Very important) - I came across this bug in one of the problem
In disjoint set problems and implementations, for finding if two nodes belong to same group or not? 
Do not directly use parent[i] because its possible that two nodes from same group can have different immediate parent. So instead use find(int id) method to find the root of the group for both nodes.

- Very Useful: For applying disjoint set on 2d matrix, you need to convert each cell to an integer id. Then only proceed with disjoint set algorithms.

- While solving 2sum, 3sum like questions, be mindful that you should not save elements in pure set or map blindly. Instead keep index as well along with number in order to avoid the duplicate entries.

- In most of the questions, try observation based approach. This will help you in giving right direction for the solution. Observe carefully and see how you can progress in the question, try examples, etc

- In 2D array, mapping each cell to single number is very helpful. For calculating rows, you just have to divide by column counts and for column, module by column count. Helpful in questions like binary search on 2D matrix and dijkstra algo etc.

>> index = row * cols + col; row = index / cols; col = index % cols

# Important Tips for LinkedList Questions
- In LinkedList questions, recursion plays a very important role. It is widely used in LL questions.
- For linked list questions, always use dummy node at the begining. it will make things very simple in most of the questions.

- In DP questions, mostly you have to explore all possibilities and then find best answer out of it.

DFS and backtracking are closely related, but not identical. 
So, DFS = exploration strategy and Backtracking = Decision Management

- DFS is an exploration strategy: follow one branch as deeply as possible, then return.
- Backtracking adds decision management: choose an option, explore it, then undo the choice before trying another option.

- Calculations on rough page is important with good examples. Don't underestimate it.

# Monotonic Stack Guidelines
https://leetcode.com/discuss/post/5148505/monotonic-stack-guide-list-of-problems-b-rnuj/

- Note: Monotonic is a generalised concept, in questions you can use monotonic stack or monotonic queue as required.


>> Trick:
- Remember this - In some questions, you cannot find the answer directly like finding total number of subarrays with exact k unique numbers. So think of breaking down it in subproblems like below. It is useful in many cases. You can just solve the subproblem and that will result in overall solution. 
- This type of thought process is very useful in lots of hard problems. Keep this tooling in mind.

Result => finding total subarrays with exact k unique integers = (findings subarrays with <= k integers) - (findings subarrays with <= k-1 integers)


# Binary Tree
- For finding the LCA (lowest common ancestor) recursively, return the current node if it is `null` or matches either target. Recurse into both children; if both calls return a node, the current node is the LCA. Otherwise, return whichever child returned a node. This propagates a found target or LCA up the recursion without searching further within that subtree.

- For finding LCA (lowest common ancestor), it would be easier to just return the matched node(l or r or common). As soon as you find it return it to parent recursive call. This way you can save lots of below recursion calls.
- Code for finding LCA is quite simple. Just few lines of code in recursive method.

- While solving binary tree questions, first thing you should decide is which traversel to select - BFS or DFS (pre, in, post). And then decide your algorithm.

- In some questions, you might need to iterate from node to its parent like if you want to do BFS in all directions. Then in such question, you should iterate the tree and create a map of child -> parent relation. That will be used in BFS all direction iterations.

- Along with Inorder, preorder traversals, Also keep reverse inorder, reverse preorder in mind. Based on question pattern they might be useful many times.

Note: An inorder traversal combined with a preorder traversal creates a unique binary tree, while a preorder and postorder combination does not because only the inorder traversal provides a boundary line between the left and right subtrees.

>> Why Preorder + Postorder Fails
The Ambiguity: Neither of these methods places the root between its left and right children.

- Morris Traversal for Binary Tree - Striver explained it very well. This is a good algorithm, good to have it in your toolkit. Special thing about morris traversal is that it does not consume any extra space. Space complexity is O(1). It uses threaded binary tree. 
Remember: Morris traversal is iterative solution using a single while loop
Video: https://www.youtube.com/watch?v=80Zug6D1_r4

- Idea: From last node of subtree, make a pointer to root node. Before moving to left subtree, find rightmost node and connect to root. After that move to left subtree.

- From any root node, how will be decide if we want to go to left subtree or right subtree?
If rightmost node already contains link then we remove link and go to right subtree, otherwise we create the link and move to left subtree.

- Realization: In matrix traversal question, better to keep 2D array for visited arrary. If you use set, then you also need to implement equals and hashCode method in the class. Which we can try to avoid for any bugs.

- For BFS question, you don't even need a new method. You can just do it in original method itself.
