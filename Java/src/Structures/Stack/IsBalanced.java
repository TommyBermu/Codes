package Structures.Stack;

import java.io.File;
import java.util.Scanner;

// Nodo genérico
class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Pila genérica usando listas enlazadas
class MyStack<T> {
    private Node<T> top;

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) throw new RuntimeException("Stack underflow");
        T ret = top.data;
        
        top = top.next;
        
        return ret;
    }

    public T peek() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

public class IsBalanced {

    public static boolean isBalanced(String s) {
        MyStack<Character> stack = new MyStack<>();
        for (int i = 0; i < s.length(); i++){
            String apertura = "([{";
            char c = s.charAt(i);
            if(apertura.indexOf(c) > -1){
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;
                char elemento = stack.pop();
                if (elemento == '(' && c != ')' || elemento == '[' && c != ']' || elemento == '{' && c != '}')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) throws Throwable {
        Scanner sc = new Scanner(new File("Java/src/Structures/Stack/Input.txt"));
        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) break;
            System.out.println(isBalanced(input) ? "YES" : "NO");
        }
        sc.close();
    }
}
