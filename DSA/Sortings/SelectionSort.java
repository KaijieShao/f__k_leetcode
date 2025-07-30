package DSA.Sortings;


import java.util.Arrays;

public class SelectionSort {

    // Scenario:
    // Repeatedly selects smallest (or largest) element from unsorted part and moves it to the correct position
    // Selection Sort, Bubble Sort, and Insertion Sort all solve small datasets (e.g., n < 100)
    // All have O(n²) time complexity, making them inefficient for big/real-world datasets

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;                           
            for (int j = i+1; j < array.length; j++) {   // Sortings are classic, intuitive example of nested loops
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {                         // Checker: avoid unnecessary swaps 
                int temp = array[i];                     // Now, 'minIndex' hold smallest value -> swap it
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] myArray = {4,2,6,5,1,3};
        selectionSort(myArray);
        System.out.println( Arrays.toString(myArray) );

    }
}



