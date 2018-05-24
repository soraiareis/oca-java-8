class Base {
  public static void display() {
    System.out.println("Base");
  }
}

class Subclass extends Base {
  public static void display() {
    System.out.println("Subclass");
  }
}

public class Sample12 {
  public static void main(String[] args) {
    Base bs = new Subclass();
    bs.display();
  }
}
