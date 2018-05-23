public class Sample7 {
  static Integer I;
  public static void main(String[] args) {
    //I.toString();
    int i = 053;
    System.out.println(i);
    int[][][] a = new int [2][3][4];
  }

}

class Concrete{}

interface InterfaceA{
  String teste = "";
  void swim();
  default void test(){}
}

interface InterfaceB{}

interface InterfaceC extends InterfaceA, InterfaceB {}

abstract class Abstract extends Concrete {
  abstract void swim();
  private void test(){}
}