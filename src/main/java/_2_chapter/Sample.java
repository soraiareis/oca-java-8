package _2_chapter;

public class Sample {

  public static void main(String[] args) {
    int x = 0;
    long y;
    for (y = 0, x = 4; x < 5 && y < 10; x++, y++){
      System.out.println(x + " ");
    }

    int[][] myComplexArray = {{5,2,1,3},{3,9,8,9},{5,7,12,7}};
    OUTER_LOOP: for (int[] mySimpleArray : myComplexArray) {
      INNER_LOOP: for (int i = 0; i < mySimpleArray.length; i++) {
        System.out.print(mySimpleArray[i] + "\t");
      }
      System.out.println();
    }
  }
}
