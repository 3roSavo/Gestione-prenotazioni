package savogineros.Gestioneprenotazioni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savogineros.Gestioneprenotazioni.entities.Edificio;
import savogineros.Gestioneprenotazioni.entities.Postazione;
import savogineros.Gestioneprenotazioni.entities.Prenotazione;
import savogineros.Gestioneprenotazioni.entities.Utente;
import savogineros.Gestioneprenotazioni.repositories.PrenotazioniDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniDAO prenotazioniDAO;

    public void save(Prenotazione prenotazione) {

        Postazione numeroPostazione = prenotazione.getPostazione();

        LocalDate dataPrenotazione = prenotazione.getDate();
        List<LocalDate> listaDatePrenotazioniPostazione = prenotazione.getPostazione().getListaPrenotazioni().stream().map(prenotazione1 -> prenotazione1.getDate()).toList();
        List<LocalDate> listaDatePrenotazioniUtente = prenotazione.getUtente().getListaPrenotazioni().stream().map(prenotazione2 -> prenotazione2.getDate()).toList();

        if (listaDatePrenotazioniPostazione.stream().anyMatch(data -> data.equals(dataPrenotazione))) {
            // Controllo che verifica che non ci siano già prenotazioni della postazione per quel giorno dato (dataPrenotazione)
            throw new RuntimeException("Postazione già occupata per quel giorno! Riprova!");
        } else if (listaDatePrenotazioniUtente.stream().anyMatch(localDate -> localDate.equals(dataPrenotazione)) ){
            // Controllo che verifica che l'utente non abbia già prenotato un'altra postazione per quel giorno dato (dataPrenotazione)
            throw new RuntimeException("Hai già una prenotazione per quel giorno! Riprova!");
        } else {
            prenotazioniDAO.save(prenotazione);
            System.out.println("Prenotazione salvata correttamente");
        }
    }

    public Prenotazione findById(long id) {
        Optional<Prenotazione> prenotazioneOptional = prenotazioniDAO.findById(id);
        if (prenotazioneOptional.isPresent()) {
            return prenotazioneOptional.get();
        } else {
            throw new RuntimeException("Prenotazione con id " + id + " non presente");
        }
    }

    public void findByIdAndDelete(long id) {
        Prenotazione prenotazione = this.findById(id);
        prenotazioniDAO.delete(prenotazione);
        System.out.println("Prenotazione eliminata correttamente!");
    }


}
