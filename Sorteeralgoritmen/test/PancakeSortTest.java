import nl.saxion.Utils;
import nl.saxion.sorting.PancakeSort;
import org.junit.Assert;
import org.junit.Test;

public class PancakeSortTest {

    @Test
    public void testSort() {
        int[] list = Utils.genereerRandomGetallen(1000);
        list = PancakeSort.pancakeSort(list);

        Assert.assertTrue(Utils.isSorted(list));
    }
}
