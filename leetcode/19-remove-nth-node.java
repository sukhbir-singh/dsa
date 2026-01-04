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
// nice recursion implementation that i tried. fast, slow pointer was a better solution.
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        } else if (head.next == null && n == 1) {
            return null;
        }

        int total = recurse(head, n);
        //System.out.println("total: " + total);

        if (total == n) {
            return head.next;
        }

        return head;
    }

    private int recurse(ListNode head, int n) {
        if (head == null) {
            return 0;
        }

        int count = recurse(head.next, n);
        //System.out.println("node: " + head.val + " , count: " + count);

        if (count == n) {
            head.next = head.next.next;
        }

        return 1+count;
    }
}

// another nice approach
class Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = head;
        for(int i=0;i<n;i++){
            fast = fast.next;
        }
        
        if(fast==null){
            head = head.next;
            return head;
        }
        
        while(fast.next!=null){
            slow = slow.next;
            fast=fast.next;
        }
        
        slow.next = slow.next.next;
            
        return head;
        
    }
}