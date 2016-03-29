package list;


/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: Stack.java
 * Date: March 22, 2016
 *
 * Description: Implementation of a Stack.
 * 
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements StackInterface<E>{
	
	private int size = -1;
	private static final int INITIAL_CAPACITY = 10;
	private Object[] elementData = {};
	
	public Stack(){
		elementData = new Object[INITIAL_CAPACITY];
		size = -1;
	}
	
	public Stack(Object[] data){
		elementData = data;
		size = elementData.length;
	}
	
	public Stack(int capacity){
		elementData = new Object[capacity];
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new StackIterator<E>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public E peek() {
		return (E)elementData[size];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E pop() {
		E ele = (E)elementData[size];
		elementData[size] = null;
		size--;
		return ele;
	}

	@Override
	public boolean isEmpty() {
		if(size == -1){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public int count() {
		return size + 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		// TODO Auto-generated method stub
		return (E[])elementData;
	}

	@Override
	public void push(Object e) {
		if (size == elementData.length) {
			increaseCapacity();
		}
		size++;
		elementData[size] = e;
	}
	
	private void increaseCapacity(){
		int newCapacity = elementData.length * 2;
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	
	@SuppressWarnings("hiding")
	private class StackIterator<E> implements Iterator<E> {
		private int index = 0;

		public boolean hasNext() {
			return index < size+1;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			@SuppressWarnings("unchecked")
			E e = (E) elementData[index];
			index++;
			return e;
		}

		public void remove() {
			// NoRemoveFunction
		}
	}

}
