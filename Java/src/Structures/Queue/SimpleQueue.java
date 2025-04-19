package Structures.Queue;

public class SimpleQueue<T> {
    private int capacity;
    private int top;
    private T[] queue;

    @SuppressWarnings("unchecked")
    public SimpleQueue(int capacity){
        this.capacity = capacity;
        top = -1;
        queue = (T[]) new Object[capacity];
    }

    public void enqueue(T element){
        if (!isFull())
            queue[++top] = element;
        else
            throw new ArrayIndexOutOfBoundsException("Queue is full");
    }

    public T dequeue(){
        if (!isEmpty()){
            T ret = queue[0];
            for (int i = 0; i < top; i++)
                queue[i] = queue[i+1];
            top--;
            return ret;
        }
        else
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
    }

    public T front(){
        if (!isEmpty())
            return queue[0];
        else
            throw new ArrayIndexOutOfBoundsException("Queue is empty");
    }

    public boolean isEmpty(){
        return top <= -1;
    }

    public boolean isFull(){
        return top >= capacity -1;
    }

    @Override
    public String toString() {
        String ret = "Queue: [";
        if(!isEmpty())
            ret += queue[0];
        for(int i = 1; i <= top; i++)
            ret += ", " + queue[i];
        return ret + "]";
    }
}
