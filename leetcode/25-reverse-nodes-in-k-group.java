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
// good use of recusion
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode kth = head;

        int count = 1;
        while (kth != null && count < k) {
            kth = kth.next;
            count++;
        }

        if (count < k || kth == null) {
            return head;
        }

        ListNode nextNode = kth.next;
        kth.next = null;

        ListNode newHead = reverseList(head);

        ListNode reversedHead = reverseKGroup(nextNode, k); // reverse remaining nodes
        head.next = reversedHead;

        return newHead;
    }

    //     p   c   n
    //     1 - 2 - 3 - 4
    // This should use lesser variables - prev, cur were sufficient
    private ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head, next = head.next;
        while (next != null) {
            cur.next = prev;
            ListNode temp = next.next;
            next.next = cur;

            prev = cur;
            cur = next;
            next = temp;
        }

        return cur;
    }
}

// iterative solution
class SolutionIterative {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;
            ListNode prev = groupNext;
            ListNode current = groupPrev.next;

            while (current != groupNext) {
                ListNode next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            ListNode oldGroupHead = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = oldGroupHead;
        }

        return dummy.next;
    }

    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }

        return start;
    }
}
