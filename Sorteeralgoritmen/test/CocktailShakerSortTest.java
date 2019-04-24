import nl.saxion.Utils;
import nl.saxion.sorting.CocktailShakerSort;
import org.junit.Assert;
import org.junit.Test;

public class CocktailShakerSortTest {

    @Test
    public void testSort() {
        int[] list = Utils.genereerRandomGetallen(1000);
        list = CocktailShakerSort.shakerSort(list);

        Assert.assertTrue(Utils.isSorted(list));
    }
}
