package tracker.personen;

import static java.time.format.DateTimeFormatter.ofPattern;


import java.time.LocalDate;

public class Index {

  private final String name;
  private final LocalDate testdatum;

  public Index(String name, LocalDate testdatum) {
    this.name = name;
    this.testdatum = testdatum;
  }

  public String informationen() {
    return name + " (Befund am: " + testdatum.format(ofPattern("dd.MMMM yyyy")) + ")";
  }

  public LocalDate datum() {
    return testdatum;
  }

  public String name() {
    return name;
  }
}
