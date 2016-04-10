package list;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: ArrayList.java
 * Date: March 22, 2016
 *
 * Description: Implementation of a custom ArrayList.
 * 
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

public class ArrayList<E> implements List<E>, Iterable<E>{

	private int size = 0;
	private Object[] elementData = {};
	private static final int INITIAL_CAPACITY = 10;
	
	public ArrayList(){
		elementData = new Object[INITIAL_CAPACITY];
		size = 0;
	}
	
	public ArrayList(E[] data){
		elementData = data;
		size = elementData.length;
	}
	
	public ArrayList(int capacity){
		elementData = new Object[capacity];
	}
	
	public ArrayList(ArrayList<E> clone){
		elementData = Arrays.copyOf(clone.elementData, clone.elementData.length);
		size = clone.size;
	}
	
	public void reStructure(){
		Object[] newData = new Object[size];
		for(int i = 0; i < size; i++){
			newData[i] = elementData[i];
		}
		elementData = newData;
	}
	
	public void add(E e) {
		if (size == elementData.length) {
			increaseCapacity();
		}
		elementData[size++] = e;
	}

	public void sort(){
		reStructure();
		Arrays.sort(elementData);
	}
	
	public void add(int index, E e) {
		if (size == elementData.length) {
			increaseCapacity();
		}
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = e;
		size++;	
	}

	public void clear() {
		elementData = new Object[INITIAL_CAPACITY];
		size = 0;
	}

	@Override
	public boolean contains(E e) {
		Iterator<E> itr = this.iterator();
		while(itr.hasNext()){
			if(itr.next() == e) return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		if (indexRange(index))
			throw new ArrayIndexOutOfBoundsException();
		return (E) elementData[index];
	}

	public boolean isEmpty() {
		if(size == 0) return true;
		else return false;
	}

	public void remove(E e) {
		int index = 0;
		Iterator<E> itr = this.iterator();
		while(itr.hasNext()){
			if(itr.next() == e) remove(index);
			index++;
		}
	}

	@SuppressWarnings("unchecked")
	public E remove(int index) {
		if (indexRange(index))
			throw new ArrayIndexOutOfBoundsException();
		Object e = elementData[index];
		for (int i = index; i < size - 1; i++) {
			
			elementData[i] = elementData[i + 1];
		}
		elementData[size - 1] = null;
		size--;
		return (E) e;
	}

	public void set(E e, int index) {
		if (indexRange(index))
			throw new ArrayIndexOutOfBoundsException();
		elementData[index] = e;
	}

	public int size() {
		return size;
	}

	public List<E> subList(int index1, int index2) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public E[] toArray() {
		return (E[]) elementData;
	}
	
	private void increaseCapacity(){
		int newCapacity = elementData.length * 2;
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	
	private boolean indexRange(int index) {
		if (index >= size || index < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void swap(int index1, int index2) {
		if (indexRange(index1) || indexRange(index2))
			throw new ArrayIndexOutOfBoundsException();
		Object e1 = elementData[index1];
		elementData[index1] = elementData[index2];
		elementData[index2] = e1;
	}
	
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}
	
	private int randNum() {
		int rand = ThreadLocalRandom.current().nextInt(0, size - 1);
		return rand;
	}

	public void shuffle() {
		for (int i = 1; i < size * 2; i++) {
			swap(i % size, randNum());

		}
	}
	
	public String toString(){
		return elementData.toString();
	}
	
	private class ArrayListIterator implements Iterator<E> {
		private int index = 0;

		public boolean hasNext() {
			return index < size;
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
