import java.util.Arrays;

public class Sample9 {

  public static void main(String[] args) {
    char[] chars = {'1', 'Z', '0', '-', '8', '1'};
    StringBuilder sb = new StringBuilder();
    sb.append(chars, 0, chars.length-1);

    sb.append("08");
    sb.setLength(4);
    sb.insert(5, "10");

    System.out.println();
  }

}
