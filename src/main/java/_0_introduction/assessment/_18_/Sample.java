package _0_introduction.assessment._18_;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;

public class Sample {

  public static void main(String[] args) {
    System.out.println(LocalDate.of(2015, Calendar.APRIL, 1));
    System.out.println(LocalDate.of(2015, Month.APRIL, 1));
    System.out.println(LocalDate.of(2015, 3, 1));
    System.out.println(LocalDate.of(2015, 4, 1));
    // System.out.println(new LocalDate(2015, 3, 1));
    // System.out.println(new LocalDate(2015, 4, 1));
  }
}
