import java.util.ArrayList;

public class DoubleEndedPriorityQueue<T extends Comparable<? super T>> {
    private ArrayList<T> minMaxHeap;

    /**
     * Constructor instantiating a new Double Ended Priority Queue
     */
    public DoubleEndedPriorityQueue() {
        minMaxHeap = new ArrayList<>();
    }

    /**
     * Returns true if the DEPQ is empty
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return minMaxHeap.isEmpty();
    }

    /**
     * Return the size of the DEPQ
     *
     * @return int
     */
    public int size() {
        return minMaxHeap.size();
    }

    /**
     * Return the smallest item in the DEPQ
     *
     * @return smallest T
     */
    public T getMin() {
        if (minMaxHeap.isEmpty()) {
            return null;
        }
        return minMaxHeap.get(0);
    }

    /**
     * Returns the biggest item in the DEPQ
     *
     * @return biggest T
     */
    public T getMax() {
        T max = null;
        if (minMaxHeap.size() == 1) {
            max = minMaxHeap.get(0);
        } else if (minMaxHeap.size() == 2) {
            max = minMaxHeap.get(1);
        } else if (minMaxHeap.size() >= 3) {
            max = (minMaxHeap.get(1).compareTo(minMaxHeap.get(2)) < 0) ? minMaxHeap.get(2) : minMaxHeap.get(1);
        }
        return max;
    }

    /**
     * Insert an item into the DEPQ
     *
     * @param item
     */
    public void put(T item) {
        minMaxHeap.add(item);
        pullUp(minMaxHeap.size() - 1);
    }

    /**
     * Pull an item up the tree as long as possible
     * @param index of the item to be pulled up
     * @return true if at least one pull happened, false if not
     */
    private boolean pullUp(int index) {
        T item = minMaxHeap.get(index);
        boolean minLevel = isOnMinRow(index);
        int parentIndex = getParentIndex(index);
        int grandparentIndex = getParentIndex(parentIndex);

        if(shouldSwitchWithParent(index)) {
            swap(index, parentIndex);
            pullUp(parentIndex);
            return true;
        }else if(shouldSwitchWithGrandparent(index)) {
            swap(index, grandparentIndex);
            pullUp(grandparentIndex);
            return true;
        }
        return false;
    }

    /**
     * Checks if an item is standing correctly according to its parent
     * @param index of the item to be checked
     * @return true if not standing correctly, false if standing correctly
     */
    private boolean shouldSwitchWithParent(int index) {
        T item = minMaxHeap.get(index);
        boolean minLevel = isOnMinRow(index);
        int parentIndex = getParentIndex(index);

        if(parentIndex < 0) {
            //Je hebt geen parent, stop maar.
            return false;
        }

        //Parent bestaat
        T parent = minMaxHeap.get(parentIndex);

        if(minLevel) {
            //Parent moet groter zijn
            return item.compareTo(parent) > 0;
        }else {
            //Parent moet kleiner zijn
            return item.compareTo(parent) < 0;
        }
    }

    /**
     * Checks if an item is standing correctly according to its grandparent
     * @param index of the item to be checked
     * @return true if not standing correctly, false if standing correctly
     */
    private boolean shouldSwitchWithGrandparent(int index) {
        T item = minMaxHeap.get(index);
        boolean minLevel = isOnMinRow(index);
        int grandparentIndex = getParentIndex(getParentIndex(index));

        if(grandparentIndex < 0) {
            //Je hebt geen grandparent, stop maar.
            return false;
        }

        //Grandparent bestaat
        T grandparent = minMaxHeap.get(grandparentIndex);

        if(minLevel) {
            //Grandparent moet kleiner zijn
            return item.compareTo(grandparent) < 0;
        }else {
            //Grandparent moet groter zijn
            return item.compareTo(grandparent) > 0;
        }
    }

    /**
     * Verwijder kleinste item van DEPQ
     *
     * @return removed T
     */
    public T removeMin() {
        T min = getMin();                                                           //Laagste item ophalen.
        if (min != null) {                                                          //Extra check of er wel een item is, en dus NIET empty.
            if (minMaxHeap.size() == 1) {                                           //Als er maar één item is.
                minMaxHeap.remove(0);                                               //Verwijder dit item
            } else {
                minMaxHeap.set(0, minMaxHeap.get(minMaxHeap.size() - 1));           //Vervang laatste item met eerste
                minMaxHeap.remove(minMaxHeap.size() - 1);                           //Verwijder laatste item
                pullDown(0);                                                            //Repareren.
            }
        }
        return min;
    }

