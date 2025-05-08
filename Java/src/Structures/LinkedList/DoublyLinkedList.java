package Structures.LinkedList;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements LinkedList<T> {
    Node<T> head, tail;

    @Override
    public void pushFront(T item) { //O(1)
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
    public T topFront() { //O(1)
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        return head.element;
    }

    @Override
    public T popFront() { //O(1)
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
    public void pushBack(T item) { //O(1)
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
    public T topBack() { //O(1)
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");

        return tail.element;
    }

    @Override
    public T popBack() { //O(1)
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
    public boolean find(T key) { //O(n)
        if(isEmpty())
            return false;

        for (Node<T> iter = head; !iter.element.equals(key); iter = iter.next)   
            if (iter.next == null)
                return false;
        return true;
    }

    @Override
    public Node<T> fetch(T key) { // O(n)
        if(isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");
        
        Node<T> iter = head;
        for (; !iter.element.equals(key); iter = iter.next)
            if (iter.next == null)
                throw new NoSuchElementException(key + " not found");
        return iter;
    }

    @Override
    public void erase(T key) { //O(n)
        if(isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");

        Node<T> iter = head;
        for (; !iter.element.equals(key); iter = iter.next)   
            if (iter.next == null)
                throw new NoSuchElementException(key + " not found");

        //iter es el elemento a borrar xd
        if (iter.prev != null) // si no es la cabeza
            iter.prev.next = iter.next;
        else {
            head = head.next;
            head.prev = null;
        }

        if (iter.next != null) // si no es la cola
            iter.next.prev = iter.prev;
        else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    @Override
    public boolean isEmpty() { //O(1)
        return head == null || tail == null;
    }

    @Override
    public void addBefore(T data, Node<T> key) { //O(1)
        if(key.equals(head)){
            pushFront(data);
            return;
        }

        Node<T> node = new Node<>(data);
        node.next = key;
        node.prev = key.prev;
        key.prev.next = node;
        key.prev = node;
    }

    @Override
    public void addAfter(T data, Node<T> key) { //O(1)
        if (key.equals(tail)){
            pushBack(data);
            return;
        }
        Node<T> node = new Node<>(data);
        node.next = key.next;
        node.prev = key;
        key.next.prev = node;
        key.next = node;
    }

    @Override
    public String toString() {
        String ret = "LinkedList: [";
        Node<T> iter = head;
        if (!isEmpty()){
            ret += iter;
            while(iter.next != null){
                iter = iter.next;
                ret += ", " + iter.element;
            }
        }
        ret += "]";

        ret += "\nReversed LinkedList: [";
        iter = tail;
        if (!isEmpty()){
            ret += iter;
            while(iter.prev != null){
                iter = iter.prev;
                ret += ", " + iter.element;
            }
        }
        return ret + "]";
    }
}