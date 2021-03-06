package tracker.excel;

import org.springframework.stereotype.Component;

@Component
public class ExcelExporter {

  private final StringBuilder sb = new StringBuilder();

  public void add(int spalte, int zeile, String wert) {
    sb.append("Z" + zeile + "S" + spalte + ":" + wert + "\n");
  }

  public String export() {
    return sb.toString();
  }

}
