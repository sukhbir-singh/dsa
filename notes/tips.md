
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

