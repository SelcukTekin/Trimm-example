package nl.saxion;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

    /**
     * Genereer een int array met random getallen.
     * @param aantalGetallen    Aantal getallen dat er in de int array moet staan.
     * @return
     */
    public static int[] genereerRandomGetallen(int aantalGetallen) {
        Random rng = new Random();

        int[] unsortedNumbers = new int[aantalGetallen];

        for(int i = 0; i < unsortedNumbers.length; i++) {
            unsortedNumbers[i] = rng.nextInt(aantalGetallen);
        }

        return unsortedNumbers;
    }

    /**
     * Hoogste en laagste meetresultaten filteren.
     * @param meetTijden
     * @return
     */
    public static ArrayList<Long> filterHoogsteEnLaagste(ArrayList<Long> meetTijden) {
        int shortestIndex = 0;
        int longestIndex = 0;

        for(int i = 0; i < 10; i++) {
            if(meetTijden.get(shortestIndex) > meetTijden.get(i)) {
                shortestIndex = i;
            }else if(meetTijden.get(longestIndex) < meetTijden.get(i)) {
                longestIndex = i;
            }
        }

        ArrayList<Long> meetTijdenFiltered = new ArrayList<Long>();

        for(int i = 0; i < 10; i++) {
            if(i != shortestIndex && i != longestIndex) {
                meetTijdenFiltered.add(meetTijden.get(i));
            }
        }

        return meetTijdenFiltered;
    }

    /**
     * Gemiddelde berekenen o.b.v. de meegestuurde waarde
     * @param meettijden
     * @return
     */
    public static Long berekenGemiddelde(ArrayList<Long> meettijden) {
        long sum = 0;
        for(long l : meettijden) {
            sum += l;
        }

        return sum / meettijden.size();
    }

    /**
     * Controleren of de output wel écht goed is gesorteerd.
     * @param list
     * @return
     */
    public static boolean isSorted(int[] list) {
        for(int i = 1; i < list.length; i++) {
            if(list[i-1] > list[i]) {
                return false;
            }
        }

        return true;
    }
}
