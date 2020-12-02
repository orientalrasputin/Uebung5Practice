package tracker.mail;

public class Mail {
  private final String adresse;
  private String text;

  public Mail(String adresse) {
    this.adresse = adresse;
  }

  public String getText() {
    return text;
  }


  public String getAdresse() {
    return adresse;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void attachFile(String attachment) {
    this.text = attachment;
  }
}
