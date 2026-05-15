package Structures.Hash;

/**
 * @author Tomas Bermudez
 */
public interface Map<K, V> {
    /**
     * Adds a key-value pair to the map.
     * 
     * @param key   the key to add
     * @param value the value associated with the key
     * @return true if the key was added, false if it was already present
     */
    boolean add(K key, V value);

    /**
     * Replaces the value associated with the specified key.
     * 
     * @param key   the key whose value is to be replaced
     * @param value the new value to associate with the key
     * @return the previous value associated with the key, or null if the key did not exist
     */
    V replace(K key, V value);

    /**
     * Retrieves the value associated with the specified key.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key does not exist
     */
    V get(K key);

    /**
     * Removes the key-value pair associated with the specified key.
     * 
     * @param key the key whose associated value is to be removed
     * @return the value that was associated with the key, or null if the key did not exist
     */
    V remove(K key);

    /**
     * Checks if the map contains the specified key.
     * 
     * @param key the key to check for
     * @return true if the map contains the key, false otherwise
     */
    boolean containsKey(K key);

    /**
     * Returns the number of key-value pairs in the map.
     * 
     * @return the size of the map
     */
    int size();

    /**
     * Removes all key-value pairs from the map.
     */
    void clear();
}