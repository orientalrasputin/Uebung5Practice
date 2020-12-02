package tracker;

import java.time.LocalDate;
import tracker.personen.Index;

public class Tracker {

  void trackingStarten(Index index, String arztMail) {

    throw new UnsupportedOperationException("not yet implemented");

    // Index abspeichern

    // Mail an die zuständige Person schicken
  }

  public void kontaktperson(String indexName, String kontaktpersonName,
                            LocalDate expositionsZeitpunkt, boolean personal) {

    throw new UnsupportedOperationException("not yet implemented");

    // Index aus dem Storage laden

    // Kontaktperson im Storage abspeichern

  }

  public void trackingAbschliessen(String indexName) {

    throw new UnsupportedOperationException("not yet implemented");

    // Index aus dem Storage laden

    // KontaktPersonen aus dem Storage laden

    // Meldung an das Gesundheitsamt schicken

    // Meldung an BÄD
    // (Fun Fact: der Betriebsärztliche Dienst am UKD wird wirklich so abgekürzt)

  }


  // Hier muss noch Code rein, der das LIS regelmäßig abfragt

}
