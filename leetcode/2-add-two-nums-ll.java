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
// Nice use of single while implementations
// In such questions when you have to create new linked list - better way is to just create a dummy node in the beginning and attached new nodes after that
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, cur = null;

        int rem = 0;
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0 : l1.val;
            int l2Val = l2 == null ? 0 : l2.val;

            int res = l1Val + l2Val + rem;
            ListNode n = new ListNode(res%10);
            rem = res/10;

            if (cur == null) {
                head = n;
                cur = n;
            } else {
                cur.next = n;
                cur = n;
            }

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (rem > 0) {
            cur.next = new ListNode(rem);
        }

        return head;
    }
}