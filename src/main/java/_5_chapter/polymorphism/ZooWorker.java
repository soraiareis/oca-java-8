package _5_chapter.polymorphism;

public class ZooWorker {
  public static void feed(Reptile reptile) {
    System.out.println("Feeding: " + reptile.getName());
  }
  public static void main(String[] args) {
    feed(new Alligator());        // Feeding: Alligator
    feed(new Crocodile());        // Feeding: Crocodile
    feed(new Reptile());          // Feeding: Reptile
  }
}
