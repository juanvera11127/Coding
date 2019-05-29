
public class LinkedList<T> implements ListInterface<T> {
	protected int size;
	protected Node<T> head;
	protected Node<T> tail;

	protected class Node<T> {
 		T item;
		Node next;

		Node(T o) {
			item = o;
			next = null;
		}
	}

	public T get(int index) {

		if (index >= 0 && index < size) {
			Node<T> current = head;
			int i = 0;
			while (i < index) {
				current = current.next;
				i++;
			}
			return current.item;
 		}
		else
			throw new ListIndexOutOfBoundsException("Index " + index + " out of bounds in get()");
	}

	public String toString() {
		String s = "(";
		Node<T> current = head;
		while (current != null) {
			s = s + current.item;
			if (current.next != null)
				s = s + ", ";
			current = current.next;
		}
		s = s + ")";
		return s;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	public void add(int index, T o) {
		if (index >=0 && index <= size) {
			if (index == 0) {
				// Adding to the front of the list
				Node<T> newNode = new Node<T>(o);
				newNode.next = head;
				head = newNode;
				if (tail == null)
					tail = newNode;
			}
			else if (index == size) {
				// Adding to the end of the list
				Node<T> newNode = new Node<T>(o);
				tail.next = newNode;
				tail = newNode;
			}
			else {
				// Adding in the middle of the list
				int i = 0;
				Node<T> current = head;
				while(i < index - 1) {
					current = current.next;
					i++;
				}
				Node<T> newNode = new Node<T>(o);
				newNode.next = current.next;
				current.next = newNode;
			}
			size++;
		}
		else {
			throw new ListIndexOutOfBoundsException("Index " + index + " out of bounds in add()");
		}

	}

	public void remove(int index) {
		if (index >= 0 && index < size) {
			if (index == 0) {
				// Removing an element from the front of the list
				head = head.next;
				if (head == null)
					tail = null;
			}
			else if (index == size - 1) {
				// Removing the last element of the list
				Node<T> current = head;
				for(int i = 0; i < size - 2; i++) {
					current = current.next;
				}
				tail = current;
				tail.next = null;
			}
			else {
				// Removing an element from the middle of the list
				Node<T> prev = head;
				for(int i = 1; i < index; i++) {
					prev = prev.next;
				}
				prev.next = prev.next.next;
			}
			size--;
		}
		else {
			throw new ListIndexOutOfBoundsException("Index " + index + " out of bounds in remove()");
		}
	}

	public void removeAll() {
		size = 0;
		head = null;
		tail = null;
	}

	public int find(T o) {
		Node<T> current = head;
		int i = -1;
		while(current != null) {
			i++;
			if (current.item.equals(o))
				return i;
			else
				current = current.next;
		}
		return -1;
	}

	public LinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
}
