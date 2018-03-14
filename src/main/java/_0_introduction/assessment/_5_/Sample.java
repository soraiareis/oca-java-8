package _0_introduction.assessment._5_;

public class Sample {

  public static void main(String[] args) {
    System.out.print("a");
    try {
      System.out.print("b");
      throw new IllegalArgumentException();
    } catch (RuntimeException e) { // The exception is captured here
      System.out.print("c");
    } finally {
      System.out.print("d");
    }
    System.out.print("e");
  }
}
