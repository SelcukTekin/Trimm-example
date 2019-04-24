/**
 * Instanties van deze klasse worden in de tree gezet
 */
public class TestNode implements Comparable<TestNode> {

    private int value;

    public TestNode(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(TestNode o) {
        return value - o.value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
