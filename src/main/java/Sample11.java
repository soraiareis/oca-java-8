import java.util.Date;

class Animal1 {
  public void eat() throws Exception {
    System.out.print("Animal eats");
  }
}
public class Sample11 extends Animal1 {

  public void eat() {System.out.print("Dog eats");}

  public static void main(String[] args) throws Exception{
    Animal1 a = new Sample11();
    Sample11 d = new Sample11();
    d.eat();
    a.eat();

    int i = 808;
    System.out.format("%d", i);
    System.out.format("%06d", i);

    System.out.println(10%-3);
  }

}

