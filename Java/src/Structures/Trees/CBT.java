package Structures.Trees;

import java.util.NoSuchElementException;

public class CBT<T> extends ArrayBasedTree<T>{

    public CBT(int capacity) {
        super(capacity);
    }

    public CBT(){}

    @Override
    public void insert(T element) {
        if (isFull())
            resize();
        tree[size++] = element;
    }

    @Override
    public boolean search(T element) {
        for(int i = 0; i < capacity; i++)
            if(tree[i].equals(element))
                return true;
        return false;
    }

    @Override
    public void remove(T element) {
        for(int i = 0; i < capacity; i++)
            if(tree[i].equals(element)){
                tree[i] = tree[--size];   // como no está organizado, se puede hacer xd
                return;
            }
        System.out.println(element + " Not found");
    }

    public T removeLast(){
        if(isEmpty())
            throw new NoSuchElementException("Empty Tree");
        return tree[--size];
    }

    @Override
    public void preOrder() {
        System.out.println("Tree is: ");
        if (size != 0)
            traversalRec(0, 1);
        else
            System.out.println("Empty");
        System.out.println("Fin \n");
    }

    @Override
    public void inOrder() {
        System.out.println("Tree is: ");
        if (size != 0)
            traversalRec(0, 2);
        else
            System.out.println("Empty");
        System.out.println("Fin \n");
    }

    @Override
    public void postOrder() {
        System.out.println("Tree is: ");
        if (size != 0)
            traversalRec(0, 3);
        else
            System.out.println("Empty");
        System.out.println("Fin \n");
    }

    private void traversalRec(int i, int type){
        if (i >= capacity || tree[i] == null) return;
        if (type == 1)
            System.out.println(tree[i]);
        if (tree[2 * i + 1] != null)
            traversalRec(2 * i + 1, type);  // hijo izquierdo
        if (type == 2)
            System.out.println(tree[i]);
        if (tree[2 * i + 2] != null)
            traversalRec(2 * i + 2, type);  // hijo derecho
        if (type == 3)
            System.out.println(tree[i]);        
    }

    @Override
    public void printTree() {
        System.out.println("\n=== Horizontal tree ===\n");
        if (tree[0] == null) {
            System.out.println("(vacío)");
            return;
        }
        printTreeRec(0, "", true, false);
    }

    /**
     * The recursive method to print the tree
     */
    private void printTreeRec(int i, String prefix, boolean isRoot, boolean isLeft) {
        if (i >= capacity || tree[i] == null) return;
    
        int rightChild = 2 * i + 2;
        int leftChild = 2 * i + 1;
        
        // Primero procesamos el hijo derecho (va hacia arriba)
        if (rightChild < capacity && tree[rightChild] != null) {
            printTreeRec(rightChild, 
                prefix + (isRoot ? "" : (isLeft ? "│   " : "    ")), 
                false, false);
        }
        
        // Luego imprimimos el nodo actual
        if (isRoot) {
            System.out.println(prefix + tree[i]);
        } else {
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + tree[i]);
        }
        
        // Finalmente procesamos el hijo izquierdo (va hacia abajo)
        if (leftChild < capacity && tree[leftChild] != null) {
            printTreeRec(leftChild, 
                prefix + (isRoot ? "" : (isLeft ? "    " : "│   ")), 
                false, true);
        }
    }

}