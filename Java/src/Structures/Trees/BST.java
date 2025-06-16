package Structures.Trees;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas Bermudez
 */
public class BST<T extends Comparable<T>> extends NodeBasedBinaryTree<T> {
    /**
     * Constructor (Empty)
     */
    public BST() {
    }

    /**
     * Constructor (T data)
     * 
     * @param data data to insert as root
     */
    public BST(T data) {
        insert(data);
    }

    @Override
    public void insert(T data) {
        root = insertRec(root, data, null);
    }

    /**
     * Insert a new node into the tree
     * 
     * @param node node to which we want to make a child
     * @param data data to create the child
     * @return updated node
     */
    protected Node<T> insertRec(Node<T> node, T data, Node<T> parent) {
        if (node == null)
            return new Node<T>(data, parent);

        if (node.data.compareTo(data) > 0)
            node.left = insertRec(node.left, data, node);

        else if (node.data.compareTo(data) < 0)
            node.right = insertRec(node.right, data, node);

        else {
            System.out.println("El valor " + data.toString() + " ya existe en el arbol");
            return node;
        }

        updateHeight(node);
        return node;
    }

    @Override
    public void remove(T data) {
        root = removeRec(root, data);
    }

    /**
     * Remove a node from the tree
     * 
     * @param node node to remove
     * @param data data to remove
     * @return updated node
     */
    protected Node<T> removeRec(Node<T> node, T data) {
        if (node == null) {
            System.out.println("Item not in Tree and not removed");
            return node;
        }

        /** Para encontrar el nodo **/
        if (node.data.compareTo(data) > 0)
            node.left = removeRec(node.left, data);

        else if (node.data.compareTo(data) < 0)
            node.right = removeRec(node.right, data);

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
            node.right = removeRec(node.right, node.data);
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
     * 
     * @param node node to update height
     */
    public void updateHeight(Node<T> node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    @Override
    public boolean search(T data) {
        return searchRec(root, data);
    }

    /**
     * Search for an element in the tree
     * 
     * @param node node to search
     * @param data element to search
     * @return true if found, false otherwise
     */
    private boolean searchRec(Node<T> node, T data) {
        if (node == null)
            return false;

        if (data.compareTo(node.data) == 0)
            return true;

        if (data.compareTo(node.data) < 0)
            return searchRec(node.left, data);
        else
            return searchRec(node.right, data);
    }

    /**
     * Fetch a node by its data
     * 
     * @param data data to fetch
     * @return fetched node
     */
    public Node<T> fetch(T data) {
        return fetchRec(root, data);
    }

    /**
     * Fetch a node by its data
     * 
     * @param node node to search
     * @param data data to fetch
     * @return fetched node
     */
    private Node<T> fetchRec(Node<T> node, T data) {
        if (node == null || data.compareTo(node.data) == 0)
            return node;

        if (data.compareTo(node.data) < 0)
            return fetchRec(node.left, data);
        else
            return fetchRec(node.right, data);
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
        /*
         * TODO ver como hacer esto xd
         * Node<T> predecesor, sucesor;
         * Node<T> node = root;
         * 
         * while (node != null) {
         * if (data.compareTo(node.data) < 0) {
         * sucesor = node;
         * node = node.left;
         * } else if (data.compareTo(node.data) > 0) {
         * predecesor = node;
         * node = node.right;
         * } else {
         * predecesor = prev(node);
         * sucesor = next(node);
         * break;
         * }
         * }
         */
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
     * find the inmediate ancestor node (in value) of a given node
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
     * find the inmediate predecessor node (in value) of a given node
     * 
     * @param node node to find predecessor
     * @return predecessor node
     */
    public Node<T> findPredecessor(Node<T> node) {
        if (node.parent != null && node.data.compareTo(node.parent.data) < 0)
            return findPredecessor(node.parent);
        return node.parent;
    }
}