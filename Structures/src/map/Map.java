package map;

public interface Map<K,V> {

	void clear();
	boolean containsKey(K key);
	boolean containsValue(V value);
	V get(K key);
	boolean isEmpty();
	V remove(K key);
	int size();
	void put(K key, V value);
	
}
