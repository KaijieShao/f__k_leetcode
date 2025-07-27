package DSA.Hashing;


public class OpenAddressing {

    // Scenario:
    // Open Addressing and Chaining both solve the same root problem, just different method to handle collisions

    class Pair {
        String key;
        String val;
    
        Pair(String key, String val) {
            this.key = key;
            this.val = val;
        }
    }

    int size;                                           
    int capacity;                                       
    Pair[] map;                                         // Store Pair nodes (each holding a key-value pair)


    public OpenAddressing() {
        this.size = 0;
        this.capacity = 2;
        this.map = new Pair[2];
    }


    public int hash(String key) {
        int index = 0;
        for (int i = 0; i < key.length(); i++) {
            index += (int) key.charAt(i);                // Converts that character to its ASCII (integer) value
        }
        return index % this.capacity;                    // To fit in array
    }


    public String get(String key) {
        int index = this.hash(key);

        while (this.map[index] != null) {
            if (this.map[index].key == key) {
                return this.map[index].val;
            }
            index += 1;                                  // Move to next slot (linear probing)
            index = index % this.capacity;               // Wrap around if needed
        }
        return null;                                     // If not found, return null
    }


    public void put(String key, String val) {
        int index = this.hash(key);

        while (true) {                                   // Probe indefinitely
            if (this.map[index] == null) {               // Empty slot found
                this.map[index] = new Pair(key, val);    // Insert new pair
                this.size += 1;                         
                if (this.size >= this.capacity / 2) {    // Resize if 50% full
                    this.rehash();
                }
                return;
            } else if (this.map[index].key == key) {     // Key already exists
                this.map[index].val = val;               // Update value
                return;
            }
            index += 1;                                  // Move to next slot (linear probing)
            index = index % this.capacity;
        }
    }


    public void remove(String key) {
        if (this.get(key) == null) {
            return;
        }
        
        int index = this.hash(key);
        while (true) {                                  
            if (this.map[index].key == key) {            // Found the key
                this.map[index] = null;                  // Create bug: get() may stop early when found a hole
                this.size -= 1;                          // Decrement size
                return;
            }
            index += 1;                                 
            index = index % this.capacity;
        }
    }


    public void rehash() {                               // Doubles table size and reinserts all pairs
        this.capacity = 2 * this.capacity;
        Pair[] newMap = new Pair[this.capacity];

        Pair[] oldMap = this.map;
        this.map = newMap;
        this.size = 0;                                   // Reset the element count
    
        for (Pair p : oldMap) {
            if (p != null) {                             // Skip empty slots
                this.put(p.key, p.val);                  // Reinsert each pair into new table
            }
        }
    }


    public void print() {
        for (Pair p : this.map) {                        // Iterate through each slot in map
            if (p != null) {                             // Print only occupied slots
                System.out.println(p.key + " " + p.val);
            }
        }
    }
}

