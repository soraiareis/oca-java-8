public class Whiz {
  static Double i = 0.0;
  public static void main(String[] args){
    // Compile
//    int j = 0;
//    int z = 0;
//    z += i;
//    System.out.println(z);

    // Compile
//    Double j = 0.25;
//    Double z = j + i;
//    System.out.println(z);

    Integer j = 0;
    Integer z = j + i.intValue();
    System.out.println(z);

    new Character('a');
    Character.valueOf('a');
  }
}
