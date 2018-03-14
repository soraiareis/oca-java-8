package _0_introduction.assessment._2_;

public class Sample {

  public static void main(String[] args) {
    String s1 = "Java";
    String s2 = "Java";
    StringBuilder sb1 = new StringBuilder();
    sb1.append("Ja").append("va");
    System.out.println(s1 == s2); // true - they refer to the same object so they are equal
    System.out.println(s1.equals(s2)); // true
    System.out.println(sb1.toString() == s1); // false - toString() is not from string pool
    System.out.println(sb1.toString().equals(s1)); // true
  }
}
