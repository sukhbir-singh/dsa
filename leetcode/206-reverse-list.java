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
// Better Solution Using 2 pointer
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode p = null;
        ListNode c = head;
        while (c != null) {
            ListNode nextTemp = c.next;
            c.next = p;  // main line where we update the pointer
            
            p = c;
            c = nextTemp;
        }
        return p;
    }
}

// recursive
class Solution2 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head; // important
        head.next = null;
        return p;
    }
}