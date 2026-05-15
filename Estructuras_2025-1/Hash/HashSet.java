package Structures.Hash;

public class HashSet<K> implements Set<K> {
    private HashMap<K, Object> hashMap;
    private final Object PRESENT = new Object();
    @Override
    public boolean add(K e) {
        return hashMap.add(e, PRESENT);
    }
    @Override
    public boolean remove(K e) {
        return hashMap.remove(e) != null ? true : false;
    }
    @Override
    public boolean contains(K e) {
        return hashMap.containsKey(e);
    }
    @Override
    public int size() {
        return hashMap.size();
    }
    @Override
    public void clear() {
        hashMap.clear();
    }
}