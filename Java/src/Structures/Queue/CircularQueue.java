package Structures.Queue;

public class CircularQueue<T> {
    private int capacity;
    private int head, tail;
    private T queue[];
    
    @SuppressWarnings("unchecked")
    public CircularQueue(int capacity){
        this.capacity = capacity;
        head = tail = -1;
        queue = (T[]) new Object[capacity];
    }

    public void enqueue(T element){
        if (isFull())
            throw new ArrayIndexOutOfBoundsException("Queue is full");
        queue[++tail%capacity] = element;
    }

    public T dequeue(){
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
        return queue[++head%capacity];
    }

    public boolean isEmpty(){
        return head >= tail;
    }

    public boolean isFull(){
        return tail-head >= capacity;
    }

    @Override
    public String toString() {
        String ret = "Queue: [";
        for(int i = 0; i < capacity; i++)
            ret += (i!=0?", ":"") + queue[i] + ((tail+1)%capacity == i ? "(t)" : "") + ((head+1)%capacity == i ? "(h)" : "");
        return ret + "]";
    }
}