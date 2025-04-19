package Structures;

import Structures.LinkedList.SinglyLinkedList;
import Structures.LinkedList.SinglyLinkedListNT;
import Structures.Queue.*;
import Structures.Stack.Stack;

@SuppressWarnings("unused")
public class Main {
    public static void main(String[] args) {
        try {
            /* Stack 
            Stack<Integer> stack = new Stack<>(10);
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            stack.push(5);

            System.out.println(stack);

            stack.push(6);
            stack.push(7);
            stack.push(8);
            stack.push(9);
            stack.push(10);

            System.out.println(stack);

            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());

            System.out.println(stack);

            System.out.println(stack.pop());

            System.out.println(stack);
            */
            
            /* SimpleQueue 
            SimpleQueue simpleQueue = new SimpleQueue(10);

            simpleQueue.enqueue(1);
            simpleQueue.enqueue(2);
            simpleQueue.enqueue(3);
            simpleQueue.enqueue(4);
            simpleQueue.enqueue(5);
            simpleQueue.enqueue(6);
            simpleQueue.enqueue(7);
            simpleQueue.enqueue(8);
            simpleQueue.enqueue(9);
            simpleQueue.enqueue(10);

            System.out.println(simpleQueue);

            System.out.println(simpleQueue.dequeue());
            System.out.println(simpleQueue.dequeue());
            System.out.println(simpleQueue.dequeue());
            System.out.println(simpleQueue.dequeue());
            System.out.println(simpleQueue.dequeue());

            System.out.println(simpleQueue);
            */
        
            /* CircularQueue 
            CircularQueue circularQueue = new CircularQueue(5);
            circularQueue.enqueue(1);
            circularQueue.enqueue(2);
            circularQueue.enqueue(3);
            circularQueue.enqueue(4);
            circularQueue.enqueue(5);

            System.out.println(circularQueue);

            System.out.println(circularQueue.dequeue());
            System.out.println(circularQueue.dequeue());
            System.out.println(circularQueue.dequeue());

            System.out.println(circularQueue);

            circularQueue.enqueue(6);
            circularQueue.enqueue(7);
            circularQueue.enqueue(8);

            System.out.println(circularQueue);

            System.out.println(circularQueue.isEmpty());
            System.out.println(circularQueue.isFull());

            System.out.println(circularQueue.dequeue());
            System.out.println(circularQueue.dequeue());
            System.out.println(circularQueue.dequeue());
            System.out.println(circularQueue.dequeue());
            System.out.println(circularQueue.dequeue());

            System.out.println(circularQueue);

            System.out.println(circularQueue.isEmpty());
            System.out.println(circularQueue.isFull());
            */

            /* SinglyLinkedListNT 
            SinglyLinkedListNT<Integer> singlyLinkedListNT = new SinglyLinkedListNT<>();

            System.out.println(singlyLinkedListNT.isEmpty());
            singlyLinkedListNT.pushFront(22);

            System.out.println(singlyLinkedListNT.isEmpty());
            System.out.println(singlyLinkedListNT);

            singlyLinkedListNT.pushFront(33);
            singlyLinkedListNT.pushFront(44);
            singlyLinkedListNT.pushFront(55);
            System.out.println(singlyLinkedListNT);

            System.out.println(singlyLinkedListNT.popFront());
            System.out.println(singlyLinkedListNT);

            singlyLinkedListNT.pushBack(11);
            System.out.println(singlyLinkedListNT);

            System.out.println(singlyLinkedListNT.topBack());
            System.out.println(singlyLinkedListNT.popBack());
            System.out.println(singlyLinkedListNT);

            System.out.println(singlyLinkedListNT.find(33));

            singlyLinkedListNT.pushFront(55);
            singlyLinkedListNT.pushFront(66);
            singlyLinkedListNT.pushFront(77);
            singlyLinkedListNT.pushBack(11);
            System.out.println(singlyLinkedListNT);

            singlyLinkedListNT.erase(44);
            System.out.println(singlyLinkedListNT);

            singlyLinkedListNT.addAfter(44, 55);
            System.out.println(singlyLinkedListNT);
            
            singlyLinkedListNT.addBefore(88, 77);
            System.out.println(singlyLinkedListNT);

            SinglyLinkedListNT<Integer> nueva = new SinglyLinkedListNT<>();
            nueva.pushBack(2);
            System.out.println(nueva);
            nueva.addBefore(1, 3);
            System.out.println(nueva);
            */

            /* SinglyLinkedList */

            SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();

            System.out.println(singlyLinkedList.isEmpty());
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}