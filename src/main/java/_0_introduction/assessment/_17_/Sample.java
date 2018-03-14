package _0_introduction.assessment._17_;

import java.util.function.Predicate;

public class Sample {

  private static boolean test(Predicate<Integer> p){
    return p.test(5);
  }

  public static void main(String[] args) {
    System.out.println(test(i -> i == 5));
    //System.out.println(test(i -> {i == 5;}));
    System.out.println(test((i) -> i == 5));
    //System.out.println(test((int i) -> i == 5));
    System.out.println(test((Integer i) -> i == 5));
    //System.out.println(test((int i) -> {return i == 5;}));
    System.out.println(test((Integer i) -> {return i == 5;}));
    System.out.println(test((i) -> {return i == 5;}));
  }
}
