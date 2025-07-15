package Structures.AVLHashMap;

public class AVLHashMap<K, V extends Comparable<V>> {
    protected static class Node<K, V extends Comparable<V>> implements Comparable<Node<K, V>> {
        protected V data;
        // para el avl
        protected Node<K, V> left, right, parent;
        protected int height = 1;
        // para el hashmap
        protected K key;
        protected Node<K, V> next;
        
        /**
         * Node constructor
         * s
         * @param data data to store in the node
         * @param parent parent of the node
         */
        public Node(K key, V data, Node<K, V> parent) {
            this.key = key;
            this.data = data;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node<K, V> o) {
            return this.data.compareTo(o.data);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node<K, V> root;
    private Node<K, V>[] bucket;
    private int size, capacity;
    private static final float LOAD_FACTOR = 0.75f;

    public AVLHashMap(){
        this.capacity = 16;
    }

    public AVLHashMap(int capacity){
        this.capacity = capacity;
    }

    /**
     * Retrieves the value associated with the specified key.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key does not exist
     */
    public V get(K key) {
        int idx = hash(key);
        Node<K, V> curr = bucket[idx];
        while (curr != null){
            if (curr.key == key){
                return curr.data;
            }
        }
        return null;
    }


    public void insert(V data) { // TODO aca debe ser con K de la key xd
        root = insertRec(root, data, null);
    }

    public boolean add(K key, V value) {
        int idx = hash(key);
        Node<K, V> curr = bucket[idx];
        while (curr != null){
            if (curr.key == key){
                return false;
            }
            curr = curr.next;
        }
        bucket[idx] = new Node<K, V>(key, value, bucket[idx]); // TODO se crearian dos nodos? ... xd
        size++;
        return true;
    }

    /**
     * Insert a new node into the tree
     * 
     * @param node node to which we want to make a child
     * @param data data to create the child
     * @return updated node
     */
    protected Node<K, V> insertRec(Node<K, V> node, V data, Node<K, V> parent) {
        //if (node == null)
            //return postInsert(new Node<K, V>(data, parent), data);

        if (node.data.compareTo(data) > 0)
            node.left = insertRec(node.left, data, node);

        else if (node.data.compareTo(data) < 0)
            node.right = insertRec(node.right, data, node);

        else {
            System.out.println("El valor " + data.toString() + " ya existe en el arbol");
            return node;
        }
        return node;
    }
    
    public void remove(V data) { // TODO aca debe ser con K de la key xd
        root = removeRec(root, data);
    }

    /**
     * Remove a node from the tree
     * 
     * @param node node to remove
     * @param data data to remove
     * @return updated node
     */
    protected Node<K, V> removeRec(Node<K, V> node, V data) {
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
            node = node.right;

        } else if (node.right == null) { // if only has left child
            node.left.parent = node.parent;
            node = node.left;

        } else { // if has both children
            node.data = findMin(node.right).data;
            node.right = removeRec(node.right, node.data);

            // Alternatively, we could use the maximum of the left subtree
            // node.data = findMax(node.left).data;
            // node.left = removeRec(node.left, node.data);
        }
        return rebalance(node, data, false);
    }
    

    /*******************************************/
    /* ** metodos para la parte del HashMap ** */ // TODO solo es una referencia xd
    /*******************************************/

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

        /**
     * Resizes the hash map when the load factor exceeds the threshold.
     */
    private void resize(){

    }

    /**
     * Rehashes the keys in the hash map to a new bucket array.
     * This is called when resizing the hash map.
     * @param key The key to rehash.
     */
    private void rehash(K key){

    }

    public V replace(K key, V value) {
        int idx = hash(key);
        Node<K, V> curr = bucket[idx];
        while (curr != null){
            if (curr.key == key){
                V val = curr.data;
                curr.data = value;
                return val;
            }
            curr = curr.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null ? true : false;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
        for (int i = 0; i < capacity; i++){
            bucket[i] = null;
        }
    }


    /***************************************/
    /* ** metodos para la parte del AVL ** */ // TODO solo es una referencia xd
    /***************************************/


    /**
     * Calculates the height of the given node.
     * 
     * @param node the node to check
     * @return the height of the node
     */
    public int getBalance(Node<K, V> node) {
        return node != null ? getHeight(node.left) - getHeight(node.right) : 0;
    }

    /**
     * Rebalances the tree at the given node after an operation.
     * This method checks the balance and performs the necessary rotations.
     * @param node the root of the subtree to rebalance
     * @param data the data that was inserted, used to determine the rotation direction
     * @param ins tells if it is an isertion or a deletion. Useful if the conditionals change but the rotations don't
     */
    public Node<K, V> rebalance(Node<K, V> node, V data, boolean ins) {
        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if ((data.compareTo(node.left.data) > 0 && ins) || (getBalance(node.left) < 0 && !ins))
                node.left = leftRotation(node.left);
            return rightRotation(node);

        } else if (balance < -1) {
            if ((data.compareTo(node.right.data) < 0 && ins) || (getBalance(node.right) > 0 && !ins))
                node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

    /**
     * Performs a left rotation on the given node.
     * This is used to maintain the balance of the tree.
     * @param node the root of the subtree to rotate
     * @return the new root of the subtree
     */
    public Node<K, V> leftRotation(Node<K, V> node) {
        Node<K, V> newRoot = node.right;
        Node<K, V> TMP = newRoot.left;

        newRoot.left = node;
        node.right = TMP;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * Performs a right rotation on the given node.
     * This is used to maintain the balance of the tree.
     * @param node the root of the subtree to rotate
     * @return the new root of the subtree
     */
    public Node<K, V> rightRotation(Node<K, V> node) {
        Node<K, V> newRoot = node.left;
        Node<K, V> TMP = newRoot.right;

        newRoot.right = node;
        node.left = TMP;

        updateHeight(node);
        updateHeight(newRoot);

        return newRoot;
    }

    /**
     * find the minimum node in the tree rooted at the given node
     * 
     * @param node node to find minimum
     * @return minimum node
     */
    public Node<K, V> findMin(Node<K, V> node) {
        while (node != null && node.left != null)
            node = node.left;
        return node;
    }

    /**
     * Get the height of a node
     * 
     * @param node node to get height
     * @return height of the node
     */
    public int getHeight(Node<K, V> node) {
        return node == null ? 0 : node.height;
    }

    /**
     * Update the height of a node
     * 
     * @param node node to update height
     */
    public void updateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
    
    /**
     * InOrder Traversal
     */
    public void inOrder() { // InOrder
        System.out.println("Tree is: ");
        if (root != null)
            traversalRec(root);
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
    private void traversalRec(Node<K, V> node) {

        if (node.left != null)
            traversalRec(node.left);
        System.out.println(node.data);
        if (node.right != null)
            traversalRec(node.right);

    }

    public void printTree() {
        System.out.println("\n=== Horizontal tree ===\n");
        if (root == null) {
            System.out.println("(vacío)");
            return;
        }
        printTreeRec(root, "", true, false);
    }

    /**
     * The recursive method to print the tree
     */
    private void printTreeRec(Node<K, V> node, String prefix, boolean isRoot, boolean isLeft) {
        if (node == null) return;
        
        // Primero procesamos el hijo derecho (va hacia arriba)
        if (node.right != null) {
            printTreeRec(node.right, 
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
            printTreeRec(node.left, 
                prefix + (isRoot ? "" : (isLeft ? "    " : "│   ")), 
                false, true);
        }
    }
}