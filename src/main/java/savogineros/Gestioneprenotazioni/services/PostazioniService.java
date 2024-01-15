package savogineros.Gestioneprenotazioni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savogineros.Gestioneprenotazioni.entities.Postazione;
import savogineros.Gestioneprenotazioni.entities.WorkStationType;
import savogineros.Gestioneprenotazioni.repositories.PostazioniDAO;

import java.util.List;
import java.util.Optional;

@Service
public class PostazioniService {
    @Autowired
    private PostazioniDAO postazioniDAO;

    public void savePostazione(Postazione postazione) {
        postazioniDAO.save(postazione);
        System.out.println("Postazione salvata correttamente!");
    }

    public Postazione findById(long id) {
        Optional<Postazione> postazioneOptional = postazioniDAO.findById(id);
        if (postazioneOptional.isPresent()) {
            return postazioneOptional.get();
        } else {
            throw new RuntimeException("Postazione con id " + id + " non presente");
        }
    }

    public void findByIdAndDelete(long id) {
        Postazione postazione = this.findById(id);
        postazioniDAO.delete(postazione);
        System.out.println("Postazione eliminata correttamente!");
    }

    public void findByIdAndUpdate(long id, Postazione postazione) {
        Postazione postazioneTrovata = findById(id);
        String descrizionePostazioneTrovata = postazioneTrovata.getDescription();
        postazioneTrovata.setDescription(postazione.getDescription());
        postazioneTrovata.setWorkStationType(postazione.getWorkStationType());
        postazioneTrovata.setMaxOccupancy(postazione.getMaxOccupancy());
        postazioneTrovata.setEdificio(postazione.getEdificio());
        savePostazione(postazioneTrovata);
        System.out.println("Postazione con id " + id + " con descrizione: " + descrizionePostazioneTrovata + ", modificata correttamente con la postazione con la descrizione " + postazioneTrovata.getDescription());
        // CONSIDERAZIONI METODO
        // Abbiamo quindi notato che la save() eseguita su una postazione con id già presente nel database semplicemente aggiorna i dati per quella postazione
    }

    public List<Postazione> findAllPostazioni() {
        return postazioniDAO.findAll();
    }

    public long countPostazioni() {
        return postazioniDAO.count();
    }

    // DERIVED QUERIES

    public List<Postazione> filterByMaxOccupancyGreaterThan(int occupancy) {
        return postazioniDAO.findByMaxOccupancyGreaterThan(occupancy);
    }

    // Richiesta esercizio: Un utente può ricercare le postazioni indicando il tipo di postazione desiderato
    // e la città di interesse
    public List<Postazione> findByWorkStationAndCity(WorkStationType workStationType, String city) {
        return postazioniDAO.findByWorkStationTypeAndEdificioCityIgnoreCase(workStationType, city);
    }
    // ATTENZIONE! Se la city si trova su un'altra componente separata da Postazione basta inserire il nome della classe
    // prima del nome dell'attributo e JPA Repository lo troverà! (Edificio-City)




}
