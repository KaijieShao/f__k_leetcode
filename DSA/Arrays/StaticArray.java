package DSA.Arrays;


public class StaticArray { 
    public void insertEnd(int[] arr, int n, int length, int capacity) { // O(1)
        if (length < capacity) { // 'length' is real; 'capacity' is size
            arr[length] = n;
        }
    }    
    // Once an array is declared, its size cannot be changed 
    // Adding an element to a full static array will NOT work
            
    public void removeEnd(int[] arr, int length) { // O(1)
        if (length > 0) {
            arr[length - 1] = 0; // soft delete
            length--;
        }
    }        

    public void insertMiddle(int[] arr, int i, int n, int length) { // O(n)
        for (int index = length - 1; index > i - 1; index--) {
            arr[index + 1] = arr[index]; // shift right
        }        
        arr[i] = n;
    }

    public void removeMiddle(int[] arr, int i, int length) { // O(n)
        for (int index = i + 1; index < length; index++) {
            arr[index - 1] = arr[index]; // shift left (overwriting)
        } 
    }

    public void printArr(int[] arr, int length) { // O(n)
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + " ");
        }      
        System.out.println();
    }
}    
