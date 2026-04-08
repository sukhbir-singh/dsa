import java.util.*;

// One improvement we can think of is to implement some handy methods in Node class like get(char) or put(char) which can be used to operate on children map
class Node {
    public boolean isWord;
    public Map<Character, Node> children;
    
    public Node() {
        isWord = false;
        children = new HashMap<>();
    }
}

class Trie {
    private final Node root;

    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        Node cur = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (!cur.children.containsKey(ch)) {
                cur.children.put(ch, new Node());
            }
            cur = cur.children.get(ch);
        }
        cur.isWord = true;
    }
    
    public boolean search(String word) {
        Node n = findLastNode(word);
        return n != null && n.isWord;
    }
    
    public boolean startsWith(String prefix) {
        Node n = findLastNode(prefix);
        return n != null;
    }

    private Node findLastNode(String word) {
        Node cur = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (!cur.children.containsKey(ch)) {
                return null;
            }
            cur = cur.children.get(ch);
        }
        return cur;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */