package Structures.Trees;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Bermudez
 */
public class BinarySearchTree<T extends Comparable<T>> {
    public static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
        public T data;
        public Node<T> left, right, parent;
        int height;

        /**
         * Node constructor
         * 
         * @param data data to store in the node
         */
        public Node(T data, Node<T> parent) {
            this.data = data;
            this.parent = parent;
            this.height = 1; // New nodes are initially added at leaf
        }

        @Override
        public int compareTo(Node<T> o) {
            return this.data.compareTo(o.data);
        }

        @Override
        public String toString() {
            return data.toString() + "(" + height + ")";
        }
    }

    /**
     * Root of the tree
     */
    protected Node<T> root;

    /**
     * Get the root node of the tree
     * 
     * @return root node
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     * Constructor (Empty)
     */
    public BinarySearchTree() {
    }

    /**
     * Constructor (T data)
     * 
     * @param data data to insert as root
     */
    public BinarySearchTree(T data) {
        insert(data);
    }

    /**
     * Insert a new node into the tree
     * 
     * @param data data to insert
     */
    public void insert(T data) {
        root = insertBST(root, data, null);
        printTree();
    }

    /**
     * Insert a new node into the tree
     * 
     * @param node node to which we want to make a child
     * @param data data to create the child
     * @return updated node
     */
    protected Node<T> insertBST(Node<T> node, T data, Node<T> parent) {
        if (node == null) {
            System.out.println("Se ha insertado: " + data);
            return new Node<T>(data, parent);
        }

        if (node.data.compareTo(data) > 0)
            node.left = insertBST(node.left, data, node);

        else if (node.data.compareTo(data) < 0)
            node.right = insertBST(node.right, data, node);

        else {
            System.out.println("El valor " + data.toString() + " ya existe en el arbol");
            return node;
        }

        updateHeight(node);
        return node;
    }

    /**
     * Remove a node from the tree
     * 
     * @param data data to remove
     */
    public void remove(T data) {
        root = removeBST(root, data);
    }

    /**
     * Remove a node from the tree
     * 
     * @param node node to remove
     * @param data data to remove
     * @return updated node
     */
    protected Node<T> removeBST(Node<T> node, T data) {
        if (node == null) {
            System.out.println("Item not in Tree and not removed");
            return node;
        }

        /** Para encontrar el nodo **/
        if (node.data.compareTo(data) > 0)
            node.left = removeBST(node.left, data);

        else if (node.data.compareTo(data) < 0)
            node.right = removeBST(node.right, data);

        /** Cuando ya encontramos el nodo **/
        else if (node.left == null && node.right == null) { // no children (leaf)
            return null;

        } else if (node.left == null) { // if only has right child
            node.right.parent = node.parent;
            return node.right;

        } else if (node.right == null) { // if only has left child
            node.left.parent = node.parent;
            return node.left;

        } else { // if has both children
            node.data = findMin(node.right).data;
            node.right = removeBST(node.right, node.data);
        }
        updateHeight(node);
        return node;
    }

    /**
     * Get the height of a node
     * 
     * @param node node to get height
     * @return height of the node
     */
    public int getHeight(Node<T> node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Update the height of a node
     * @param node node to update height
     */
    public void updateHeight(Node<T> node){
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * Fetch a node by its data
     * 
     * @param data data to fetch
     * @return fetched node
     */
    public Node<T> fetch(T data) {
        return fetchBST(root, data);
    }

    /**
     * Fetch a node by its data
     * 
     * @param node node to search
     * @param data data to fetch
     * @return fetched node
     */
    private Node<T> fetchBST(Node<T> node, T data) {
        if (node == null || data.compareTo(node.data) == 0)
            return node;

        if (data.compareTo(node.data) < 0)
            return fetchBST(node.left, data);
        else
            return fetchBST(node.right, data);
    }

    /**
     * Find the next node in the BST
     * 
     * @param data data to find next node
     * @return next node
     */
    public Node<T> next(Node<T> node) {
        if (node == null)
            return null;
        
        return node.right != null ? findMin(node.right) : findAncestor(node);
    }

    /**
     * Find the previous node in the BST
     * 
     * @param node data to find previous node
     * @return previous node
     */
    public Node<T> prev(Node<T> node) {
        if (node == null)
            return null;
        // If right subtree exists, return the minimum of the right subtree
        return node.left != null ? findMax(node.left) : findPredecessor(node);
    }

    /**
     * Nearest neighbors search
     * 
     * @param data data to search nearest neighbors
     */
    public void nearestNeighbors(T data) {
        // TODO Implement nearest neighbors search
    }

    /**
     * Range search
     * 
     * @param min min value
     * @param max max value
     * @return list of nodes within the range
     */
    public List<Node<T>> rangeSearch(T min, T max) {
        Node<T> node = findMin(root);
        List<Node<T>> result = new ArrayList<>();

        while (node != null) {
            if (node.data.compareTo(max) > 0)
                break;

            if (node.data.compareTo(min) >= 0)
                result.add(node);

            node = next(node);
        }
        return result;
    }

    /**
     * find the minimum node in the tree
     * 
     * @param node node to find minimum
     * @return minimum node
     */
    public Node<T> findMin(Node<T> node) {
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }

    /**
     * find the maximum node in the tree
     * 
     * @param node node to find maximum
     * @return maximum node
     */
    public Node<T> findMax(Node<T> node) {
        while (node != null && node.right != null)
            node = node.right;
        return node;
    }

    /**
     * find the immediate ancestor of a node
     * 
     * @param node node to find ancestor
     * @return ancestor node
     */
    public Node<T> findAncestor(Node<T> node) {
        if (node.parent != null && node.data.compareTo(node.parent.data) > 0)
            return findAncestor(node.parent);
        return node.parent;
    }

    /**
     * find the immediate predecessor of a node
     * 
     * @param node node to find predecessor
     * @return predecessor node
     */
    public Node<T> findPredecessor(Node<T> node) {
        if (node.parent != null && node.data.compareTo(node.parent.data) < 0)
            return findPredecessor(node.parent);
        return node.parent;
    }

    /**
     * InOrder Traversal
     */
    public void inOrderTraversal() { // InOrder
        System.out.println("Tree is: ");
        if (root != null)
            TraversalBST(root, 2);
        else
            System.out.println("Empty");
        System.out.println("Fin \n");
    }

    /**
     * PreOrder Traversal
     */
    public void preOrderTraversal() { // InOrder
        System.out.println("Tree is: ");
        if (root != null)
            TraversalBST(root, 1);
        else
            System.out.println("Empty");
        System.out.println("Fin \n");
    }

    /**
     * PostOrder Traversal
     */
    public void postOrderTraversal() { // InOrder
        System.out.println("Tree is: ");
        if (root != null)
            TraversalBST(root, 3);
        else
            System.out.println("Empty");
        System.out.println("Fin \n");
    }

    /**
     * Traversal BST
     * 
     * @param node node to traverse
     * @param type traversal type (1: PreOrder, 2: InOrder, 3: PostOrder)
     */
    private void TraversalBST(Node<T> node, int type) {
        if (type == 1)
            System.out.println(node.data);
        if (node.left != null)
            TraversalBST(node.left, type);
        if (type == 2)
            System.out.println(node.data);
        if (node.right != null)
            TraversalBST(node.right, type);
        if (type == 3)
            System.out.println(node.data);
    }

    /**
     * Print the tree in a better format
     */
    public void printTree() {
        System.out.println("\n=== Horizontal tree ===\n");
        if (root == null) {
            System.out.println("(vacío)");
            return;
        }
        printTreeBST(root, "", true, false);
    }

    /**
     * The recursive method to print the tree
     */
    private void printTreeBST(Node<T> node, String prefix, boolean isRoot, boolean isLeft) {
        if (node == null) return;
        
        // Primero procesamos el hijo derecho (va hacia arriba)
        if (node.right != null) {
            printTreeBST(node.right, 
                prefix + (isRoot ? "" : (isLeft ? "│   " : "    ")), 
                false, false);
        }
        
        // Luego imprimimos el nodo actual
        if (isRoot) {
            System.out.println(prefix + node);
        } else {
            System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node);
        }
        
        // Finalmente procesamos el hijo izquierdo (va hacia abajo)
        if (node.left != null) {
            printTreeBST(node.left, 
                prefix + (isRoot ? "" : (isLeft ? "    " : "│   ")), 
                false, true);
        }
    }
}