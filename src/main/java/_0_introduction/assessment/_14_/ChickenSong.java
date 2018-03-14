package _0_introduction.assessment._14_;

import java.util.List;

class Chicken {}
interface HenHouse { public java.util.List<Chicken> getChickens();}
public class ChickenSong {
  public static void main(String[] args) {
    HenHouse house = new HenHouse() {
      @Override
      public List<Chicken> getChickens() {
        //return new ArrayList<>(); // Gives an IndexOutOfBoundsException
        return null; // Gives an NullPointerException
      }
    };
    Chicken chicken = house.getChickens().get(0);
    for (int i = 0; i < house.getChickens().size(); chicken = house.getChickens().get(i++)){
      System.out.print("Cluck");
    }
  }
}
