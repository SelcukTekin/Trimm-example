public class Main {

    public static void main(String[] args) {
        DoubleEndedPriorityQueue<TestNode> depq = new DoubleEndedPriorityQueue<>();

        int[] numbers = {10, 90, 80, 20, 50, 40, 70, 15, 100, 5};

        //Insert every number from int[] numbers into the depq.
        TestNode lastInsertNode = null;
        for (int n : numbers) {
            System.out.println("--> Adding " + n);
            TestNode node = new TestNode(n);
            depq.put(node);
            depq.printTree();
            lastInsertNode = node;
        }

        //Update the last inserted value to 10000
        System.out.println("--> Updating " + lastInsertNode.toString() + " -> 10000");
        lastInsertNode.setValue(10000);
        depq.update(lastInsertNode);
        depq.printTree();

        //Removing every node
        while (!depq.isEmpty()) {
            System.out.println("--> Removing max");
            System.out.println(depq.removeMax());
        }
    }
}