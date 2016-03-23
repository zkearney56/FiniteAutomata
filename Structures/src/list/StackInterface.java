package list;

public interface StackInterface<E> extends Iterable<E> {

	public E peek();
	public void push(E e);
	public E pop();
	public boolean isEmpty();
	public int count();
	public E[] toArray();
	
}
