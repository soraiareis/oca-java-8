package _3_chapter;

public class Sample {

  public static void main(String[] args) {
    String string = "animals";
    System.out.println(string.indexOf('a')); // 0
    System.out.println(string.indexOf("al")); // 4
    System.out.println(string.indexOf('a', 4)); // 4
    System.out.println(string.indexOf("al", 5)); // -1

    System.out.println(string.substring(3)); // mals
    System.out.println(string.substring(3, 6)); // mal
    System.out.println(string.substring(3, 7)); // mals


    System.out.println("abc".equals("ABC")); // false
    System.out.println("ABC".equals("ABC")); // true
    System.out.println("abc".equalsIgnoreCase("ABC")); // false


    System.out.println("abcabc".replace('a', 'A')); // AbcAbc
    System.out.println("abcabc".replace("ab", "AB")); // ABcABc

    System.out.println("abc".trim()); // abc
    System.out.println("\t    a b c\n".trim()); // a b c

    StringBuilder a = new StringBuilder("abc");
    StringBuilder b = a.append("de");
    b = b.append("f").append("g");
    System.out.println("a=" + a);   // abcdefg
    System.out.println("b=" + b);   // abcdefg
  }
}
