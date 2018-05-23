public class Sample6 {
  static Animal a = new Bird();
  public static void main(String[] args) {
    System.out.println(a instanceof Fly);
  }

}

class Animal{}

interface Fly{}

class Bird extends Animal implements Fly {}