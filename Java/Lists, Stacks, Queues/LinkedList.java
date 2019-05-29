
public class LinkedList<T> implements ListInterface<T> {


	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public class Node<T> {
		private T data;
		private Node<T> next;
		
		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	public LinkedList() {
		head = null;
		tail = null;
	}
	
	@Override
	public void add(T data, int index) {

		if(index < 0 || index > size) {
			throw new ListException("Error: Out of Bounds");
		}
		if(index == size && size > 0) {
			tail.next = new Node<T>(data, null);
			tail = tail.next;
		}
		else if(index == 0) {
			head = new Node<T>(data, head);
			if(size == 0) {				
				tail = head;
			}
		}
		else {
			Node<T> curr = head;
			while(index > 1) {
				curr = curr.next;
				index--;
			}
			curr.next = new Node<T>(data, curr.next);			
		}
		size++;
	}

	@Override
	public T get(int index) {
		if(index == 0) {
			return head.data;
		}
		if(index < 0 || index > size-1) {
			throw new ListException("Error: Out of Bounds");
		}
		if(index == size) {
			return tail.data;
		}
		Node<T> curr = head;
		while(index > 0) {
			curr = curr.next;
			index--;
		}
		return curr.data;
	}

	@Override
	public void remove(int index) {
		if(size == 0) {
			throw new ListException("Error: Empty List");
		}
		else if(size == 1) {
			head = null;
			tail = null;
		}
		else if(index == 0) {
			head = head.next;
		}
		else if(index == size-1) {
			Node<T> curr = head;
			while(index > 1) {
				curr = curr.next;
				index--;
			}
			tail = curr;
			curr.next = null;
		}
		else if(index < size-1 && index>0){
			Node<T> curr = head;
			while(index > 1) {
				curr = curr.next;
				index--;
			}
			curr.next = curr.next.next;
		}
		else {
			throw new ListException("Error: Out of Bounds");
		}
		size--;
	}

	@Override
	public int size() {
		return size;
	}
	
	public String toString() {
		if(size == 0) {
			return "()";
		}
		String temp = "(";
		Node<T> curr = head;
		int count = size;
		
		while(count > 0) {
			if(count == 1) {
				temp += curr.data + ")";
			}
			else {				
				temp += curr.data + ", ";
			}
			curr = curr.next;
			count--;
		}
		return temp;		
	}
}


