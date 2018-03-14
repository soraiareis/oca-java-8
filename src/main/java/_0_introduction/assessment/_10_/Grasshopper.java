package _0_introduction.assessment._10_;

public class Grasshopper {
  public Grasshopper(String n) {
    name = n;
  }

  public static void main(String[] args) {
    Grasshopper one = new Grasshopper("g1");
    Grasshopper two = new Grasshopper("g2");
    one = two; // two object will be eligible for Garbage Collection
    two = null; // two object continues eligible for Garbage Collection
    one = null; // one object will be eligible for Garbage Collection
  }

  private String name;
}
