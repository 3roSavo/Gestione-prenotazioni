package savogineros.Gestioneprenotazioni;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import savogineros.Gestioneprenotazioni.entities.*;
import savogineros.Gestioneprenotazioni.services.EdificiService;
import savogineros.Gestioneprenotazioni.services.PostazioniService;
import savogineros.Gestioneprenotazioni.services.PrenotazioniService;
import savogineros.Gestioneprenotazioni.services.UtentiService;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private EdificiService edificiService;
    @Autowired
    private PostazioniService postazioniService;
    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private UtentiService utentiService;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker(Locale.ITALIAN);
        Random rndm = new Random();

        for (int i =0; i < 5; i++) {

            //Edificio edificio1 = new Edificio(faker.company().name(), faker.address().streetAddress(), faker.country().name());
            //edificiService.save(edificio1);

            //Utente utenteLocale = new Utente(faker.funnyName().name(),faker.harryPotter().character(),faker.name().lastName(),faker.internet().emailAddress());
            //utentiService.save(utenteLocale);
        }

        Postazione postazione1 = new Postazione(faker.chuckNorris().fact(), WorkStationType.SALA_RIUNIONI,14, edificiService.findById(103));
        System.out.println(postazione1);
        postazioniService.save(postazione1);



        /*try {
            Utente utente1 = utentiService.findById(1);
            System.out.println(utente1);

            //Postazione postazione1 = postazioniService.findById(102);
            //System.out.println(postazione1);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }*/
        //Prenotazione prenotazione1 = new Prenotazione(utente1,postazione1,LocalDate.parse("2023-06-12"));
        //prenotazioniService.save(prenotazione1);




    }
}
