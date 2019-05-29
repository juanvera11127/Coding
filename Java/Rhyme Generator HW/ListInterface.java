public interface ListInterface<T> {

	public boolean isEmpty();

	public int size();

	public void add(int index, T value)
		throws ListIndexOutOfBoundsException;

	public void remove(int index)
		throws ListIndexOutOfBoundsException;

	public void removeAll();

	public T get(int index)
		throws ListIndexOutOfBoundsException;

	public int find(T o);
}
