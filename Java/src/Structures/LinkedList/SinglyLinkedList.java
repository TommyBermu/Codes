package Structures.LinkedList;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements LinkedList<T> {
    Node<T> head, tail;

    @Override
    public void pushFront(T item) { //O(1)
        Node<T> node = new Node<>(item);
        if (isEmpty()){
            System.out.println("es una lista vacia");
            tail = node;
        }
        node.next = head;
        head = node;
    }

    @Override
    public T topFront() { //O(1)
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        return head.element;
    }

    @Override
    public T popFront() { //O(1)
        if(isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        T ret = head.element;
        head = head.next;
        tail = head.next == null ? head : tail; // si el tamaño de la lista es 1, al eliminar, que la cola sea null, si no, que se mantenga igual
        return ret;
    }

    @Override
    public void pushBack(T item) { //O(1)
        Node<T> node = new Node<>(item);
        if (isEmpty())
            head = node;
        else
            tail.next = node;
        tail = node;
    }

    @Override
    public T topBack() { //O(1)
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        return tail.element;
    }

    @Override
    public T popBack() { //O(n)
        if(isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        if (head == tail) { // si es el unico elemento
            T ret = head.element;
            head = tail = null;
            return ret;
        }
        
        Node<T> iter = head;
        while (iter.next.next != null)
            iter = iter.next;
        T ret = iter.next.element;
        iter.next = null;
        tail = iter;
        return ret;
    }

    @Override
    public boolean find(T key) { //O(n)
        if(isEmpty())
            return false;

        for (Node<T> iter = head; iter.next != null; iter = iter.next)
            if (iter.element.equals(key))
                return true;
        return false;
    }

    @Override
    public void erase(T key) {
        if (isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");
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
