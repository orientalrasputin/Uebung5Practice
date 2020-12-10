package tracker.betriebsaerztlicherdienst;

import static java.time.Period.between;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tracker.excel.ExcelExporter;
import tracker.mail.Mail;
import tracker.mail.MailSender;
import tracker.personen.KontaktPerson;

@Component
public class BetriebsaerztlicherDienst {
  private final MailSender mailsender;
  private final ExcelExporter exporter;

  private int zeile = 0;

  public BetriebsaerztlicherDienst(MailSender mailsender, ExcelExporter exporter) {
    this.mailsender = mailsender;
    this.exporter = exporter;
  }

  public void quarantaeneMeldung(KontaktPerson kontaktPerson) {
    // Zwischenspeichern oder direkt in den ExcelExporter schreiben
    boolean expired = between(kontaktPerson.datum(), LocalDate.now()).getDays() > 14;
    if (kontaktPerson.isPersonal() && !expired) {
      exporter.add(0, zeile++, kontaktPerson.informationen());
    }
  }

  public void versenden() {
    // Mail erzeugen
    Mail mail = new Mail("bäd@u.kd");
    // Excel Export hinzufuegen
    mail.attachFile(exporter.export());
    // Mail verschicken
    mailsender.send(mail);
  }
}
