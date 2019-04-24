import nl.saxion.Utils;
import nl.saxion.sorting.CountingSort;
import org.junit.Assert;
import org.junit.Test;

public class CountingSortTest {

    @Test
    public void testSort() {
        int[] list = Utils.genereerRandomGetallen(1000);
        list = CountingSort.countingSort(list);

        Assert.assertTrue(Utils.isSorted(list));
    }
}
