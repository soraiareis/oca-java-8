public class Employer {
  Employer(){
    System.out.println("CP");
  }
  static {System.out.println("SP");}
  Employer(String s, int i){
    name = s;
    age = i;
  }
  String name;
  int age;
}
