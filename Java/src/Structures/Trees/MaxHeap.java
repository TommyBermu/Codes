package Structures.Trees;

public class MaxHeap<T extends Comparable<T>> extends CBT<T>{
    T maxPriority;

    public MaxHeap(int capacity, T maxPriority) {
        super(capacity);
        this.maxPriority = maxPriority;
    }

    public MaxHeap(T maxPriority) {
        super();
        this.maxPriority = maxPriority;
    }

    public MaxHeap(){
        super();
    }

    public void shiftUp(int i){
        while (i > 0 && tree[parent(i)].compareTo(tree[i]) < 0){
            swap(parent(i), i);
            i = parent(i);
        }
    }    
    
    public void shiftDown(int i){
        int maxIndex = i;
        int l = leftChild(i);
        int r = rightChild(i);

        if (l < size && tree[l].compareTo(tree[maxIndex]) > 0)
            maxIndex = l;

        if (r < size && tree[r].compareTo(tree[maxIndex]) > 0)
            maxIndex = r;

        if (i != maxIndex){
            swap(i, maxIndex);
            shiftDown(maxIndex);
        }
    }

    private void swap(int i, int j) {
        T temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }

    @Override
    public void insert(T element){
        super.insert(element);
        shiftUp(size-1);
    }

    public T extractMax(){
        T result = tree[0];
        tree[0] = tree[--size];
        shiftDown(0);
        return result;
    }

    @Override
    public void remove(T element){
        delete(getIndex(element));
    }

    public T delete(int i) {
        setValue(i, maxPriority);
        shiftUp(i);
        return extractMax();
    }

    public void changePriority(int i, T p){
        T oldp = tree[i];
        tree[i] = p;
        if(p.compareTo(oldp) > 0)
            shiftUp(i);
        else
            shiftDown(i);
    }

    public static <T extends Comparable<T>> void heapSort(T[] array){
        MaxHeap<T> mHeap = new MaxHeap<>();
        for (T item: array)  // n log n
            mHeap.insert(item);

        for(int i = array.length - 1; i >= 0; i--)  // n log n
            array[i] = mHeap.extractMax();
    }
}
