package tracker;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class TrackerApplication {

  Tracker tracker;


  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(TrackerApplication.class, args);
  }

  // Am Anfang sucht Spring nach einer Bean für das
  // (funktionale) ApplicationRunner Interface und ruft die run Methode auf.
  @Bean
  ApplicationRunner init(/* hier können Sie Objekte injizieren, die Sie im Lambda-Ausdruck nutzen können*/) {
    return args -> {

      // Phase 1: Einige Testergebnisse in das LIS eintragen

      // Dem Tracker etwas Zeit geben, um die Ergebnisse abzuholen
      // Diese Zeit muss länger sein als das Zeit-Intervall in dem das LIS abgefragt wird
      Thread.sleep(10000);

      // Phase 2: Einige Kontaktpersonen für die positive Fälle hinzufügen

      // Phase 2 abschliessen, Phase 3 sollte dann automatisch getriggert werden


    };
  }

  // Alle 5s wird diese Methode ausgeführt.
  // Benötigt die @EnableScheduled Annotation oben an der Klasse
  @Scheduled(fixedDelay = 5000)
  void tick() {
    System.out.println("Ping");
  }


}
