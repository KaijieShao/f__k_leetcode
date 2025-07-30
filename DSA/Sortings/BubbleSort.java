package DSA.Sortings;


import java.util.Arrays;

public class BubbleSort {

    // Scenario:
    // A basic, easy-to-implement sorting algorithm for small datasets or educational purposes
    // 1. It repeatedly steps through the list
    // 2. Compares adjacent items, and swaps them if they are in the wrong order 
    // 3. "bubbling" larger elements to the end.

    // O(n^2) time complexity
    public static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] myArray = {4,2,6,5,1,3};
        bubbleSort(myArray);
        System.out.println( Arrays.toString(myArray) );      

    }
}

