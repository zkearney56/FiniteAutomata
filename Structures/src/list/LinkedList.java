package list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E> {

	private RootNode<E> root, end;
	private int size = 0;
	
	public LinkedList(){
		root = new RootNode<E>();
		end = new RootNode<E>();
		root.link = end;
		size = 0;
	}
	
	@Override
	public void add(E e) {
		Node<E> add = new DataNode<E>();
		add.setData(e);
		if(isEmpty()){				
			add.setNext(end);
			root.link = add;
			size++;
		}
		else{
			Node<E> currNode = root;
			while(!(currNode.next instanceof RootNode)){
				currNode = currNode.next;
			}
			currNode.setNext(add);
			add.setNext(end);
			size++;
		}
	}

	@Override
	public void add(int index, E e) {
		if(indexRange(index))
			throw new IndexOutOfBoundsException();
		if(index == size){
			add(e);
			return;
		}
		else if(index == 0){
			Node<E> next = root.link;
			Node<E> newNode = new DataNode<E>();
			newNode.next = next;
			root.link = newNode;
			
		}
		int i = 0;
		Node<E> current = root;
		while(i < index){
			current = current.next();
			i++;
		}
		Node<E> next = current.next();
		Node<E> newNode = new DataNode<E>();
		newNode.setData(e);
		newNode.setNext(next);
		current.setNext(newNode);
		size++;
	}

	@Override
	public void clear() {
		root.link = end;
		size = 0;
	}

	@Override
	public boolean contains(E e) {
		if(size==0) return false;
		Node<E> current = root.link;
		while(true){
			if(current.next() instanceof RootNode) return false;
			if(current.getData() == e) return true;
			current = current.next();
		}
	}

	@Override
	public E get(int index) {
		if(indexRange(index))
			throw new IndexOutOfBoundsException();
		int i = 0;
		Node<E> current = root.link;
		while(i < index){
			current = current.next();
			i++;
		}
		return current.getData();
	}

	@Override
	public boolean isEmpty() {
		if(size==0) return true;
		else return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private void remove(Node<E> node) {
		Node<E> currNode = root;
		Node<E> prevNode = root;
		while(currNode != node){
			prevNode = currNode;
			currNode = currNode.next();
		}
		prevNode.setNext(currNode.next());
		size--;
	}

	@Override
	public E remove(int index) {
		if(isEmpty()) throw new IndexOutOfBoundsException();
		Node<E> current = root.link;
		for(int i = 0; i < index; i ++){
			current = current.next();
		}
		E e = current.getData();
		remove(current);
		return e;
	}
	
	public void remove(E e) {
		if(isEmpty()) throw new NoSuchElementException();
		Node<E> current = root.link;
		while(!(current.next() instanceof RootNode)){
			if(current.getData() == e){
				Node<E> next = current.next();
				remove(current);
				current = next;
			}
			current = current.next();
		}
	}
	@Override
	public void set(E e, int index) {
		if(isEmpty()) throw new IndexOutOfBoundsException();
		Node<E> current = root.link;
		for(int i = 0; i < index; i ++){
			current = current.next();
		}
		current.setData(e);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int index1, int index2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean indexRange(int index) {
		if (index > size || index < 0) {
			return true;
		} else {
			return false;
		}
	}

	class LinkedListIterator implements Iterator<E>{
		private int index = 0;
		private Node<E> current = root.link;
		@Override
		public boolean hasNext() {
			if(current.next() instanceof RootNode) return false;
			return index < size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			if(current instanceof RootNode){
				current = root.link;
				index++;
				return (E) current;
			}
			else{
				current = current.next();
				index++;
				return (E) current;
			}
		}
		
	}
}
