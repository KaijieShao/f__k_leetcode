package DSA.Sortings;


import java.util.Arrays;

public class QuickSort {

    // Scenario:
    // QuickSort uses O(log n) space for recursion in the average/best case (vs. MergeSort’s O(n))
    // Choose MergeSort when you need stability, guaranteed O(nlogn) time, or are sorting huge/external data
    // Choose QuickSort for in-place, fast average-case sorting of in-memory data, when stability isn’t needed

    private static void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }


    private static int pivot(int[] array, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;
        for (int i = pivotIndex + 1; i <= endIndex; i++) { // Pivot -> Smaller than pivot -> Larger than pivot
            if (array[i] < array[pivotIndex]) {
                swapIndex++;
                swap(array, swapIndex, i);
            }
        }
        swap(array, pivotIndex, swapIndex);                // Smaller than pivot -> Pivot -> Larger than pivot

        return swapIndex;
    }


    // O(n log n) time, O(log n) space
    public static void quickSort(int[] array, int left, int right) {  // 'void' means the array is sorted in place
        if (left < right) {                                           // Base case: left >= right
            int pivotIndex = pivot(array, left, right);               // Finds the correct position of pivot first
            quickSort(array, left, pivotIndex-1);                     // Recursively sort left and right halves
            quickSort(array, pivotIndex+1, right);
        }
    }

    public static void main(String[] args) {
        int[] myArray = {4,6,1,7,3,2,5};
        quickSort(myArray, 0, 6);
        System.out.println( Arrays.toString( myArray ) );
    }
}


