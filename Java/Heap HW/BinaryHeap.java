import java.util.*;

public class BinaryHeap<T extends Comparable <T> > implements HeapInterface<T>{

  public BinaryHeap(int initial_capacity, boolean max){

  }

  public void push(T entry){
  }

  protected void makeSafe(){

  }

  public T pop(){
  }

  public int size(){

  }
  public static void main(String[] args){
    BinaryHeap<String> heap = new BinaryHeap<String>(2,false);
    heap.push("a");
    heap.push("b");
    heap.push("c");
    heap.push("e");
    heap.push("d");
    while (heap.size() > 0){
      System.out.println(heap.pop());
    }
    //Should print a,b,c,d,e


    heap = new BinaryHeap<String>(2,true);
    heap.push("a");
    heap.push("b");
    heap.push("c");
    heap.push("e");
    heap.push("d");
    while (heap.size() > 0){
      System.out.println(heap.pop());
    }
    //should print e,d,c,b,a
  }

}
