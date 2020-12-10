package tracker.labor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LaborInformationsSystem {

  private List<TestErgebnis> ergebnisse = new ArrayList<>();

  public synchronized void add(TestErgebnis ergebnis) {
    ergebnisse.add(ergebnis);
  }

  public synchronized List<TestErgebnis> fetchPositiveErgebnisse() {
    ArrayList<TestErgebnis> result = new ArrayList<>();
    result.addAll(ergebnisse);
    ergebnisse.clear();
    return result;
  }
}
