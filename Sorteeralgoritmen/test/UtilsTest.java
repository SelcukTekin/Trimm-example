import nl.saxion.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UtilsTest {

    @Test
    public void testIsSorted() {
        Assert.assertTrue(Utils.isSorted(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));
        Assert.assertFalse(Utils.isSorted(new int[]{0, 1, 2, 3, 4, 5, 7, 6, 8, 9}));
        Assert.assertTrue(Utils.isSorted(new int[]{}));
        Assert.assertTrue(Utils.isSorted(new int[]{0}));
    }

    @Test
    public void testGenereerLijst() {
        int[] generatedList = Utils.genereerRandomGetallen(100);
        Assert.assertEquals(100, generatedList.length);

        for(int i: generatedList) {
            Assert.assertTrue(i < 100 && i >= 0);
        }
    }

    @Test
    public void testFilterHoogsteEnLaagste() {
        ArrayList<Long> meettijden = new ArrayList<>();

        for(int i = 1; i <= 10; i++) { // 1000 tot 10000
            meettijden.add((long) i * 1000);
        }

        meettijden = Utils.filterHoogsteEnLaagste(meettijden);

        Assert.assertEquals(meettijden.size(), 8);

        for(int i = 0; i <= 7; i++) { // index 1 tot 8
            Assert.assertEquals((long) meettijden.get(i), (long) (i + 2) * 1000);
        }
    }

    @Test
    public void testBerekenGemiddelde() {
        ArrayList<Long> meettijden = new ArrayList<>();

        for(int i = 1; i <= 10; i++) { // 1000 tot 10000
            meettijden.add((long) i * 1000);
        }

        long gemiddelde = Utils.berekenGemiddelde(meettijden);

        Assert.assertEquals(5500, gemiddelde);
    }
}
