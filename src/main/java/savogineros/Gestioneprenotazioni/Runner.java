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
import java.util.*;

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

            // Inizializziamo il DATABASE!
            //randomUsers();
            //randomEdifici();
            //randomPostazioni();
            //randomPrenotazioni();

            try {
                // PROVE METODO findByIdAndUpdate()
                Utente utente = new Utente("Genoveffa4ever", "Genoveffa", "Caltarani", "gevo.star@gmail.com");
                //utentiService.findByIdAndUpdate(4,utente);

                Edificio edificio = new Edificio("Empire State Building", "via dei matti, 0", "Potenza");
                //edificiService.findByIdAndUpdate(8,edificio);

                //Postazione postazione = new Postazione("postazione carina situata vicino alla segretaria carina", WorkStationType.OPENSPACE,3, edificiService.findById(3));
                //postazioniService.findByIdAndUpdate(6,postazione);

                Prenotazione prenotazione = new Prenotazione(utentiService.findById(6), postazioniService.findById(4), LocalDate.of(2021, 7, 12));
                //prenotazioniService.findByIdAndUpdate(54,prenotazione);
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
            // Conteggio elementi per tabella/classe nel DB
            System.out.println(prenotazioniService.countPrenotazioni());
            System.out.println(utentiService.countUtenti());
            System.out.println(postazioniService.countPostazioni());
            System.out.println(edificiService.countEdifici());

            // DERIVED QUERIES
            System.out.println("---------------findByFirstNameIgnoreCase-------------------");
            utentiService.filterByFirstName("diamante").forEach(utente -> System.out.println(utente));

            System.out.println("----------------findByFirstNameIgnoreCaseAndLastNameIgnoreCase-------------------");
            utentiService.filterByFirstNameAndLastName("diamante", "porcospino").forEach(utente -> System.out.println(utente));

            System.out.println("-----------------findByFirstNameStartingWithIgnoreCase---------------------");
            utentiService.filterByFirstNameStartingWith("abb").forEach(utente -> System.out.println(utente));

            System.out.println("-----------------findByFirstNameInIgnoreCase---------------------");
            utentiService.filterByFirstNameList(List.of("diamante", "abella", "asdrubale")).forEach(utente -> System.out.println(utente));

            System.out.println("-----------------findByFirstNameNull---------------------");
            utentiService.filterByFirstNameNull().forEach(utente -> System.out.println(utente));

        System.out.println("-----------------findByMaxOccupancy---------------------");
        postazioniService.filterByMaxOccupancyGreaterThan(5).forEach(postazione -> System.out.println(postazione));

        System.out.println("-----------------findFirstByLastNameContainingIgnoreCase---------------------");
        Optional<Utente> utenteOptional = utentiService.findFirstByLastNameContaining("no");
        if (utenteOptional.isPresent()) {
            System.out.println(utenteOptional);
        } else {
            System.err.println("Non esiste nessun utente con quel cognome");
        }

        System.out.println("-----------------findByWorkStationTypeAndCityIgnoreCase---------------------");
        postazioniService.findByWorkStationAndCity(WorkStationType.PRIVATO,"Sesto Donatella").forEach(postazione -> System.out.println(postazione));
        // ATTENZIONE! Se la city si trova su un'altra componente separata da Postazione basta inserire il nome della classe
        // prima del nome dell'attributo e JPA Repository lo troverà! (Edificio-City)

        System.out.println("-----------------existsByUsername---------------------");
        System.out.println(utentiService.existsByUserName("valdo.ferrara"));

        System.out.println("-----------------JPQL = filterByMaxOccupancyGreaterThan3---------------------");
        postazioniService.filterByMaxOccupancyGreaterThan3().forEach(postazione -> System.out.println(postazione));
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
            utentiService.saveUtente(utente);
        }
    }
    public void randomEdifici() {
        for (int i = 0; i< 10; i++) {
            Faker faker = new Faker(Locale.ITALIAN);
            String randomName = faker.company().name();
            String randomAddress = faker.address().streetAddress();
            String randomCity = faker.address().city();
            Edificio edificio = new Edificio(randomName, randomAddress, randomCity);
            edificiService.saveEdificio(edificio);
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
            if (randomWorkstationType.equals(WorkStationType.PRIVATO)) { // come si gestisce l'eccezione? boooh
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
            postazioniService.savePostazione(postazione);
        }
    }

    public void randomPrenotazioni() {
        // Mi vengono in mente due modi:
        // o attraverso Collections.shuffle(), dove poi mi prendo costantemente un indice fisso
        // o attraverso un numero random passato come indice della collection
        // Per quanto riguarda la data mi son già fatto la funzione più sopra
        // Prendiamo la seconda strada
        Random rndm = new Random();
        List<Utente> listaUtenti = utentiService.findAllUtenti();
        List<Postazione> listaPostazioni = postazioniService.findAllPostazioni();
        for (int i = 0; i< 10; i++) {
            int indiceRandomListaUtenti = rndm.nextInt(0, listaUtenti.size());
            int indiceRandomListaPostazioni = rndm.nextInt(0, listaPostazioni.size());

            Prenotazione prenotazione = new Prenotazione(listaUtenti.get(indiceRandomListaUtenti), listaPostazioni.get(indiceRandomListaPostazioni), dataRandom());
            prenotazioniService.savePrenotazione(prenotazione);
        }
    }
}
