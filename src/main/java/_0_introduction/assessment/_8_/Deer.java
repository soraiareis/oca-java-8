package _0_introduction.assessment._8_;

public class Deer {
  public Deer() {
    System.out.print("Deer");
  }

  public Deer(int age) {
    System.out.print("DeerAge");
  }

  private boolean hasHorns() {
    return false;
  }

  public static void main(String[] args) {
    Deer deer = new Reindeer(5);
    System.out.println("," + deer.hasHorns()); // Result = DeerReindeer,false
  }
}

class Reindeer extends Deer {
  public Reindeer(int age) { // Since it does not call Deer, it calls by defaults Deer()
    System.out.print("Reindeer");
  }

  public boolean hasHorns() {
    return true;
  }
}
