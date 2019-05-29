
public interface QueueInterface<T> {

  public void enqueue(T data);

  public T dequeue();

  public int size();
  public T peek();

}
