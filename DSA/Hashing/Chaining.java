package DSA.Hashing;



import java.util.ArrayList;

public class Chaining {

    // Scenario:
    // i.e., Checking for Duplicates/Uniqueness OR Frequency Counting/Aggregating Information

    private int size = 7;                                      // Prime number helps REDUCE collisions
    private Node[] dataMap;

    class Node {
        String key;
        int value;
        Node next;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    public Chaining() {
        dataMap = new Node[size];
    }


    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {
            System.out.println(i + ":");                       // Print the bucket index followed by a colon

            Node temp = dataMap[i];                            // Start traversing the LL at this bucket
            while (temp != null) {                            
                System.out.println("{" + temp.key + "= " + temp.value + "}");
                temp = temp.next;                              // Move to the next node in the LL at same index
            }
        }
    }


    private int hash(String key) {
        int hash = 0;                                          // Accumulate each character's contribution
        char[] keyChars = key.toCharArray();                   
        for (int i = 0; i < keyChars.length; i++) {          
            int asciiValue = keyChars[i];                      // Unicode i.e., 'a' = 97
            hash = (hash + asciiValue * 23) % dataMap.length;  // % -> Hash falls within the value range of index
        }
        return hash;                                           // index: MUST be 0 to 6 (within 'size = 7')
    }


    public void set(String key, int value) {
        int index = hash(key);

        Node newNode = new Node(key, value);
        if (dataMap[index] == null) {
            dataMap[index] = newNode;
        } else {
            Node temp = dataMap[index];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }


    public int get(String key) {
        int index = hash(key);

        Node temp = dataMap[index];                            // Assign 'temp' to the head of LL at that bucket
        while (temp != null) {
            if (temp.key == key) return temp.value;
            temp = temp.next;
        }
        return 0;
    }


    public ArrayList keys() {
        ArrayList<String> allKeys = new ArrayList<>();

        for (int i = 0; i < dataMap.length; i++) {
            Node temp = dataMap[i];
            while (temp != null) {
                allKeys.add(temp.key);
                temp = temp.next;
            }
        }
        return allKeys;
    }
}

