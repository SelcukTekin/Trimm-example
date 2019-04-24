package nl.saxion.sorting;
//!!Credits naar: http://www.javacodex.com/Sorting/Pancake-Sort
public class PancakeSort {
    public static int[] pancakeSort(int[] arrayToSort) {
        int max = 0;
        int j, index = 0;

        for (int i = 0; i < arrayToSort.length; i++) {
            max = arrayToSort[0];
            index = 0;
            for (j = 0; j < arrayToSort.length - i; j++) {
                if (arrayToSort[j] > max) {
                    max = arrayToSort[j];
                    index = j;
                }
            }
            flip(arrayToSort, index, arrayToSort.length - 1 - i);
        }

        return arrayToSort;
    }

    public static void flip(int[] arrayToSort, int l, int r) {
        while (l <= r) {
            int temp = arrayToSort[l];
            arrayToSort[l] = arrayToSort[r];
            arrayToSort[r] = temp;
            l++;
            r--;
        }
    }
}