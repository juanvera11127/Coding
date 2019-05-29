
public class Stack<T> implements StackInterface<T>{

	private int size = 0;
	private LinkedList<T> list;
	
	public Stack() {
		list = new LinkedList<T>();
		size = 0;
	}
	@Override
	public void push(T data) {
		list.add(data, 0);
		size++;
	}

	@Override
	public T pop() {
		if(size == 0) {
			throw new StackException("Error: Stack is empty");
		}
		T temp = list.get(0);
		list.remove(0);
		size--;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T peek() {
		return list.get(0);
	}
	
	public String toString() {
		return list.toString();
	}
}
