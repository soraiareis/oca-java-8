package _0_introduction.assessment._6_;

public class MathFunctions {

  public static void addToInt(int x, int amountToAdd) {
    x = x + amountToAdd;
  }

  public static void main(String[] args) {
    int a = 15;
    int b = 10;
    MathFunctions.addToInt  (a, b); // Esse método não tem retorno nenhum
    System.out.println(a); // Imprime 15
  }
}
