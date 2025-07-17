package DSA.Sortings;

public class BucketSort {

    public static int[] bucketSort(int[] arr) {

        int[] counts = {0, 0, 0};       
        
        for (int num : arr) {
            counts[num] += 1;
        }

        int i = 0; 
        for (int n = 0; n < counts.length; n++) {

            for (int j = 0; j < counts[n]; j++) {
                arr[i] = n;
                i++;
            }
        }

        return arr;
    }
}
