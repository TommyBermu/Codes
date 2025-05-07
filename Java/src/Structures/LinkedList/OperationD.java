package Structures.LinkedList;

@FunctionalInterface
public interface OperationD<T> {
    void apply(T value, T key);
}
