package DSA.Sortings;


public class BucketSort {

    // Scenario:
    // When the data is uniformly distributed over a known range and efficient, linear-time sorting is desired
    // i.e., Large datasets with known/uniform value distribution (e.g., age, salary ranges)

    public static int[] bucketSort(int[] arr) {
        int[] counts = {0, 0, 0};                    // Assumes 'arr' contains only the values 0, 1, 2
        
        for (int num : arr) {
            counts[num] += 1;                        // Increment the corresponding bucket count
        }

        int i = 0; 
        for (int n = 0; n < counts.length; n++) {    // Iterates through each bucket in order
            for (int j = 0; j < counts[n]; j++) {    // Write all counted values into the array from each bucket
                arr[i] = n;
                i++;
            }
        }

        return arr;
    }
}

