
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

