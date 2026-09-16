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
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        int total = count(head);
        int r = k%total;
        if (r == 0) {
            return head;
        }

        ListNode kthPrev = findKthNode(head, total - r);

        ListNode kth = kthPrev.next;
        kthPrev.next = null;

        ListNode newHead = kth;

        // attach last node
        ListNode lastNode = kth;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = head;

        return newHead;
    }

    private int count(ListNode head) {
        int c = 0;
        while (head != null) {
            head = head.next;
            c++;
        }
        return c;
    }

    private ListNode findKthNode(ListNode head, int k) {
        while (head != null && k > 1) {
            head = head.next;
            k--;
        }
        return head;
    }
}