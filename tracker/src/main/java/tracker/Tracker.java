package tracker;

import java.time.LocalDate;
import static java.time.Period.between;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tracker.betriebsaerztlicherdienst.BetriebsaerztlicherDienst;
import tracker.gesundheitsamt.Gesundheitsamt;
import tracker.labor.TestErgebnis;
import tracker.mail.Mail;
import tracker.mail.MailSender;
import tracker.persistenz.Storage;
import tracker.personen.Index;
import tracker.personen.KontaktPerson;

@EnableScheduling
@Component
public class Tracker {

  void trackingStarten(Index index, String arztMail) {

    // Index abspeichern
    storage.addIndex(index);

    // Mail an die zuständige Person schicken
    Mail mail = new Mail(arztMail);
    mail.setText(index.informationen());
    MailSender mailSender = new MailSender();
    mailSender.send(mail);
  }

  public void kontaktperson(String indexName, String kontaktpersonName,
                            LocalDate expositionsZeitpunkt, boolean personal) {

    // Index aus dem Storage laden
    Index index = storage.getIndex(indexName);

    // Kontaktperson im Storage abspeichern
    KontaktPerson kontaktPerson = new KontaktPerson(kontaktpersonName,index,expositionsZeitpunkt, personal);
    storage.addKontakt(kontaktPerson);
  }

  public void trackingAbschliessen(String indexName) {

    // Index aus dem Storage laden
    Index index = storage.getIndex(indexName);

    // KontaktPersonen aus dem Storage laden
    List<KontaktPerson> kontaktPersonList = storage.getKontakte(index);

    // Meldung an das Gesundheitsamt schicken
    gesundheitsamt.positiverTest(index);
    boolean isBetween14 = false;

   for (KontaktPerson kp : kontaktPersonList){
      gesundheitsamt.kontakt(kp);
     System.out.println("Exp date: "+ kp.datum());
     System.out.println("Now"+LocalDate.now());
      /* if( between( kp.datum(), LocalDate.now()).getDays() <= 14 ) {
        isBetween14 = true;
      }
      */
    }

    //if (isBetween14)
       gesundheitsamt.abschicken();

    // Meldung an BÄD
    // (Fun Fact: der Betriebsärztliche Dienst am UKD wird wirklich so abgekürzt)

  }


  // Hier muss noch Code rein, der das LIS regelmäßig abfragt

  @Autowired
  private Storage storage;

  @Autowired
  private Gesundheitsamt gesundheitsamt;

  @Autowired
  private BetriebsaerztlicherDienst betriebsaerztlicherDienst;

  public void getPositiveTests(List<TestErgebnis> testErgebnisse){
    for (TestErgebnis testErgebnis: testErgebnisse) {
      trackingStarten(testErgebnis.index(), testErgebnis.arztMail());
    }
  }
}
