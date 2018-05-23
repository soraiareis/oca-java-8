import java.lang.reflect.Array;
import java.util.Arrays;

interface I {}

public class Sample10 {

  public static void updateAge(Employer ps, int a){
    ps.age = a;
  }

  public final static void main(String[] args) {
    Integer[] integers1 = {2, -1, 4, 5, 3};
    Integer[] integers2 = {2, -1, 4, 5, 3};

    System.out.print((integers1 == integers2) + "");
    System.out.print(Arrays.equals(integers1, integers2) + "");
    System.out.print(Arrays.deepEquals(integers1, integers2) + "");

    int[] ints1 = {2, -1, 4, 5, 3};
    int[] ints2 = {2, -1, 4, 5, 3};

    System.out.print((ints1 == ints2) + "");
    System.out.print(Arrays.equals(ints1, ints2) + "");
    //System.out.print(Arrays.deepEquals(ints1, ints2) + "");

    System.out.println();

    L1: for (int i = 5, j = 0; i > 0; i--){
      L2: for (; j < 5; j++){
        System.out.print(i + "" + j + " ");
        if(j == 0)
          continue L1;
      }
    }

    int marks = 60;
    if (marks >= 40)
      System.out.print(" ");
    else if (marks >= 60)
      System.out.print(" ");

    I i = new I() {};

    final int[] array /*comment*/  = {1, 2};
  }

}
