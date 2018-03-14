package _0_introduction.assessment._16_;

public interface Animal {
  //public default String getName(){return null;}
  public String getName();
}
interface Mammal {
  //public default String getName(){return null;}
  public String getName();
}
abstract class Otter implements Mammal, Animal {
  //public abstract String getName();
  //public String getName(){return null;}
}
