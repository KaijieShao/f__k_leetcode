package DSA.Arrays;


public class DynamicArray {
    int capacity;
    int length;
    int[] arr;

    public DynamicArray() {
        capacity = 2;
        length = 0;
        arr = new int[2];
    }

    public void resize() { 
        capacity = 2 * capacity;
        int[] newArr = new int[capacity]; 
        for (int i = 0; i < length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr; // Replace internal array with the new one
    }  
    // O(1) Amortized Time (doubling is crucial):
    // 1) When you double the array's capacity on resize, the expensive O(n) copy happens infrequently.
    // 2) Most pushBack operations are O(1).
    // 3) The rare O(n) resize cost is averaged (amortized) over many insertions.
    // 4) So, amortized time per append is O(1), not O(n).

    public void pushback(int n) { 
        if (length == capacity) {
            this.resize();
        }
        arr[length] = n;
        length++;
    }
    // Insertion (End): O(1)

    public void popback() { 
        if (length > 0) {
            length--;
        }  
    }     
    // Deletion (End): O(1)

    public int get(int i) { 
        if (i < length) {
            return arr[i];
        }    
        return -1;
    }    
    // Access: O(1)

    public void insert(int i, int n) { 
        if (i < length) {
            arr[i] = n;
            return; // Exits the method immediately if the inidex is valid and assignment succeeds
        }    
        return; // Second 'return' is reached only if the 'if' condition fails (invalid index) -> exit, do nothing
    }        
    // Insertion (Middle): O(n)

    public void print() {
        for (int i = 0; i < length; i++) {
            System.out.println(arr[i]);
        }
    }
} 


