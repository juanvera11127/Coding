
public class TestLists {

  public static void main(String[] args){
    LinkedList<String> list = new LinkedList<String>();

    list.add("abc",0);
    list.add("def",0);
    list.add("xyz",1);
    list.add("hello",list.size());
    // list should be def, xyz, abc,hello
    System.out.println(list);
    list.remove(0);
    list.remove(list.size()-1);
    list.remove(1);
    // list should be xyz
    System.out.println(list);
    try {
      for (int ii = 0; ii < 10; ii++){
        list.remove(0);
      }
    }
    catch (ListException ex) {
			System.out.println("EXCEPTION: " + ex);
		}

    try {
      list.add("def",10);
    }
    catch (ListException ex) {
			System.out.println("EXCEPTION: " + ex);
		}
    try {
      list.get(10);
    }
    catch (ListException ex) {
			System.out.println("EXCEPTION: " + ex);
		}



  }

}