    /**
     * Verwijder grootste item van DEPQ
     *
     * @return removed T
     */
    public T removeMax() {
        T max = getMax();                                                           //Hoogste item ophalen
        int index;
        if (max != null)                                                            //Extra check of er wel een item is, en dus NIET empty
            if (minMaxHeap.size() == 1) {                                           //Als er maar 1 item is.
                minMaxHeap.remove(0);                                         //Verwijder dit item
            } else {
                index = minMaxHeap.indexOf(max);                                    //Index ophalen van grootste item
                if (index == minMaxHeap.size() - 1) {                               //Als die achteraan staat
                    minMaxHeap.remove(index);                                       //Verwijderen, niet nodig om te repareren
                } else {                                                            //Anders
                    minMaxHeap.set(index, minMaxHeap.get(minMaxHeap.size() - 1));   //Vervang index met het achterste item.
                    minMaxHeap.remove(minMaxHeap.size() - 1);                 //Verwijder achterste item
                    pullDown(index);                                 //Repareren
                }
            }
        return max;
    }

    /**
     * Pull an item down the tree as long as possible
     * @param index of the item to be pulled down
     * @return true if at least one pull happened, false if not
     */
    private boolean pullDown(int index) {
        T item = minMaxHeap.get(index);
        boolean minLevel = isOnMinRow(index);

        int switchChildIndex = shouldSwitchWithChildren(index);
        int switchGrandchildIndex = shouldSwitchWithGrandchildren(index);

        int swapIndex = -1;

        if(switchChildIndex != -1 && switchGrandchildIndex != -1) {
            int comparison = minMaxHeap.get(switchChildIndex).compareTo(minMaxHeap.get(switchGrandchildIndex));
            if(minLevel) {
                if(comparison <= 0) {
                    swapIndex = switchChildIndex;
                }else {
                    swapIndex = switchGrandchildIndex;
                }
            }else {
                if(comparison >= 0) {
                    swapIndex = switchChildIndex;
                }else {
                    swapIndex = switchGrandchildIndex;
                }
            }
        }

        if(swapIndex == -1) {
            if(switchGrandchildIndex != -1) {
                swap(index, switchGrandchildIndex);
                pullDown(switchGrandchildIndex);
                return true;
            }else if(switchChildIndex != -1) {
                swap(index, switchChildIndex);
                pullDown(switchChildIndex);
                return true;
            }
        }else {
            swap(index, swapIndex);
            pullDown(swapIndex);
            return true;
        }

        return false;
    }

    /**
     * Checks if an item is standing correctly according to its children
     * @param index of the item to be checked
     * @return index of an invalid child, -1 if all children are valid
     */
    private int shouldSwitchWithChildren(int index) {
        T item = minMaxHeap.get(index);
        boolean minLevel = isOnMinRow(index);

        int childIndex = getNextChildIndex(index, minLevel);

        if(childIndex == -1) {
            return -1;
        }

        T child = minMaxHeap.get(childIndex);

        if(minLevel) {
            //Child moet kleiner/gelijk zijn
            if(child.compareTo(item) <= 0) {
                return childIndex;
            }
        }else {
            //Child moet groter/gelijk zijn
            if(child.compareTo(item) >= 0) {
                return childIndex;
            }
        }

        return -1;
    }

    /**
     * Checks if an item is standing correctly according to its grandchildren
     * @param index of the item to be checked
     * @return index of an invalid grandchild, -1 if all grandchildren are valid
     */
    private int shouldSwitchWithGrandchildren(int index) {
        T item = minMaxHeap.get(index);
        boolean minLevel = isOnMinRow(index);

        int childStartIndex = getChildrenStartingIndex(index);

        int leftGrandchildIndex = getNextChildIndex(childStartIndex, minLevel);
        int rightGrandchildIndex = getNextChildIndex(childStartIndex + 1, minLevel);

        if(leftGrandchildIndex == -1 && rightGrandchildIndex == -1) {
            return -1;
        }

        T potentialSwitch;
        int potentialIndex;

        if(leftGrandchildIndex == -1 || rightGrandchildIndex == -1) {
            if(leftGrandchildIndex == -1) {
                potentialSwitch = minMaxHeap.get(rightGrandchildIndex);
                potentialIndex = rightGrandchildIndex;
            }else {
                potentialSwitch = minMaxHeap.get(leftGrandchildIndex);
                potentialIndex = leftGrandchildIndex;
            }
        }else {
            T left = minMaxHeap.get(leftGrandchildIndex);
            T right = minMaxHeap.get(rightGrandchildIndex);

            if(minLevel) {
                if(left.compareTo(right) <= 0) {
                    potentialSwitch = left;
                    potentialIndex = leftGrandchildIndex;
                }else {
                    potentialSwitch = right;
                    potentialIndex = rightGrandchildIndex;
                }
            }else {
                if(left.compareTo(right) >= 0) {
                    potentialSwitch = left;
                    potentialIndex = leftGrandchildIndex;
                }else {
                    potentialSwitch = right;
                    potentialIndex = rightGrandchildIndex;
                }
            }
        }

        if(potentialSwitch != null) {
            if(minLevel){
                if(item.compareTo(potentialSwitch) >= 0) {
                    return potentialIndex;
                }
            }else {
                if(item.compareTo(potentialSwitch) <= 0) {
                    return potentialIndex;
                }
            }
        }

        return -1;
    }


