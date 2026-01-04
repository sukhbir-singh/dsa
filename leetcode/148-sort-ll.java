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
// bubble sort results in time limit exceeded
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // trying bubble sort
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            len++;
        }

        for (int i=0; i<len; i++) {
            ListNode p = head, c = head.next;
            while (c != null) {
                if (p.val > c.val) {
                    int t = p.val;
                    p.val = c.val;
                    c.val = t;
                }
                p = p.next;
                c = c.next;
            }
        }

        return head;
    }
}