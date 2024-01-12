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
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniDAO prenotazioniDAO;

    public void save(Prenotazione prenotazione) {

        LocalDate dataPrenotazione = prenotazione.getDate();
        List<LocalDate> listaDatePrenotazione = prenotazione.getPostazione().getListaPrenotazioni().stream().map(prenotazione1 -> prenotazione1.getDate()).toList();
        if (listaDatePrenotazione.stream().anyMatch(data -> data.equals(dataPrenotazione))) {
            throw new RuntimeException("Giorno già prenotato... riprova");
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
            throw new RuntimeException("Utente con id " + id + " non presente");
        }
    }

    public void findByIdAndDelete(long id) {
        Prenotazione prenotazione = this.findById(id);
        prenotazioniDAO.delete(prenotazione);
        System.out.println("Prenotazione eliminata correttamente!");
    }


}
