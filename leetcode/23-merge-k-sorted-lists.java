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
// good example usage of priority queue.
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        // custom priority queue
        Queue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node: lists) {
            if (node != null)
                pq.add(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.remove();
            temp.next = node;
            temp = temp.next;
            if (temp.next != null) {
                pq.add(temp.next);
            }
        }

        return dummy.next;
    }
}