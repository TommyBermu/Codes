package Structures.Stack;

public class Stack<T> {
    private int capacity;
    private int top;
    private T[] stack;
    
    @SuppressWarnings("unchecked")
    public Stack(int capacity){
        this.top = -1;
        this.stack = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public void push(T elemento){
        if (isFull())
            throw new ArrayIndexOutOfBoundsException("Stack is full");
        stack[++top] = elemento;
          
    }

    public T pop(){
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Stack is empty"); 
        return stack[top--];
    }

    public T top(){
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Stack is empty");
        return stack[top];
    }

    public boolean isFull(){
        return top >= capacity -1;
    }

    public boolean isEmpty(){
        return top <= -1;
    }

    @Override
    public String toString() {
        String ret = "Stack: [";
        if(!isEmpty())
            ret += stack[0];
        for(int i = 1; i <= top; i++)
            ret += ", " + stack[i];
        return ret + "]";
    }
}