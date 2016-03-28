package list;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: Node.java
 * Date: March 22, 2016
 *
 * Description: Abstract Node for a linked list.
 * 
 */


public abstract class Node<E>{

	protected Node<E> next;
	protected Node<E> prev;
	protected E data;
	
	public Node(){
	}
	
	public Node(Node<E> prev, Node<E> next, E data){
		this.next = next;
		this.prev = prev;
		this.data = data;
	}
	
	public Node(Node<E> prev, Node<E> next){
		this.next = next;
		this.prev = prev;
	}
	
	public Node(Node<E> next){
		this.next = next;
	}
	
	public void setNext(Node<E> next){
		this.next = next;
	}
	
	public void setPrev(Node<E> prev){
		this.prev = prev;
	}
	
	public void setData(E data){
		this.data = data;
	}
	
	public Node<E> next(){
		return next;
	}
	
	public Node<E> prev(){
		return prev;
	}
	
	public E getData(){
		return data;
	}
	
	public String toString(){
		return data.toString();
	}
	
	public abstract boolean isRoot();
	
	public abstract boolean hasNext();
}
