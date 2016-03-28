package list;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: List.java
 * Date: March 22, 2016
 *
 * Description: Implementation of a custom List interface.
 * 
 */

import java.util.Iterator;

public interface List<E> extends Iterable<E>{

	/**
	 * Adds an item to the list.
	 * @param e
	 */
	void add(E e);
	/**
	 * Adds an item to the list at a specific index.
	 * @param index
	 * @param e
	 */
	void add(int index, E e);
	/**
	 * Clears the list.
	 */
	void clear();
	/**
	 * Returns true if the list contains item e.
	 * @param e
	 * @return
	 */
	boolean contains(E e);
	/**
	 * Returns item at specific index.
	 * @param index
	 * @return
	 */
	E get(int index);
	/**
	 * Returns true if the list is empty.
	 * @return
	 */
	boolean isEmpty();
	/**
	 * Returns an iterator for the list.
	 */
	Iterator<E> iterator();
	/**
	 * Removes a specific object from the list.
	 * @param e
	 */
	void remove(E e);
	/**
	 * Removes and return object from list at specified index.
	 * @param index
	 * @return
	 */
	E remove(int index);
	/**
	 * Sets specific index to a specific item e.
	 * @param e
	 * @param index
	 */
	void set(E e, int index);
	/**
	 * Returns the size of the list.
	 * @return
	 */
	int size();
	/**
	 * Returns sublist from index1 to index2.
	 * @param index1
	 * @param index2
	 * @return
	 */
	List<E> subList(int index1, int index2);
	/**
	 * Returns list as an array object.
	 * @return
	 */
	E[] toArray();
	
}
