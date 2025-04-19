package Structures.LinkedList;
import java.util.NoSuchElementException;

public class SinglyLinkedListNT<T> implements LinkedList<T>{
    Node<T> head;

    @Override
    public void pushFront(T item) {
        Node<T> node = new Node<>(item);
        node.next = head;
        head = node;
    }

    @Override
    public T topFront() {
        if (isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");
        return head.element;
    }

    @Override
    public T popFront() {
        if(isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");

        T ret = head.element;
        head = head.next;
        return ret;
    }

    @Override
    public void pushBack(T item) {
        Node<T> node = new Node<>(item);
        if(head != null){

            Node<T> iter = head;
            for (;iter.next != null; iter = iter.next);

            iter.next = node;
        } else
            head = node;
    }

    @Override
    public T topBack() {
        if (isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");

        Node<T> iter = head;
        for (; iter.next != null; iter = iter.next);

        return iter.element;
    }

    @Override
    public T popBack() {
        if (isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");
        
        
        if(head.next == null){
            T ret = head.element;
            head = null;
            return ret;
        }

        Node<T> iter = head;
        for (;iter.next.next != null; iter = iter.next);

        T ret = iter.next.element;
        iter.next = null;
        return ret;
    }

    @Override
    public boolean find(T key) {
        if(isEmpty())
            return false;
        
        for (Node<T> iter = head; !iter.element.equals(key); iter = iter.next)
            if (iter.next == null)
                return false;
        return true;
    }

    @Override
    public void erase(T key) {
        if(isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");
        
        Node<T> iter = head;

        if (iter.element.equals(key))
            head = head.next; // si es el primero, entonces que head apunte al siguiente
        else if (iter.next == null)
            throw new NoSuchElementException(key + " not found");
        else {
            for (;!iter.next.element.equals(key); iter = iter.next){
                if (iter.next.next == null)
                    throw new NoSuchElementException(key + " not found");
            }
            iter.next = iter.next.next;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void addBefore(T data, T key) {
        Node<T> node = new Node<>(data);
    
        if(head.element.equals(key)) { // si se debe insertar antes del primer elemento
            node.next = head;
            head = node;
            return;
        }
        if (head.next == null) // si tiene solo un elemento pero no es el que se busca
            throw new NoSuchElementException(key + " not found");
        
        Node<T> iter = head;
        for (; !iter.next.element.equals(key); iter = iter.next)
            if(iter.next == null)
                throw new NoSuchElementException(key + " not found");
        node.next = iter.next;
        iter.next = node;
    }

    @Override
    public void addAfter(T data, T key) {
        Node<T> node = new Node<>(data);
        Node<T> iter = head;
        for (; !iter.element.equals(key); iter = iter.next)
            if(iter.next == null)
                throw new NoSuchElementException(key + " not found");
        
        node.next = iter.next;
        iter.next = node;
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