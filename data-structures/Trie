import java.util.*;

// Very Important Datastructure - Frequently asked
class TrieNode {
    // Each node holds a map of its children (character -> next node)
    // and a flag to mark the end of a complete word.
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

public class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            // 1. Try to get the existing node for this character
            TrieNode next = current.children.get(ch);
            
            // 2. If it doesn't exist, create it and put it in the map
            if (next == null) {
                next = new TrieNode();
                current.children.put(ch, next);
            }
            
            // 3. Move the pointer to the next node
            current = next;
        }
        current.isEndOfWord = true;
    }

    // Returns true if the word is in the trie
    public boolean search(String word) {
        TrieNode node = getLastNode(word);
        return node != null && node.isEndOfWord;
    }

    // Returns true if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        return getLastNode(prefix) != null;
    }

    // Helper method to navigate to the end of a string path
    private TrieNode getLastNode(String s) {
        TrieNode current = root;
        for (char ch : s.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) return null;
        }
        return current;
    }
}
