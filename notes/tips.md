
- when sending array indexes as parameters, better to send actual inclusive indexes. This will help in avoiding out of index errors.

- always calculate mid point using subtraction, other wise additiona can cause out of range for integer.
`int mid = st + (end-st)/2;`

- reverse array algorithm.
// remember that while reversing you have to iterate to only half
for (int i=0; i<totalElements/2; i++) {
    swap(nums, i, nums.length-1-i);
}

- (Very Important) Difference between two elements in cummulative sum array = Sum of elements between those positions in the original array.

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

- for checking palindrom in string => expand from centers

- In linkedin list questions related to deleting of nodes, implement separate methods for deleting head, tail and other middle nodes. Because handling of head, tail nodes is little different than common nodes and in kth node deletion, you can use deleteHead and deleteTail method to make things simple.

- While deleting kth node in linkedlist, makes sure that you also set the pointers to null for the deleted node as well. Other it can cause some issues later on while iterating.

- In LL questions, mostly cases are when handling head node and tail node.

- In LL, while implementing insertNode, deleteNode methods - always remember to return the new head. Because in the methods execution - head could be changed.

- In lots of LL questions, you will be given that you never have to insert before Head, otherwise head will be changed. Clarify this before implementing such questions.

- Head can also be null when there is no elements in the LL. So always handle this case at the start of the method.

- For reversing a DLL, for each node it is just swapping of next and prev pointers.

- (Important) In linkedlist question, if you want to traverse till mid point of the linked list. Make sure you set the conditions correctly as shown below.

ListNode fast = head;
ListNode slow = head;
while (fast.next != null && fast.next.next != null) {    // With these conditions, slow point will stop before the second half of the linked list
    fast = fast.next.next;
    slow = slow.next;
}
return slow;

- In linked list question, if we need to process from last node to the first node then we should reverse the linked list. If interviewer do not want us to reverse the linked list, then next approach we can try is using the recursion.
Example: you may need to pass back carry from recusive call in linked list. That's why linked list + recursion is useful in lots of questions.


- Important: In linkedlist, find middle method if you want to go to node just before middle element then set the fast pointer to .next.next so that one step is skipped. Striver taught this in his video. Very good trick. And in lots of questions, you want to go to node just before the middle node. Like delete middle node question, merge sort on linked list question, etc.

