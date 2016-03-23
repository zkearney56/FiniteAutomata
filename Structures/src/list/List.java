package list;

import java.util.Iterator;

public interface List<E> extends Iterable<E>{

	void add(E e);
	void add(int index, E e);
	void clear();
	boolean contains(E e);
	E get(int index);
	boolean isEmpty();
	Iterator<E> iterator();
	void remove(E e);
	E remove(int index);
	void set(E e, int index);
	int size();
	List<E> subList(int index1, int index2);
	E[] toArray();
	
}
