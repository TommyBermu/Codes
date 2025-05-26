package Structures.Trees;

/**
 * @author Tomas Bermudez
 */
public class BinarySearchTree<T extends Comparable<T>> {
    public static class Node<T extends Comparable<T>> implements Comparable<Node<T>>{
        public T data;
        public Node<T> left, right;

        public Node(T data){
            this.data = data;
        }

        @Override
        public int compareTo(Node<T> o) {
            return this.data.compareTo(o.data);
        }
    }

    public Node<T> root;

    public BinarySearchTree(){}

    /**
     * @param data data to insert 
     */
    public void insert(T data){
        root = insertBST(root, data);
    }

    /**
     * @param node node to which we want to make a child 
     * @param data data to create the child
     */
    private Node<T> insertBST(Node<T> node, T data){
        if (node == null){
            System.out.println("Se ha insertado: " + data);
            return new Node<T>(data);
        }

        if (node.data.compareTo(data) > 0)
            node.left = insertBST(node.left, data);
        else if (node.data.compareTo(data) < 0)
            node.right = insertBST(node.right, data);
        else
            System.out.println("El valor " + data.toString() + " ya existe en el arbol");

        return node;
    }

    public void remove(T data){
        root = removeBST(root, data);
    }
    
    /**
     * @param node 
     * @param data
     * @return 
     */
    private Node<T> removeBST(Node<T> node, T data){
        if (node == null) {
            System.out.println("Item not in Tree and not removed");
            return node;
        }

        if (node.data.compareTo(data) > 0)
            node.left = removeBST(node.left, data);

        else if (node.data.compareTo(data) < 0)
             node.right = removeBST(node.right, data);

        else if (node.left != null && node.right != null)
            return null;

        else if (node.left == null)
            return node.right;

        else if (node.right == null)
            return node.left;

        else
            node.right = removeBST(node.right, findMin(node.right).data);

        return node;
    }

    private Node<T> findMin(Node<T> node){
        if(node != null)
            while (node.left != null)
                node = node.left;
        
        return node;        
    }

    private Node<T> findMax(Node<T> node){
        if(node != null)
            while (node.right != null)
                node = node.right;
        
        return node;        
    }
    
    public void recorrer(){ // InOrder
        System.out.println("Tree is: ");
        if (root != null)
            recorrerBST(root);
        else
            System.out.println("Empty");
        System.out.println("Fin \n");
    }

    private void recorrerBST(Node<T> node){
        if(node.left != null)
            recorrerBST(node.left);
        System.out.println(node.data);
        if(node.right != null)
            recorrerBST(node.right);
    }
}