private ListNode findMiddle(ListNode head) {
    if (head == null) {
        return null;
    } else if (head.next == null) {
        return head;
    }

    // Important: if we want element before middle - ensure that you take f as next.next
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

- (Important) In combination questions on array, for optimization - one very good trick that I learnt is that check if current array element is equals to previous index element. If yes, then do not proceed as it will result in same combination. Otherwise proceed.
So the idea is that if we want unique combinations as result. We should avoid processing same combinations again and again. And loop over entire array for picking each element is better that take or not-take approach.
// Skip duplicate numbers by checking if candidates[i] == candidates[i - 1] for i > index.

- Hint: Whenever in question, you are asked to find unique combinations remember that one good trick is to use Set at the start in main method. And to put combinations in that set and later convert them to list. One more thing: It's important to sort the array initially at the beginning, otherwise same combinations with different sequences will come up. like [1,1,4] , [1,4,1] => Inside set these two are different lists. right but actually logically these are same. That's why sorting the input list is very important.

- This is also very useful in question when we need to keep pair type of things in datastructure like stack.
Stack<int[]> st = new Stack<>();
st.push(new int[]{ nums2[i], i });

- Nice Trick: I just learnt that if you want to keep track of array position along with integer present there in stack, then no need to store pair of integers representing both of them. Instead just keep the array indices and that will represent both array element and the index at which the number is present.

- (Important) While implementing solutions ensure that you handled all the edges cases - positive, negative, greater, smaller, etc.

- Next greater elements, next smaller elements -> these are very important tools for other questions. These algorithms you should know by heart like DFS, etc.

- In many questions, when you think how to solve the problem using brute force - you will also get the hint how to solve it optimally in best possible way.

- Try to make code as clean as possible. Most of the times when code does not looks clean, it introduces lots of unavoidable bugs.

- Remember: In an array, if you have two pointers l and r and are places at two positions in the array, then the total elements present in the subarray starting from l to r is (r-l+1).

- Note: If you have all alphabet characters, then taking array for storing frequency is also a good option.
int[] freq = new int[26];

- Good to know: QuickSelect - pick pivot element, sort elements as left, mid and right. 
Time complexity: O(n) on average, O(n^2) in the worst case.

- Counting sort is a non-comparison sorting algorithm. It can be used to sort an array of positive integers.

- Its also a good practicse to iterate overall couple of examples to understand the way of solution and how it is working. This also gives direction to some patterns that can help in solving the problem.

- Useful: very nice pattern for priority queue: reprocess elements using a temporary list.

- One good technique, I learnt is that you just pick one element from the list like the first element of the array and then keep decreasing the number and find it in the map till the point it exists. In this way, you know the start of the sequence. This approach is helpful in many questions to find start of the sequences. And also help in coming up with most optimal solution.

- Trick: In questions when you have to create a new linked list and return - Always remember to create a one node list first and then keep adding elements to it. In the end, return head.next

- Time complexity of heap is O(log k), where k is size of the heap. If we keep constant size of heap then time complexity remains consistent.

- In simple bracket question, you can just use an integer counter instead of taking whole stack based approach.

- Sometimes recursively iterating all solutions is also a brute force implementation.
Time complexity for recursion is (num_of_branches)^N

- "As time goes by" simulation is useful in many scheduling questions. Video: https://www.youtube.com/watch?v=AsGzwR_FWok

- I feel most of the greedy algorithms are intuision based. Think through the examples and you will find a way to solve them.

# Tree
- In Tree questions, what i found is that in most cases, the brute force is to traverse across entire tree and for each node - try to find the height of left child and height of right child. For finding height at that point, you have to traverse whole subtree. so that is what is taking time.

- For calculating diameter of the tree, for each node - you can return -1 from both empty child nodes and then add +2 to it. 
Note: diameter is maximum length between any 2 nodes in the tree.

- TreeInfo is a good variable name to encaptulate the extra informations that you want to send from each child node to the parent nodes.

- For comparing two trees, simulateous traversals in both trees at the same time is very good way to compare.

- Good method naming: if question is isSymmetrical, you name recursion method isSymmetricalHelper

- In tree, level order traversal is one of the important questions. Frequently used in other questions.

- One common mistake that i observed doing in lots of tree question implementation is getting confused between pushing TreeNode or integer to map. Sometime you have to keep track or integer values and other time you have to keep track or Nodes.

- I found one more interesting way to implement tree - level order traversal today. where you can use just one queue. Keep adding nodes to this list along with a null entry. Whenever we see a null, it means one row is finished execution. In the loop, add condition for this null. 
Another way is to use nested loop like for loop instead while loop. because we know that at any point, all nodes present in the queue belongs to particular level. All nodes are there. [This is a very good observation - remember this]

- One good tip: If you are dividing problem into small methods, and if you need to calculate some final list that aggregate result of all these methods. Then good practise is to define an empty list in main method and then pass that list as argument in all the smaller methods. So that they will add data in this list. This is much better that creating new list in each method and then combining.

- Remember: Recursive traveral in tree is much easier to implement as compared to iterative traversal.

- Thinking perspective: you should start seeing nodes in tree in columns for top-bottom view type questions.
Note: vertical line based thinking and traversal for tree is useful in other questions as well.

- For BST, both recursive and iterative approach is good and simple to implement. Because in each iteration you have to go to either one of the side of the node.

- Note: When reversing the condition for falisy, make sure all symbols are reversed including >, <, then will become <= and >=

- BST Inorder property is very useful. That is, inorder traversal of BST gives us sorted list.

- In BST questions, most of the times - you will be using the binary property of tree nodes to solve the problem. 

- In BST, in most cases you assume that all values are unique. (Clarify this assumption in interviews.)

- Remember: Two sum problem on sorted array - can be solved easily using two pointer.

- Finding the swapped pair in the almost sorted array in O(n) time is an important trick! (Reference: https://leetcode.com/problems/recover-binary-search-tree/description/)

- Remember: In BFS, visit node and then add it to the queue. But In DFS, visit node in the first line of recursion method.

- Important: If you have to create adjancency list for a graph from scratch. Remember that after creating list of list of integer, you have to loop and push empty list to the adj list.

- Thought: If you just have to visit all nodes in a connected graph, prefer DFS simply because it is easy and stack is implicit. In BFS you have to code little more and also have to maintain queue datastruction related operations.

- For Travering in matrix questions, I think using directions array is a very good approach.

int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
for (int[] d : directions) {
    int neighborRow = row + d[0];
    int neighborCol = col + d[1];
...
}

