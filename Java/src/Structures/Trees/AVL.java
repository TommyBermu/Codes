package Structures.Trees;

public class AVL<T extends Comparable<T>> extends BinarySearchTree<T>{

    @Override
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

        return rebalance(node, data);
    }

    @Override
    public void remove(T data) {
        super.remove(data);

        // TODO metodo para rebalancear xd
    }

    /**
     * 
     * @param node 
     * @return 
     */
    public int getBalance(Node<T> node){
        return node != null ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    /**
     * 
     * @param node
     */
    protected Node<T> rebalance(Node<T> node, T data) {
        int balance = getBalance(node);
        
        if (balance > 1)
            if (data.compareTo(node.left.data) < 0)
                return rightRotation(node);
            else {
                node.left = leftRotation(node.left);
                return rightRotation(node);
            }
        else if (balance < -1)
            if (data.compareTo(node.right.data) > 0)
                return leftRotation(node);
            else {
                node.right = rightRotation(node.right);
                return leftRotation(node);
            }
        
        return node;
    }       

    /**
     * 
     * @param node
     * @return
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
     * 
     * @param node
     * @return
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