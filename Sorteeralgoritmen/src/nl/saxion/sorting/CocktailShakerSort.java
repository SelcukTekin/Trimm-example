package nl.saxion.sorting;
//!! Credits naar: http://www.javacodex.com/Sorting/Cocktail-Sort
public class CocktailShakerSort {

    /**
     * Shaker sort (bidirectional bubble sort)
     * Orders in descending order
     * @param arrayToSort array to be sorted
     */
    public static int[] shakerSort(int[] arrayToSort) {
        boolean swapped;
        do {
            swapped = false;
            for ( int i =0; i <= arrayToSort.length - 2; i++ ) {
                if (arrayToSort[i] > arrayToSort[i + 1]) {
                    //test whether the two elements are in the wrong order
                    int temp = arrayToSort[i];
                    arrayToSort[i] = arrayToSort[i+1];
                    arrayToSort[i+1]=temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                //we can exit the outer loop here if no swaps occurred.
                break;
            }
            swapped = false;
            for (int i = arrayToSort.length - 2; i>=0; i--) {
                if (arrayToSort[i] > arrayToSort[i + 1]) {
                    int temp = arrayToSort[i];
                    arrayToSort[i] = arrayToSort[i+1];
                    arrayToSort[i+1]=temp;
                    swapped = true;
                }
            }
            //if no elements have been swapped, then the list is sorted
        } while (swapped);

        return arrayToSort;
    }
}