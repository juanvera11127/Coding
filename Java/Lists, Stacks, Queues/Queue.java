
public class Queue<T> implements QueueInterface<T> {

	private int size = 0;
	private LinkedList<T> list;
	
	public Queue() {
		list = new LinkedList<T>();
		size = 0;
	}

	@Override
	public void enqueue(T data) {
		list.add(data, size);
		size++;
	}

	@Override
	public T dequeue() {
		if(size == 0) {
			throw new QueueException("Error: Queue is empty");
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
