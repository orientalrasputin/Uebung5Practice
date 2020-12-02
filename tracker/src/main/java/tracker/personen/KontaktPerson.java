package tracker.personen;

import static java.time.format.DateTimeFormatter.ofPattern;


import java.time.LocalDate;

public class KontaktPerson {

  private final String name;
  private final Index index;
  private final LocalDate kontaktdatum;
  private final boolean personal;

  public KontaktPerson(String name, Index index, LocalDate kontaktdatum, boolean personal) {
    this.name = name;
    this.index = index;
    this.kontaktdatum = kontaktdatum;
    this.personal = personal;
  }

  public String informationen() {
    return name + " (" +
        (personal ? "beschäftigt am UKD, " : "") +
        "Kontakt mit "+index.name()+" am: " +
        kontaktdatum.format(ofPattern("dd.MMMM yyyy")) + ")";
  }

  public LocalDate datum() {
    return kontaktdatum;
  }

  public boolean isPersonal() {
    return personal;
  }

  public Index index() {
    return this.index;
  }

  public String name() {
    return name;
  }
}
