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

    public void savePrenotazione(Prenotazione prenotazione) {

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

    public void findByIdAndUpdate(long id, Prenotazione prenotazione) {
        Prenotazione prenotazioneTrovata = findById(id);
        LocalDate dataPrenotazioneTrovata = prenotazioneTrovata.getDate();

        prenotazioneTrovata.setUtente(prenotazione.getUtente());
        prenotazioneTrovata.setPostazione(prenotazione.getPostazione());
        prenotazioneTrovata.setDate(prenotazione.getDate());

        savePrenotazione(prenotazioneTrovata);
        System.out.println("Prenotazione con id " + id + " per la data " + dataPrenotazioneTrovata + " modificata correttamente con la data " + prenotazioneTrovata.getDate());
        // CONSIDERAZIONI METODO
        // Abbiamo quindi notato che la save() eseguita su una prenotazione con id già presente nel database semplicemente aggiorna i dati per quella prenotazione
    }

    public void findByIdAndDelete(long id) {
        Prenotazione prenotazione = this.findById(id);
        prenotazioniDAO.delete(prenotazione);
        System.out.println("Prenotazione eliminata correttamente!");
    }

    public long countPrenotazioni() {
        return prenotazioniDAO.count();
    }


}
