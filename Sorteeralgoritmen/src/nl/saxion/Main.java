package nl.saxion;

import nl.saxion.sorting.CocktailShakerSort;
import nl.saxion.sorting.CountingSort;
import nl.saxion.sorting.PancakeSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    //Omdat de ene algoritme sneller is dan de andere gaan wij specifieke algoritmes vaker/minder vaak runnen dan de ander.
    private static final int[] COCKTAIL_AMOUNT = new int[]{10000, 20000, 40000, 80000, 160000, 320000};
    private static final int[] PANCAKE_AMOUNT = new int[]{10000, 20000, 40000, 80000, 160000, 320000};

    //Counting sort is goed dus die mag íets vaker dan de andere.
    private static final int[] COUNTING_AMOUNT = new int[]{
            2500000, 5000000, 7500000, 10000000,
            12500000, 15000000, 17500000, 20000000,
            22500000, 25000000, 27500000, 30000000,
            32500000, 35000000, 37500000, 40000000,
            42500000, 45000000, 47500000, 50000000};

    private static final int ITERATIONS = 10;//Aantal keer dat wij de algoritme runnen, om zo een betrouwbaar resultaat te krijgen.

    public static void main(String[] args) {

        System.out.println("--RUNNING COCKTAIL SHAKER--");
        for (int a : COCKTAIL_AMOUNT) {
            int[] list = Utils.genereerRandomGetallen(a);
            long cocktailTime = sortAndTime(list, "COCKTAILSHAKER");
            System.out.println(a + ";" + cocktailTime); //csv format zodat het makkelijk te plakken is in excel.
        }
        System.out.println("--EIND COCKTAIL SHAKER");


        System.out.println("--RUNNING PANCAKE SORT--");
        for (int a : PANCAKE_AMOUNT) {
            int[] list = Utils.genereerRandomGetallen(a);
            long pancakeTime = sortAndTime(list, "PANCAKE");
            System.out.println(a + ";" + pancakeTime); //csv format zodat het makkelijk te plakken is in excel.
        }
        System.out.println("--EIND PANCAKE SORT");

        System.out.println("--RUNNING COUNTING SORT--");
        for (int a : COUNTING_AMOUNT) {
            int[] list = Utils.genereerRandomGetallen(a);
            long countingTime = sortAndTime(list, "COUNTING");
            System.out.println(a + ";" + countingTime); //csv format zodat het makkelijk te plakken is in excel.
        }
        System.out.println("--EIND COUNTING SORT");


    }

    /**
     * meegeleverde lijst sorteren o.b.v. de gekozen sorteermethode.
     *
     * @param array
     * @param sortmethod
     * @return
     */
    public static long sortAndTime(int[] array, String sortmethod) {
        ArrayList<Long> meettijden = new ArrayList<>();

        for (int i = 0; i < ITERATIONS; i++) {
            long start = System.nanoTime();

            switch (sortmethod) {
                case "COCKTAILSHAKER":
                    CocktailShakerSort.shakerSort(Arrays.copyOf(array, array.length));
                    break;
                case "COUNTING":
                    CountingSort.countingSort(Arrays.copyOf(array, array.length));
                    break;
                case "PANCAKE":
                    PancakeSort.pancakeSort(Arrays.copyOf(array, array.length));
                    break;
                default:
                    System.out.println("Unknown sort method");
                    break;
            }

            long end = System.nanoTime();

            meettijden.add(end - start);
        }

        long gemiddelde = Utils.berekenGemiddelde(Utils.filterHoogsteEnLaagste(meettijden));
        return gemiddelde;
    }

}