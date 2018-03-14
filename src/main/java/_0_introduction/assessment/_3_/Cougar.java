package _0_introduction.assessment._3_;

interface HasTail {
  int getTailLength();
}

abstract class Puma implements HasTail {
  // protected int getTailLength() { // Cannot have a more restrictive access (protected) than the interface (public)
  public int getTailLength() {
      return 4;
  }
}

public class Cougar extends Puma {

  public static void main(String[] args) {
    //Puma puma = new Puma(); // Puma is abstract and cannot be instantiated
    //System.out.println(puma.getTailLength());
  }

  public int getTailLength(int length) {
    return 2;
  }
}
