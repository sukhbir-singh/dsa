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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = null, c = head, n = head.next;
        while (c != null) {
            c.next = p;

            p = c;
            c = n;
            if (n != null) {
                n = n.next;
            }
        }

        return p;
    }
}


// Better Solution Using 2 pointer
class SolutionUsing2Pointer {
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
