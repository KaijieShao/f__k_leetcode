package DSA.Arrays;


public class StaticArray {

    // Scenario:
    // 1. Fixed size: Once created, the size can't change.
    // 2. Wastes memory if too big, can't add if full.
    // 3. Can't handle unpredictable data growth.

    // O(1)
    public void insertEnd(int[] arr, int n, int length, int capacity) { // Length is real; Capacity is size
        if (length < capacity) {
            arr[length] = n;
        }
    }    
            
    // O(1)
    public void removeEnd(int[] arr, int length) {
        if (length > 0) {
            arr[length - 1] = 0; // Soft delete
            length--;
        }
    }        

    // Big O(n) -> Worst case 
    public void insertMiddle(int[] arr, int i, int n, int length) {
        for (int index = length - 1; index > i - 1; index--) { // Shift starting from the end to i
            arr[index + 1] = arr[index]; // Shift elements to the right
        }
        arr[i] = n;
    }

    // O(n)
    public void removeMiddle(int[] arr, int i, int length) {
        for (int index = i + 1; index < length; index++) {
            arr[index - 1] = arr[index]; // Shift elements to the left (overwrite)
        } 
    }

    // O(n)
    public void printArr(int[] arr, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }      
        System.out.println(); // print a newline
    }
}    



