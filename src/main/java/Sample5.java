import java.util.ArrayList;
import java.util.List;

public class Sample5 {

  public static void main(String[] args) {
    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder(10);
    StringBuilder sb3 = new StringBuilder("ABCD");
    StringBuilder sb4 = new StringBuilder("animal");
    System.out.println(sb1.capacity() + sb2.capacity() + sb3.capacity());
    System.out.println(sb4.capacity());

    List list = new ArrayList<>();
    list.add(2);
    list.add(5);
    list.add(7);
    list.add(9);
    System.out.println(Integer.max((int)list.get(0), 1));

    System.out.println(new String(new byte[]{88,89}));
    System.out.println(new String(new char[]{'a','b'}));
    System.out.println(new String(new StringBuilder("ABC")));

  }

}
