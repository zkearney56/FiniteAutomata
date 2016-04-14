package stack;

public class StackList<E> implements StackInterface<E> {

	private StackNode<E> top;
	private int size = 0;
	private int maxSize = 10000;
	
	public StackList(){
		top = new StackNode<E>(null, top);
	}
	
	public StackList(int maxSize){
		this.maxSize = maxSize;
		top = new StackNode<E>(null, top);
	}

	@Override
	public E peek() {
		return top.get();
	}

	@Override
	public void push(E e) {
		if(size < maxSize){
		size++;
		StackNode<E> newNode = new StackNode<E>(e,top);
		top = newNode;
		}
		else throw new Error("STACK FULL::MAXSIZE==" + maxSize);
		}

	@Override
	public E pop() {
		if(size > 0){
		E returnVal = top.get();
		top = top.link();
		size--;
		return returnVal;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if(size == maxSize){
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count() {
		return size;
	}

	/**
	public E[] toArray() {
		@SuppressWarnings("unchecked")
		Object[] array = new Object[size];
		StackNode<E> curr = new StackNode<E>(top.get(), top.link());
		for(int i = size - 1; i >= 0; i--){
			array[i] = curr.get();
			curr = curr.link();
		}
		// TODO Auto-generated method stub
		return (E[]) array;
	}
			
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@SuppressWarnings("hiding")
	class StackNode<E> {

		private E data;
		private StackNode<E> link;
		
		StackNode(E e, StackNode<E> link){
			data = e;
			this.link = link;
		}
		
		E get(){
			return data;
		}
		
		StackNode<E> link(){
			return link;
		}
	}
	
}
