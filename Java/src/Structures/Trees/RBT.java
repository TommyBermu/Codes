package Structures.Trees;

/**
 * @author Tomas Bermudez
 *         This class is used when we have to do many insertions/deletions
 *         due to this class make rotations less frequently.
 */
public class RBT<T extends Comparable<T>> extends BST<T> implements SelfBalanced<T> {
    /**
     * Constructor (Empty)
     */
    public RBT() {
        super();
    }

    /**
     * Constructor with data
     * 
     * @param data data to insert as root
     */
    public RBT(T data) {
        super(data);
        this.root.red = false; // Root is always black
    }

    @Override
    protected Node<T> postInsert(Node<T> node, T data) {
        fixInsert(node);
        return node;
    }

    @Override
    public Node<T> postDelete(Node<T> node, T data) {
        return rebalance(node, data, false);
    }

    @Override
    public Node<T> rebalance(Node<T> node, T data, boolean ins) {
        if (node.parent != null && !node.parent.red)
            return node;
        //if () // case 1: uncle red

        // case 2.1: uncle black and this is a right child
        // case 2.2: uncle black and this si a left child

        if (node.parent == null) // case 0: is the root
            node.red = false;

        return node;
    }

    private void recolor(Node<T> node) {
        if (node == null || node.parent == null)
            return;

        boolean uncleRed = getColor(getUncle(node));

        if (uncleRed && node.parent.red) {
            getUncle(node).red = false;
            node.parent.red = false;
            getGrand(node).red = true;
        }
        recolor(node.parent.parent);
    }

    public void fixInsert(Node<T> node) {
        while (node.parent != null && node.parent.red){
            if (node.parent == node.parent.parent.left){
                Node<T> uncle = node.parent.parent.right;
                if(uncle.red){
                    node.parent.red = false;
                    uncle.red = false;
                    node.parent.parent.red = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotation(node);
                    }
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    rightRotation(node.parent.parent);
                }
            } else {
                Node<T> uncle = node.parent.parent.left;
                if(uncle.red){
                    node.parent.red = false;
                    uncle.red = false;
                    node.parent.parent.red = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotation(node);
                    }
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    leftRotation(node.parent.parent);
                }
            }
        }
        
    }

    private boolean getColor(Node<T> node) {
        return node == null ? false : node.red;
    }

    private Node<T> getGrand(Node<T> node) {
        if (node.parent == null)
            return null; // No grandparent if parent is null

        return node.parent.parent;
    }

    private Node<T> getUncle(Node<T> node) {
        Node<T> gp = getGrand(node);
        if (gp == null)
            return null;

        return gp.left == node.parent ? gp.right : gp.left;
    }

    @Override
    public Node<T> leftRotation(Node<T> node) {
        Node<T> newRoot = node.right;
        Node<T> TMP = newRoot.left;

        newRoot.left = node;
        node.right = TMP;

        return newRoot;
    }

    @Override
    public Node<T> rightRotation(Node<T> node) {
        Node<T> newRoot = node.left;
        Node<T> TMP = newRoot.right;

        newRoot.right = node;
        node.left = TMP;

        return newRoot;
    }
}