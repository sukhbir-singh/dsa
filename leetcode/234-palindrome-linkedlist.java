import java.util.*;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// other sol: reverse list and compare, or use advanced recursion to compare first and last, then second and second-last
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Integer> st = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            st.push(temp.val);
            temp = temp.next;
        }

        // validation
        temp = head;
        while (temp != null) {
            int value = st.pop();
            if (value != temp.val) {
                return false;
            }
            temp = temp.next;
        }

        return true;
    }
}

// interesting advanced recursion based algorithm
class Solution2 {
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) return false;
            if (currentNode.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
}