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
import java.util.Collections;
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
        try {
            // Inizializziamo il DATABASE!
            //randomUsers();
            //randomEdifici();
            //randomPostazioni();
            //randomPrenotazioni();
            //Prenotazione prenotazione = new Prenotazione(utentiService.findById(1),postazioniService.findById(3),LocalDate.parse("2020-03-31"));
            //prenotazioniService.save(prenotazione);



        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }


    public LocalDate dataRandom() {
        // Creiamo una data random prestando attenzione che il giorno della data sia compatibile con il mese e anni bisestili

        Random rndm = new Random();
        int annoRandom = rndm.nextInt(2020,2025);
        int meseRandom = rndm.nextInt(1,13);
        int giornoRandom = rndm.nextInt(1, LocalDate.of(annoRandom,meseRandom,1).lengthOfMonth() + 1);
        return LocalDate.of(annoRandom,meseRandom,giornoRandom);
    }

    public void randomUsers() {
        Faker faker = new Faker(Locale.ITALIAN);
        for (int i = 0; i< 10; i++) {
            String randomUserName = faker.name().username();
            String randomFirstName = faker.name().firstName();
            String randomLastName = faker.name().lastName();
            String randomEmail = faker.internet().emailAddress();
            Utente utente = new Utente(randomUserName, randomFirstName, randomLastName, randomEmail);
            utentiService.save(utente);
        }
    }
    public void randomEdifici() {
        for (int i = 0; i< 10; i++) {
            Faker faker = new Faker(Locale.ITALIAN);
            String randomName = faker.company().name();
            String randomAddress = faker.address().streetAddress();
            String randomCity = faker.address().city();
            Edificio edificio = new Edificio(randomName, randomAddress, randomCity);
            edificiService.save(edificio);
        }
    }
    public void randomPostazioni() {
        for (int i = 0; i< 10; i++) {
            Faker faker = new Faker(Locale.ITALIAN);
            Random rndm = new Random();
            int randomNum = rndm.nextInt(1, 4);

            String randomDescription = faker.company().catchPhrase();

            WorkStationType randomWorkstationType = null;
            switch (randomNum) {
                case 1:
                    randomWorkstationType = WorkStationType.PRIVATO;
                    break;

                case 2:
                    randomWorkstationType = WorkStationType.OPENSPACE;
                    break;

                case 3:
                    randomWorkstationType = WorkStationType.SALA_RIUNIONI;
                    break;
            }

            int randomMaxOccupancy = 1;
            if (randomWorkstationType.equals(WorkStationType.PRIVATO)) {
                randomMaxOccupancy = rndm.nextInt(1, 3);
            } else if (randomWorkstationType.equals(WorkStationType.OPENSPACE)) {
                randomMaxOccupancy = rndm.nextInt(1, 5);
            } else if (randomWorkstationType.equals(WorkStationType.SALA_RIUNIONI)) {
                randomMaxOccupancy = rndm.nextInt(1, 16);
            }

            List<Edificio> listaEdifici = edificiService.findAllEdifici();
            Collections.shuffle(listaEdifici);
            Edificio randomEdificio = listaEdifici.get(0);

            Postazione postazione = new Postazione(randomDescription, randomWorkstationType, randomMaxOccupancy, randomEdificio);
            postazioniService.save(postazione);
        }
    }

    public void randomPrenotazioni() {
        // Mi vengono in mente due modi:
        // o attraverso Collections.shuffle(), dove poi mi prendo costantemente un indice fisso
        // o attraverso un numero random passato come indice della collection
        // Per quanto riguarda la data mi son già fatto la funzione più sopra
        // Prendiamo la seconda strada
        Random rndm = new Random();
        for (int i = 0; i< 10; i++) {
            List<Utente> listaUtenti = utentiService.findAllUtenti();
            List<Postazione> listaPostazioni = postazioniService.findAllPostazioni();
            int indiceRandomListaUtenti = rndm.nextInt(0, listaUtenti.size());
            int indiceRandomListaPostazioni = rndm.nextInt(0, listaPostazioni.size());

            Prenotazione prenotazione = new Prenotazione(listaUtenti.get(indiceRandomListaUtenti), listaPostazioni.get(indiceRandomListaPostazioni), dataRandom());
            prenotazioniService.save(prenotazione);
        }
    }
}
