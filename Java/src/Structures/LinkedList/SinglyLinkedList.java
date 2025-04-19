package Structures.LinkedList;

public class SinglyLinkedList<T> implements LinkedList<T> {
    Node<T> head, tail;

    @Override
    public void pushFront(T item) { //O(1)
        Node<T> node = new Node<>(item);
        node.next = head;
        head = node;
        if (isEmpty())
            tail = node;
    }

    @Override
    public T topFront() { //O(1)
        if (!isEmpty())
            return head.element;
        else 
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");
    }

    @Override
    public T popFront() { //O(1)
        if(!isEmpty()){
            T ret = head.element;
            head = head.next;
            tail = head.next == null ? head : tail; // si el tamaño de la lista es 1, al eliminar, que la cola sea null, si no, que se mantenga igual
            return ret;
        }
        else
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");
    }

    @Override
    public void pushBack(T item) { //O(1)
        Node<T> node = new Node<>(item);
        node.next = tail;
        tail = node;
        if (isEmpty())
            head = node;
    }

    @Override
    public T topBack() { //O(1)
        if (!isEmpty())
            return tail.element;
        else
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");
    }

    @Override
    public T popBack() { //O(1)
        if(!isEmpty()){
            T ret = tail.element;
            tail = tail.prev; // TODO es solo con un enlace xd
            tail.next = null; // TODO ver si esta bien
            head = head.next == null ? tail : head; // si el tamaño de la lista es 1, al eliminar, que la cola sea null, si no, que se mantenga igual
            return ret;
        }
        else
            throw new ArrayIndexOutOfBoundsException("Empty Linkedlist");
    }

    @Override
    public boolean find(T key) { //O(n)
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
        return head == null;
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
        return "sapo";
    }
}
