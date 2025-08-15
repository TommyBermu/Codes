package Structures.Trees;

public class MinHeap<T extends Comparable<T>> extends CBT<T> {
    /**
     * Constructor for creating a MinHeap with a specified capacity and minimum
     * priority.
     * 
     * @param capacity    the maximum capacity of the heap
     * @param minPriority the minimum priority value
     */
    public MinHeap(int capacity, T minPriority) {
        super(capacity);
        this.minPriority = minPriority;
    }

    /**
     * Constructor for creating a MinHeap with a specified minimum priority.
     * 
     * @param minPriority the minimum priority value
     */
    public MinHeap(T minPriority) {
        super();
        this.minPriority = minPriority;
    }

    /**
     * Default constructor for creating an empty MinHeap.
     */
    public MinHeap() {
        super();
    }

    private T minPriority;

    /**
     * shifts the element at index i up in the heap to maintain the min-heap
     * property.
     * 
     * @param i the index of the element to shift up
     */
    private void shiftUp(int i) {
        while (i > 0 && tree[parent(i)].compareTo(tree[i]) > 0) {
            swap(tree, parent(i), i);
            i = parent(i);
        }
    }

    /**
     * shifts the element at index i down in the heap to maintain the min-heap
     * property.
     * 
     * @param i the index of the element to shift down
     */
    private static <T extends Comparable<T>> void shiftDown(T[] tree, int size, int i) {
        int maxIndex = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l < size && tree[l].compareTo(tree[maxIndex]) < 0)
            maxIndex = l;

        if (r < size && tree[r].compareTo(tree[maxIndex]) < 0)
            maxIndex = r;

        if (i != maxIndex) {
            swap(tree, i, maxIndex);
            shiftDown(tree, size, maxIndex);
        }
    }

    @Override
    public void insert(T element) {
        super.insert(element);
        shiftUp(size - 1);
    }

    /**
     * Extracts the minimum element from the heap, which is the root of the heap.
     * 
     * @return the minimum element in the heap
     */
    public T extractMin() {
        T result = tree[0];
        tree[0] = tree[--size];
        shiftDown(tree, size, 0);
        return result;
    }

    @Override
    public void remove(T element) {
        delete(getIndex(element));
    }

    /**
     * Deletes the element at index i in the heap and returns it.
     * 
     * @param i the index of the element to delete
     * @return the deleted element
     */
    public T delete(int i) {
        setValue(i, minPriority);
        shiftUp(i);
        return extractMin();
    }

    /**
     * Changes the priority of the element at index i to the new value p.
     * 
     * @param i the index of the element whose priority is to be changed
     * @param p the new priority value
     */
    public void changePriority(int i, T p) {
        T oldp = tree[i];
        tree[i] = p;
        if (p.compareTo(oldp) < 0)
            shiftUp(i);
        else
            shiftDown(tree, size, i);
    }

    /**
     * Performs heap sort on the given array using the MinHeap structure, it sort
     * the array in descending order.
     * 
     * @param <T>   the type of elements in the array, which must be comparable
     * @param array the array to be sorted
     */
    public static <T extends Comparable<T>> void heapSort(T[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            shiftDown(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            shiftDown(array, i, 0);
        }
    }
}
