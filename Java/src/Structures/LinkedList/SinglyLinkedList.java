package Structures.LinkedList;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements LinkedList<T> {
    Node<T> head, tail;

    @Override
    public void pushFront(T data) { //O(1)
        Node<T> node = new Node<>(data);
        if (isEmpty())
            tail = node;
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
        if (head == tail) // si solo hay un elemento
            head = tail = null;
        else
            head = head.next;
        return ret;
    }

    @Override
    public void pushBack(T data) { //O(1)
        Node<T> node = new Node<>(data);
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
            T ret = tail.element;
            tail = head = null;
            return ret;
        }
        
        Node<T> iter = head;
        for (;iter.next.next != null; iter = iter.next);
        
        T ret = iter.next.element;
        iter.next = null;
        tail = iter;
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
        if (isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");

        if(head.element.equals(key)){
            popFront();
            return;
        }
            
        if(tail.element.equals(key)){
            popBack();
            return;
        }
        // si solo tiene un elemento
        if (head.next == null)
            throw new NoSuchElementException(key + " not found");
        
        // si no es el primero ni el ultimo
        Node<T> iter = head;
        for (; !iter.next.element.equals(key); iter = iter.next)
            if (iter.next == null) // si no se encontro el elemento
                throw new NoSuchElementException(key + " not found");
        
        iter.next = iter.next.next;
    }

    @Override
    public boolean isEmpty() { //O(1)
        return head == null || tail == null;
    }

    @Override
    public void addBefore(T data, Node<T> key) { // O(n)
        if (head.equals(key)){
            pushFront(data);
            return;
        }
        
        // si tiene mas de un elemento
        Node<T> iter = head;
        for (; !iter.next.equals(key); iter = iter.next);

        Node<T> node = new Node<>(data);
        node.next = iter.next;
        iter.next = node;
    }

    @Override
    public void addAfter(T data, Node<T> key) { // O(1)
        if (tail.equals(key)){
            pushBack(data);
            return;
        }
        
        Node<T> node = new Node<>(data);
        node.next = key.next;
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
        return ret + "]";
    }
}
