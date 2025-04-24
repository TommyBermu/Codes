package Structures.LinkedList;

public class DoublyLinkedList<T> implements LinkedList<T> {
    Node<T> head, tail;

    @Override
    public void pushFront(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty()){
            head = tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
    }

    @Override
    public T topFront() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        return head.element;
    }

    @Override
    public T popFront() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        T ret = head.element;
        
        if (head.equals(tail)) // si se elimino el ultimo elemento, que la cola tambien se borre
            head = tail = null;
        else {
            head = head.next;
            head.prev = null;
        }
        return ret;
    }

    @Override
    public void pushBack(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty()){
            tail = head = node;
            return;
        }
        
        node.prev = tail;
        tail.next = node;
        tail = node;
    }

    @Override
    public T topBack() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        return tail.element;
    }

    @Override
    public T popBack() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        T ret = tail.element;

        if (head.equals(tail)) // si se elimino el unico elemento
            head = tail = null;
        else {
            tail = tail.prev;
            tail.next = null;
        }
    
        return ret;
    }

    @Override
    public boolean find(T key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public void erase(T key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'erase'");
    }

    @Override
    public boolean isEmpty() {
        return head == null || tail == null;
    }

    @Override
    public void addBefore(T data, T key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBefore'");
    }

    @Override
    public void addAfter(T data, T key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
    }

    @Override
    public String toString() {
        String ret = "LinkedList: [";
        Node<T> iter = head;
        if (!isEmpty())
            ret += iter;
        while(iter.next != null){
            iter = iter.next;
            ret += ", " + iter.element;
        }
        return ret + "]";
    }
}
