/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        Node nextNode = node.next;
        node.val = nextNode.val;
        node.next = nextNode.next;
    }
}