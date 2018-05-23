public class Testing {

  static class Data {
    int value;
    Data (int value){
      this.value = value;
    }
  }

  static void changeDate(Data data){
    data.value = 2 * data.value;
  }

  public static void main(String[] args) {
    Data data = new Data(1);
    Testing.changeDate(data);
    System.out.println(data.value);

    boolean f = true;
    if (f = false)
      System.out.println(f);
    else if (f)
      System.out.println(f);
  }

}
