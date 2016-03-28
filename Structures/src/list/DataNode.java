package list;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: DataNode.java
 * Date: March 22, 2016
 *
 * Description: Implementation of a Data Node for a linked list.
 * 
 */

public class DataNode<E> extends Node<E>{

	public DataNode(Node<E> link, Node<E> end, E e) {
		super(link, end, e);
	}
	
	public DataNode(Node<E> prev, Node<E> next){
		super(prev,next);
	}
	public DataNode(Node<E> next){
		
	}
	
	public DataNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isRoot() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		if(this.next() instanceof RootNode){
			return false;
		}
		if(this.next() != null){
			return true;
		}
		return false;
	}

}
