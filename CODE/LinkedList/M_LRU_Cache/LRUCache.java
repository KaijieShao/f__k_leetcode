package CODE.LinkedList.M_LRU_Cache;

// You need to build a data structure that remembers usage order and efficiently evicts the oldest (least recently used) entry when over capacity, while supporting fast lookup and update.

import java.util.HashMap;

public class LRUCache {
    
    // --- Class Members ---
    
    private int cap;                                           // Stores the maximum capacity of the cache.
    private HashMap<Integer, Node> cache;                      // Find any node in the doubly linked list in O(1) time.
    // Key:   Integer - this is the cache key (like 1, 2, 3)
    // Value: Node - Key, and Value (the actual cached data)

    private Node left;                                         // A "dummy" node representing the LRU side of the list.
    private Node right;                                        // A "dummy" node representing the MRU side of the list.

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.cache = new HashMap<>();

        this.left = new Node(0, 0);                    // Left Dummy Node:  its key/value don't matter.
        this.right = new Node(0, 0);                   // Right Dummy Node: its key/value don't matter.

        this.left.next = this.right;                           // Connect Left and Right Dummy to initialize an empty DLL
        this.right.prev = this.left;
    }

    // --- Helper Methods for DLL Manipulation ---

    private void remove(Node node) {
        Node prev = node.prev;
        Node nxt = node.next;
        prev.next = nxt;
        nxt.prev = prev;
    }

    private void insert(Node node) {
        Node prev = this.right.prev;
        prev.next = node;
        node.prev = prev;
        node.next = this.right;
        this.right.prev = node;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            // Remove the node from its current place in the DLL because you’re about to mark it as most recently used.

            insert(node);
            // Insert the node at the end (MRU position/right before the right dummy) — this marks it as most recently used

            return node.val;   // Returns the value of the node.
        }
        return -1;             // If the key doesn't exist, returns -1.
    }

    public void put(int key, int value) {

        // If key already exists:
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        // Remove the old node from the DLL since you'll update it and move it to MRU

        // Create and insert new node:
        Node newNode = new Node(key, value);      // Make a new node for this key-value pair.
        cache.put(key, newNode);                  // Add it to the hashmap.
        insert(newNode);                          // Insert it at the “most recently used” (MRU) position in the list.

        // If over capacity, evict LRU:
        if (cache.size() > cap) {
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
        // If cache is too big, find the least recently used node (node right after the left dummy).
        // Remove that node from the list and the hashmap.
    }
}

// 52:15