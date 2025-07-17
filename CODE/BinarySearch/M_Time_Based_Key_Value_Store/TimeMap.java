package CODE.BinarySearch.M_Time_Based_Key_Value_Store;

// Implement a time-based key-value store:
// 1. Each key can have multiple values set at different timestamps.
// 2. For a given key and timestamp, return the value set at the largest timestamp less than or equal to the given timestamp.
// 3. You must design two methods:
//    - set(key, <value, timestamp>): Store a value for a key at a specific time.
//    - get(key, timestamp): Retrieve the most recent value for the key at or before that timestamp.
// 4. The timestamps for each key's set calls are strictly increasing.

import java.util.Map;
import java.util.HashMap; // Stores keys in no specific order (retrieve the value for an exact key as long as the key exists, regardless of order)
import java.util.TreeMap; // Always stores its keys in sorted order

public class TimeMap {
    private Map<String, TreeMap<Integer, String>> m;

    public TimeMap() {
        m = new HashMap<>();
    }
    // Constructor: Initializes the member variable m as a new, empty HashMap

    public void set(String key, String value, int timestamp) {
        m.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }
    // computeIfAbsent takes a key and a lambda (k -> new TreeMap<>()); Lambda runs only if the key doesn’t exist!
    // 1. Checks if key exists in the map m.
    // 2. If not, creates a new TreeMap and puts it in m at key
    // 3. Returns the TreeMap for key (existing or newly created)

    // .put(timestamp, value) -> Inserts timestamp as key and value as value into the returned TreeMap

    public String get(String key, int timestamp) {
        if (!m.containsKey(key)) return "";                                    // If not, return empty string
        TreeMap<Integer, String> timestamps = m.get(key);                      // Get the TreeMap for the key   
        // Syntax: You declare the type on the left (TreeMap<Integer, String>) to match what you're getting from the map

        Map.Entry<Integer, String> entry = timestamps.floorEntry(timestamp);   // Finds the entry with the largest timestamp ≤ given timestamp
        // Syntax: use a TreeMap method -> floorEntry(): It searches the sorted keys and returns the entry with the largest key that is less than or equal to timestamp
        //         Map.entry<Integer, String>
        //         entry.getKey()   -> timestamp
        //         entry.getValue() -> value stored at that timestamp

        return entry == null ? "" : entry.getValue();                          // Returns the value if found, else empty string
    }
}

