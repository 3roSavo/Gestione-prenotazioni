package savogineros.Gestioneprenotazioni;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import savogineros.Gestioneprenotazioni.entities.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(Locale.ITALIAN);


        /*System.out.println("Prova dal runner");
        Utente utente1 = new Utente(faker.funnyName().name(),faker.harryPotter().character(),faker.name().lastName(),faker.internet().emailAddress());
        System.out.println(utente1);
        Edificio edificio1 = new Edificio("Palazzo Belvedere", faker.address().streetAddress(), faker.country().name());
        System.out.println(edificio1);
        Postazione postazione1 = new Postazione("gradevole postazione accanto alle finestre", WorkStationType.SALA_RIUNIONI,14, edificio1);
        edificio1.setListaPostazioni(List.of(postazione1));
        System.out.println(postazione1);
        Prenotazione prenotazione1 = new Prenotazione(utente1,postazione1, LocalDate.now());
        System.out.println(prenotazione1);
        System.out.println(utente1);*/

    }
}
