package Structures.LinkedList;
import java.util.NoSuchElementException;

public class SinglyLinkedListNT<T> implements LinkedList<T>{
    Node<T> head;

    @Override
    public void pushFront(T item) { // O(1)
        Node<T> node = new Node<>(item);
        node.next = head;
        head = node;
    }

    @Override
    public T topFront() { // O(1)
        if (isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");

        return head.element;
    }

    @Override
    public T popFront() { //O(1)
        if(isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");

        T ret = head.element;
        head = head.next;
        return ret;
    }

    @Override
    public void pushBack(T item) { // O(n)
        Node<T> node = new Node<>(item);
        if(!isEmpty()){
            Node<T> iter = head;
            for (;iter.next != null; iter = iter.next);

            iter.next = node;
        } else
            head = node;
    }

    @Override
    public T topBack() { // O(n)
        if (isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");

        Node<T> iter = head;
        for (; iter.next != null; iter = iter.next);

        return iter.element;
    }

    @Override
    public T popBack() { // O(n)
        if (isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");
        
        // si solo hay un elemento
        if(head.next == null){
            T ret = head.element;
            head = null;
            return ret;
        }

        // si hay mas de uno, osea head.next != null
        Node<T> iter = head;
        for (;iter.next.next != null; iter = iter.next);

        T ret = iter.next.element;
        iter.next = null;
        return ret;
    }

    @Override
    public boolean find(T key) { // O(n)
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
    public void erase(T key) { // O(n)
        if(isEmpty())
            throw new NoSuchElementException("Empty Linkedlist");
        
        Node<T> iter = head;
        if (iter.element.equals(key)){ // si es el primero
            head = head.next;
            return;
        }
        if (iter.next == null) // si no es el primero pero es el unico
            throw new NoSuchElementException(key + " not found");

        for (;!iter.next.element.equals(key); iter = iter.next){ // si hay dos o mas, osea iter.next != null
            if (iter.next.next == null)
                throw new NoSuchElementException(key + " not found");
        }
        iter.next = iter.next.next;
    }

    @Override
    public boolean isEmpty() { // O(1)
        return head == null;
    }

    @Override
    public void addBefore(T data, Node<T> key) { // O(n)
        if(head.equals(key)){// si se debe insertar antes del primer elemento
            pushFront(data);
            return;
        }
        
        Node<T> iter = head;
        for (; !iter.next.equals(key); iter = iter.next); // si tiene mas de uno, osea head.next != null

        Node<T> node = new Node<>(data);
        node.next = iter.next;
        iter.next = node;
    }

    @Override
    public void addAfter(T data, Node<T> key) { // O(1)
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