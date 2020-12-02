package tracker.mail;

public class MailSender {
  public void send(Mail mail) {
    System.out.println("Mail an " + mail.getAdresse() + " verschickt.");
    System.out.println("Inhalt: ");
    String text = mail.getText();
    System.out.println(text != null ? text : "");
    System.out.println("------------------------------");
  }
}
