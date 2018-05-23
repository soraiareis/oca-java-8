public class Sample {

  public static void main(String[] args) {
    final int x;
    x = 0;
    final int y = 2;
    int i = 2;

    switch (i){
      //case x: System.out.println("A"); break;
      case y: {System.out.println("A");} break;
      case 1: System.out.println("A"); break;
    }
  }

}
