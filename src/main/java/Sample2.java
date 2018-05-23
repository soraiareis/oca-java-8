import java.util.Arrays;

public class Sample2 {

  public static void main(String[] args) {
    int i = 2;
    char[] array = new char[i];
    array[0] = 65;
    array[1] = 66;
    System.out.println(array[1]);


    int a[] = {2, 5, 9, 5, 0, 3};
    Arrays.sort(a, 2, 6);
    System.out.println(a[2] + a[5]);
  }

}
