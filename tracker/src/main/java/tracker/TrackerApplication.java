package tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import tracker.labor.LaborInformationsSystem;
import tracker.labor.TestErgebnis;
import tracker.personen.Index;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class TrackerApplication {
   @Autowired
  Tracker tracker;
   @Autowired
  LaborInformationsSystem lis;

  public static void main(String[] args) throws InterruptedException {

    SpringApplication.run(TrackerApplication.class, args);

  }

  // Am Anfang sucht Spring nach einer Bean für das
  // (funktionale) ApplicationRunner Interface und ruft die run Methode auf.
  @Bean
  ApplicationRunner init(/* hier können Sie Objekte injizieren, die Sie im Lambda-Ausdruck nutzen können*/) {
    return args -> {

      // Phase 1: Einige Testergebnisse in das LIS eintragen
      Index timo = new Index("Timo", LocalDate.of(2020, 12,9));
      Index florian = new Index("Florian", LocalDate.of(2020,12,8));

      lis.add(new TestErgebnis(timo, "arzt1@artz.de"));
      lis.add(new TestErgebnis(florian, "arzt2@artz.de"));

      // Dem Tracker etwas Zeit geben, um die Ergebnisse abzuholen
      // Diese Zeit muss länger sein als das Zeit-Intervall in dem das LIS abgefragt wird
      Thread.sleep(10000);

      // Phase 2: Einige Kontaktpersonen für die positive Fälle hinzufügen

      tracker.kontaktperson(timo.name(), "Kirsten", LocalDate.of(2020,12,01), false);
      tracker.kontaktperson(timo.name(), "Julia", LocalDate.of(2020,11,25), true);

      tracker.trackingAbschliessen(timo.name());
      // Phase 2 abschliessen, Phase 3 sollte dann automatisch getriggert werden
    };
  }

  @Scheduled(fixedDelay = 5000)
  private void getTests(){
    tracker.getPositiveTests(lis.fetchPositiveErgebnisse());
  }

  /* Alle 5s wird diese Methode ausgeführt.
  // Benötigt die @EnableScheduled Annotation oben an der Klasse
  @Scheduled(fixedDelay = 5000)
  void tick() {
    System.out.println("Ping");
  }
  */
}
