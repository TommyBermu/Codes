package Structures.Trees;

public class MaxHeap<T extends Comparable<T>> extends CBT<T> {

    /**
     * Constructor for creating a MaxHeap with a specified capacity and maximum
     * priority.
     * 
     * @param capacity    the maximum capacity of the heap
     * @param maxPriority the maximum priority value
     */
    public MaxHeap(int capacity, T maxPriority) {
        super(capacity);
        this.maxPriority = maxPriority;
    }

    /**
     * Constructor for creating a MaxHeap with a specified maximum priority.
     * 
     * @param maxPriority the maximum priority value
     */
    public MaxHeap(T maxPriority) {
        super();
        this.maxPriority = maxPriority;
    }

    /**
     * Default constructor for creating an empty MaxHeap.
     */
    public MaxHeap() {
        super();
    }

    private T maxPriority;

    /**
     * shifts the element at index i up in the heap to maintain the max-heap
     * property.
     * 
     * @param i the index of the element to shift up
     */
    public void shiftUp(int i) {
        while (i > 0 && tree[parent(i)].compareTo(tree[i]) < 0) {
            swap(tree, parent(i), i);
            i = parent(i);
        }
    }

    /**
     * shifts the element at index i down in the heap to maintain the max-heap
     * property.
     * 
     * @param i the index of the element to shift down
     */
    private static <T extends Comparable<T>> void shiftDown(T[] tree, int size, int i) {
        int maxIndex = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l < size && tree[l].compareTo(tree[maxIndex]) > 0)
            maxIndex = l;

        if (r < size && tree[r].compareTo(tree[maxIndex]) > 0)
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
     * Extracts the maximum element from the heap.
     * 
     * @return the maximum element
     */
    public T extractMax() {
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
     * Deletes the element at index i from the heap.
     * 
     * @param i the index of the element to delete
     * @return the deleted element
     */
    public T delete(int i) {
        setValue(i, maxPriority);
        shiftUp(i);
        return extractMax();
    }

    /**
     * Changes the priority of the element at index i.
     * 
     * @param i the index of the element to change
     * @param p the new priority value
     */
    public void changePriority(int i, T p) {
        T oldp = tree[i];
        tree[i] = p;
        if (p.compareTo(oldp) > 0)
            shiftUp(i);
        else
            shiftDown(tree, size, i);
    }

    /**
     * Performs heap sort on the given array.
     * 
     * @param array the array to sort
     */
    public static <T extends Comparable<T>> void heapSort(T[] array) {
        MaxHeap<T> mHeap = new MaxHeap<>();
        for (T item : array)
            mHeap.insert(item);

        for (int i = array.length - 1; i >= 0; i--)
            array[i] = mHeap.extractMax();
    }
}
