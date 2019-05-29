public interface HeapInterface<T extends Comparable <T> >{

  public void push(T entry);
  public T pop();
  public int size();

}
