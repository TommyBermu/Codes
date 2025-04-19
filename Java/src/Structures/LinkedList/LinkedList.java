package Structures.LinkedList;

public interface LinkedList<T> {
    static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev; // no usar en SinglyLinkedList :D

        public Node(T element){
            this.element = element;
        }

        public Node(){};

        @Override
        public String toString() {
            return "" + element;
        }
    }

    public void pushFront(T item);

    public T topFront();

    public T popFront();

    public void pushBack(T item);

    public T topBack();

    public T popBack();

    public boolean find(T key);

    public void erase(T key);

    public boolean isEmpty();

    public void addBefore(T data, T key);

    public void addAfter(T data, T key);
}
