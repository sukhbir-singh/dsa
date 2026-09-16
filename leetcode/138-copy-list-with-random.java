import java.util.*;
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// easier algo would be to use Map (OriginalNode -> ClonedNode)
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        
        Node originalIterator = head;
        
        Node newHead = null;
        Node newIterator = null;
        
        Map<Node, Integer> reverseMap = new HashMap<>();
        Map<Integer, Node> map = new HashMap<>();
        int count = 0;
        
        while(originalIterator != null) {
            reverseMap.put(originalIterator, count);
            Node node = new Node(originalIterator.val);

            if (newHead == null) {
                newHead = node;
                newIterator = node;
            } else {
                newIterator.next = node;
                newIterator = node;
            }
            
            originalIterator = originalIterator.next;
            map.put(count, node);
            count++;
        }
        
        originalIterator = head;
        newIterator = newHead;
        
        while(newIterator != null) {
            if (originalIterator.random == null) {
                newIterator.random = null;
            } else {
                int index = reverseMap.get(originalIterator.random);
                newIterator.random = map.get(index);
            }
            
            originalIterator = originalIterator.next;
            newIterator = newIterator.next;
        }
        
        return newHead;
    }
}