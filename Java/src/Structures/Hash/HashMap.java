package Structures.Hash;

/**
 * @author Tomas Bermudez
 */
public class HashMap<K, V> implements Map<K, V> {
    /**
     * Node class representing a key-value pair in the hash map.
     */
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] buckets;
    private int size, capacity;
    private static final float LOAD_FACTOR = 0.75f;

    public HashMap(){
        this.size = 16;
    }

    public HashMap(int size){
        this.size = size;
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public boolean add(K key, V value) {
        int idx = hash(key);
        Node<K, V> curr = buckets[idx];
        while (curr != null){
            if (curr.key == key){
                return false;
            }
            curr = curr.next;
        }
        buckets[idx] = new Node<K, V>(key, value, buckets[idx]);
        size++;
        return true;
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
    
    @Override
    public V replace(K key, V value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'replace'");
    }

    @Override
    public V get(K key) {
        int idx = hash(key);
        Node<K, V> curr = buckets[idx];
        while (curr != null){
            if (curr.key == key){
                return curr.value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null ? true : false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < capacity; i++){
            buckets[i] = null;
        }
    }
}