    /**
     * Returns a string containing all items in the DEPQ
     * @return list with item values
     */
    @Override
    public String toString() {
        String numbers = "";
        for (T item : minMaxHeap) {
            numbers = numbers.concat(item.toString() + ",");
        }
        return numbers;
    }

    /**
     * Print the tree in console in ASCII
     * Usefull for visualising and double checking
     */
    public void printTree(){
        int depth = 1;
        int counter = 0;
        String savedString = "";
        int maxDepth = 0;

        //Calculate(FIND) max depth.
        while(Math.pow(2, maxDepth+1) < minMaxHeap.size()){
            maxDepth++;
        }

        for (T item : minMaxHeap){
            for(int i = 0; i < 50/Math.pow(2,depth-1); i++){    //Every line is max 50 characters long
                if(i == (50/depth-1)/2){                        //If position is in the middle of current sentence part -> insert value
                    savedString += item.toString();
                } else {
                    savedString += " ";
                }
            }
            counter++;
            if(counter >= Math.pow(2, depth-1)){                //Check if this was the last one in the current depth
                System.out.println(savedString);                //Sout the current saved string line
                depth++;
                counter = 0;
                savedString = "";                               //Clear saved string
            }
        }
        if(savedString.length() > 0) {
            System.out.println(savedString);                    //Just incase there is a depth partly filled -> write to console
        }
        System.out.println("");                                 //Add another white line to mark the end of the tree
    }

    /**
     * Update an item because the value might have changed from the outside
     * @param item to be updated
     */
    public void update(T item) {
        int index = minMaxHeap.indexOf(item);

        if(index == -1) {
            return;
        }

        if(pullDown(index) || pullUp(index)) {
            update(item);
        }
    }

    /**
     * Checks of the given index is on a min row or a max row
     * @param index of the position to check
     * @return true if on min row, false if max row
     */
    private boolean isOnMinRow(int index) {
        int currentIndex = 0;
        boolean minRow = true;

        while (index > currentIndex) {
            minRow = !minRow;
            currentIndex = (currentIndex * 2) + 2;
        }

        return minRow;
    }

    /**
     * Draai twee items met elkaar om
     *
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2) {
        T temp = minMaxHeap.get(index1);
        minMaxHeap.set(index1, minMaxHeap.get(index2));
        minMaxHeap.set(index2, temp);
    }

    /**
     * Get the index for the parent of the given index
     * @param index of the child
     * @return index of the parent
     */
    private int getParentIndex(int index) {
        return (int) Math.ceil((((double) index) / 2) - 1);
    }

    /**
     * Get the index where the children start, +1 for second child
     * @param index of parent
     * @return index of where the children start
     */
    private int getChildrenStartingIndex(int index) {
        return (index * 2) + 1;
    }

    /**
     * Get the smallest/largest child of given index
     * @param index of the parent
     * @param smallestChild look for the smallest or the largest child
     * @return the index of the largest child (-1 if not found)
     */
    private int getNextChildIndex(int index, boolean smallestChild) {
        int child1Index = getChildrenStartingIndex(index);
        int child2Index = child1Index + 1;

        if(child2Index <= minMaxHeap.size() - 1) {

            int comparison = minMaxHeap.get(child1Index).compareTo(minMaxHeap.get(child2Index));

            if(comparison <= 0) {
                if(smallestChild) {
                    return child1Index;
                }else {
                    return child2Index;
                }
            }else {
                if(smallestChild) {
                    return child2Index;
                }else {
                    return child1Index;
                }
            }

        } else if(child1Index <= minMaxHeap.size() - 1) {
            return child1Index;
        }

        return -1;
    }
}