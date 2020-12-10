package tracker.persistenz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import tracker.personen.Index;
import tracker.personen.KontaktPerson;

@Component
public class Storage {

  private final Map<String, Index> indexTabelle = new HashMap<>();
  private final Map<Index, List<KontaktPerson>> kontaktTabelle = new HashMap<>();

  public void addIndex(Index index) {
    indexTabelle.put(index.name(), index);
    System.out.println("Index eingefügt: " + index.informationen());
  }

  public void addKontakt(KontaktPerson kontaktPerson) {
    Index index = kontaktPerson.index();
    List<KontaktPerson> kontaktListe = kontaktTabelle.getOrDefault(index, new ArrayList<>());
    kontaktListe.add(kontaktPerson);
    kontaktTabelle.put(index, kontaktListe);
    System.out.println("Kontaktperson eingefügt: " + kontaktPerson.informationen());
  }

  public Index getIndex(String indexName) {
    return indexTabelle.get(indexName);
  }

  public List<KontaktPerson> getKontakte(Index index) {
    return kontaktTabelle.get(index);
  }


}
