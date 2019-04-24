package nl.saxion.sorting;
//!! Credits naar: http://www.javainuse.com/java/countingsort
public class CountingSort {

    public static int[] countingSort(int[] arrayToSort) {
        int arrayLength = arrayToSort.length;
        int[] count = new int[arrayToSort.length];

        // initialize the occurrence of each element in the count array
        for (int i = 0; i < arrayLength; i++) {
            count[arrayToSort[i]]++;
        }

        // calculate sum of indexes
        for (int i = 1; i < arrayLength; i++) {
            count[i] += count[i - 1];
        }

        // modify original array according to the sum count
        int j = 0;
        for (int i = 0; i < arrayLength; i++) {
            while (j < count[i]) {
                arrayToSort[j++] = i;
            }
        }

        return arrayToSort;
    }
}
