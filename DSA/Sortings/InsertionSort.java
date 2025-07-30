package DSA.Sortings;


import java.util.Arrays;

public class InsertionSort {

    // Scenario:
    // Great analogy - InsertionSort is like building up a winning hand in Mahjong
    // You pick up one item at a time, compare it to what's already sorted, and insert it in the correct position

    // O(n^2) time, O(1) space
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while (j > -1 && temp < array[j]) {        // If the array is sorted, the while loop is never triggered
                array[j + 1] = array[j];               // Shifting the value right
                array[j] = temp;
                j--;
            }
        }
    }


    public static void main(String[] args) {
        int[] myArray = {4,2,6,5,1,3};
        insertionSort(myArray);
        System.out.println( Arrays.toString(myArray) );
    }
}


