package Structures.LinkedList.Tests;

import Structures.LinkedList.LinkedList.Node;

@FunctionalInterface
public interface OperationD<T> {
    void apply(T value, Node<T> key);
}
