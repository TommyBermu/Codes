package Structures.Trees;

public abstract class ArrayBasedTree<T> implements Tree<T> {
    protected T[] tree;
    protected int size = 0;
    protected int capacity;

    /**
     * Constructor para inicializar el árbol con una capacidad específica.
     * @param capacity la capacidad máxima del árbol
     */
    public ArrayBasedTree(int capacity) {
        tree = (T[]) new Comparable[capacity];
        this.capacity = capacity;
    }

    /**
     * Constructor para inicializar el árbol con una capacidad de 7.
     */
    public ArrayBasedTree() {
        tree = (T[]) new Comparable[7];
        this.capacity = 7;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public int getSize(){
        return this.size;
    }

    public void resize(){
        T temp[] = (T[]) new Object[capacity*2];
        for(int i = 0; i < capacity; i++)
            temp[i] = tree[i];
        
        this.capacity = capacity*2;
        this.tree = temp;
        System.out.println("resized");
    }

    public void setValue(int i, T data){
        if (i >= capacity) return;
        tree[i] = data;
    }

    public T getValue(int i){
        return tree[i];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == capacity;
    }
}