package list;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: StackInterface.java
 * Date: March 22, 2016
 *
 * Description: Implementation of a custom Stack interface.
 * 
 */


public interface StackInterface<E> extends Iterable<E> {

	/**
	 * Views the item at the top of the stack
	 * @return
	 */
	public E peek();
	
	/**
	 * Pushes an item to the top of the stack
	 * @param e
	 */
	public void push(E e);
	/**
	 * Pops the item from the top of the stack
	 * @return
	 */
	public E pop();
	/**
	 * Returns true if stack is empty
	 * @return
	 */
	public boolean isEmpty();
	/**
	 * Returns number of objects in stack
	 * @return
	 */
	public int count();
	/**
	 * Returns the stack as an array
	 * @return
	 */
	public E[] toArray();
	
}
