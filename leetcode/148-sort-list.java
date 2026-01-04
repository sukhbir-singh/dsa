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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        // Base case is very important: return head if list contains only single element
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middle = findMiddle(head);
        //System.out.println("head is " + head.val +  ". middle is " + middle.val);
        ListNode secondHead = middle.next;
        middle.next = null;

        ListNode h1 = mergeSort(head);
        ListNode h2 = mergeSort(secondHead);

        return mergeLists(h1, h2);
    }

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

    private ListNode mergeLists(ListNode h1, ListNode h2) {
        ListNode tempHead = new ListNode(0);
        ListNode temp = tempHead;

        while (h1 != null || h2 != null) {
            int val1 = h1 == null ? Integer.MAX_VALUE : h1.val;
            int val2 = h2 == null ? Integer.MAX_VALUE : h2.val;

            if (val1 <= val2) {
                temp.next = new ListNode(val1);
                h1 = h1.next;
            } else {
                temp.next = new ListNode(val2);
                h2 = h2.next;
            }

            temp = temp.next;
        }

        return tempHead.next;
    }
}