package list;

class RootNode<E> extends Node<E>{

		Node<E> link;
		
		public RootNode(){		
		}
		
		public void setLink(Node<E> link){
			this.link = link;
		}
		
		public void setPrev(Node<E> prev){
			link = prev;
		}
		
		public void setNext(Node<E> next){
			link = next;
		}
		
		public Node<E> next(){
			return link;
		}
		
		public Node<E> prev(){
			return link;
		}

		@Override
		public boolean isRoot() {
			return true;
		}

		@Override
		public boolean hasNext() {
			return false;
		}
		
	}