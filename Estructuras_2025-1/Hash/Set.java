package Structures.Hash;

/**
 * @author Tomas Bermudez
 */
public interface Set<K> {
    /**
     * Adds the specified element to the set.
     *
     * @param e the element to add
     * @return true if the element was added, false if it was already present
     */
    boolean add(K e);

    /**
     * Checks if the set contains the specified element.
     *
     * @param e the element to check for
     * @return true if the element is present, false otherwise
     */
    boolean remove(K e);

    /**
     * Checks if the set contains the specified element.
     *
     * @param e the element to check for
     * @return true if the element is present, false otherwise
     */
    boolean contains(K e);

    /**
     * Returns the number of elements in the set.
     *
     * @return the size of the set
     */
    int size();

    /**
     * Removes all elements from the set.
     */
    void clear();
}
