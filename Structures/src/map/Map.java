package map;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: Map.java
 * Date: March 22, 2016
 *
 * Description: Interface for implementation of a Map.
 * 
 */

public interface Map<K,V> {

	/**
	 * Clears the map.
	 */
	void clear();
	/**
	 * Returns true if map contains key K.
	 * @param key
	 * @return
	 */
	boolean containsKey(K key);
	/**
	 * Returns true if map contains value V.
	 * @param value
	 * @return
	 */
	boolean containsValue(V value);
	/**
	 * Returns value from specified key.
	 * @param key
	 * @return
	 */
	V get(K key);
	/**
	 * Returns true if map is empty.
	 * @return
	 */
	boolean isEmpty();
	/**
	 * Removes value from specified key and returns the value.
	 * @param key
	 * @return
	 */
	V remove(K key);
	/**
	 * Returns size of map.
	 * @return
	 */
	int size();
	/**
	 * Puts Key-Value pair into map.
	 * @param key
	 * @param value
	 */
	void put(K key, V value);
	
}
