package tracker.gesundheitsamt;


import static java.time.Period.between;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tracker.excel.ExcelExporter;
import tracker.mail.Mail;
import tracker.mail.MailSender;
import tracker.personen.Index;
import tracker.personen.KontaktPerson;

@Component
public class Gesundheitsamt {

  private final MailSender mailsender;
  private final ExcelExporter exporter;

  @Value("${KontaktZeit}")
  private int kontaktZeit;

  private int kontaktzeile = 1;

  public Gesundheitsamt(MailSender mailsender, ExcelExporter exporter) {
    this.mailsender = mailsender;
    this.exporter = exporter;
  }

  public void positiverTest(Index index) {
    exporter.add(0, 0, index.informationen());
  }

  public void kontakt(KontaktPerson kontaktPerson) {
    int tage = between(kontaktPerson.datum(), LocalDate.now()).getDays();
    boolean expired = between(kontaktPerson.datum(), LocalDate.now()).getDays() > kontaktZeit;
    System.out.println(tage);
    if (!expired) {
      exporter.add(0, kontaktzeile++, kontaktPerson.informationen());
    }
  }

  public void abschicken() {
    // Mail erzeugen
    Mail mail = new Mail("gesund@heitsa.mt");
    // Excel Export hinzufuegen
    mail.attachFile(exporter.export());
    // Mail verschicken
    mailsender.send(mail);
  }
}
