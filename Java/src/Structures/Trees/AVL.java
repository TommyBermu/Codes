package Structures.Trees;

public class AVL<T extends Comparable<T>> extends BinarySearchTree<T>{

    /**
     * Inserts a new node with the given data into the AVL tree.
     * This method overrides the insertBST method from BinarySearchTree
     */
    @Override
    protected Node<T> insertBST(Node<T> node, T data, Node<T> parent) {
        if (node == null)
            return new Node<T>(data, parent);
        
        if (node.data.compareTo(data) > 0)
            node.left = insertBST(node.left, data, node);

        else if (node.data.compareTo(data) < 0)
            node.right = insertBST(node.right, data, node);

        else {
            System.out.println("El valor " + data.toString() + " ya existe en el arbol");
            return node;
        }

        updateHeight(node);

        return rebalance(node, data);
    }

    /**
     * Removes a node with the given data from the AVL tree.
     * This method overrides the removeBST method from BinarySearchTree.
     */
    @Override
    public Node<T> removeBST(Node<T> node, T data) {
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

        return rebalanceRec(node, data);
    }

    /**
     * Calculates the height of the given node.
     * @param node the node to check
     * @return the height of the node
     */
    public int getBalance(Node<T> node){
        return node != null ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    /**
     * Rebalances the AVL tree at the given node after an insertion.
     * This method checks the balance factor of the node and performs the necessary rotations.
     * @param node the root of the subtree to rebalance
     * @param data the data that was inserted, used to determine the rotation direction
     */
    protected Node<T> rebalance(Node<T> node, T data) {
        int balance = getBalance(node);
        
        if (balance > 1) {
            if (data.compareTo(node.left.data) > 0)
                node.left = leftRotation(node.left);
            return rightRotation(node);

        } else if (balance < -1){
            if (data.compareTo(node.right.data) < 0)
                node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }       

    /**
     * Rebalances the AVL tree at the given node after an insertion.
     * This method checks the balance factor of the node and performs the necessary rotations.
     * @param node the root of the subtree to rebalance
     * @param data the data that was inserted, used to determine the rotation direction
     * @return the new root of the subtree after rebalancing
     */
    protected Node<T> rebalanceRec(Node<T> node, T data){
        int balance = getBalance(node);
        
        if (balance > 1) {
            if (getBalance(node.left) < 0)
                node.left = leftRotation(node.left);
            return rightRotation(node);

        } else if (balance < -1){
            if (getBalance(node.right) > 0)
                node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

    /**
     * Performs a left rotation on the given node.
     * This is used to maintain the balance of the AVL tree.
     * @param node the root of the subtree to rotate
     * @return the new root of the subtree
     */
    protected Node<T> leftRotation(Node<T> node){
        Node<T> newRoot = node.right;
        Node<T> TMP = newRoot.left;

        newRoot.left = node;
        node.right = TMP;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Performs a right rotation on the given node.
     * This is used to maintain the balance of the AVL tree.
     * @param node the root of the subtree to rotate
     * @return the new root of the subtree
     */
    protected Node<T> rightRotation(Node<T> node){
        Node<T> newRoot = node.left;
        Node<T> TMP = newRoot.right;

        newRoot.right = node;
        node.left = TMP;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }
}