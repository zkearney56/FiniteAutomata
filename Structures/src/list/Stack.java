package list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements StackInterface{
	
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
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new StackIterator();
	}

	@Override
	public Object peek() {
		return elementData[size];
	}

	@Override
	public Object pop() {
		Object ele = elementData[size];
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
		return size+1;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return elementData;
	}

	@Override
	public void push(Object e) {
		if (size == elementData.length) {
			increaseCapacity();
		}
		elementData[size++] = e;
	}
	
	private void increaseCapacity(){
		int newCapacity = elementData.length * 2;
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	
	private class StackIterator implements Iterator<E> {
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