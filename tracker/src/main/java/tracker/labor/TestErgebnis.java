package tracker.labor;

import tracker.personen.Index;

public class TestErgebnis {

  private final Index index;
  private final String mail;

  public TestErgebnis(Index index, String mail) {
    this.index = index;
    this.mail = mail;
  }

  public Index index() {
    return index;
  }

  public String arztMail() {
    return mail;
  }
}
