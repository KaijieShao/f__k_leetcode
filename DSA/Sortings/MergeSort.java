package DSA.Sortings;


import java.util.Arrays;

public class MergeSort {

    // Scenario:
    // Simple algorithms have worst-case time complexity O(n²), which becomes very slow as n grows
    // M: Using a divide-and-conquer strategy to break the sorting problem into smaller, more manageable pieces
    // Achieving O(n log n) time complexity in all cases, making it much more efficient for big inputs

    public static int[] merge(int[] array1, int[] array2) { 
        int[] combined = new int[array1.length + array2.length];                       // 💥 Space!
        int index = 0;
        int i = 0;
        int j = 0;
        while (i < array1.length && j < array2.length) {   // RUN until one of the arrays is empty
            if (array1[i] < array2[j]) {
                combined[index] = array1[i];
                index++;
                i++;
            } else {
                combined[index] = array2[j];
                index++;
                j++;
            }
        }
        while (i < array1.length) {                        // Ensures all elements from both end up in 'combined'
            combined[index] = array1[i];
            index++;
            i++;
        }
        while (j < array2.length) {
            combined[index] = array2[j];
            index++;
            j++;
        }

        return combined;
    }


    // O(n log n) time, O(n) space
    public static int[] mergeSort(int[] array) {           // Creates and returns new array (not like BubbleSort)
        if (array.length == 1) return array;               // Base case: when len(the_list) is 1
        
        int midIndex = array.length/2;                    
        int[] left = mergeSort(Arrays.copyOfRange(array, 0, midIndex));          // 💥 Space!
        int[] right = mergeSort(Arrays.copyOfRange(array, midIndex, array.length));

        return merge(left, right);
    }


    public static void main(String[] args) {
        int[] originalArray = {3,1,4,2};
        int [] sortedArray = mergeSort(originalArray);

        System.out.println( "\nOriginal Array: " + Arrays.toString( originalArray ) );
        System.out.println( "\nSorted Array: " + Arrays.toString( sortedArray ) );
    }
}



