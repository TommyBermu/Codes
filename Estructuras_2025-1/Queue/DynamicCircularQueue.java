package Structures.Queue;

//TODO esta estructura no está testeada xd

public class DynamicCircularQueue<T> {
    private int capacity;
    private int head, tail;
    private T queue[];

    @SuppressWarnings("unchecked")
    public DynamicCircularQueue(){
        this.capacity = 2;
        head = tail = -1;
        queue = (T[]) new Object[this.capacity];
    }

    @SuppressWarnings("unchecked")
    public void resize(){
        
        T temp[] = (T[]) new Object[capacity*2];
        for(int i = 0; i < capacity; i++)
            temp[i] = queue[i];
        
        this.capacity = capacity*2;
        this.queue = temp;
    }

    public void enqueue(T element){
        if(isFull())
            resize();
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
