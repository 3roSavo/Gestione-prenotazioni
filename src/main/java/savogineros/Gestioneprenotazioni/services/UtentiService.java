package savogineros.Gestioneprenotazioni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savogineros.Gestioneprenotazioni.entities.Edificio;
import savogineros.Gestioneprenotazioni.entities.Utente;
import savogineros.Gestioneprenotazioni.repositories.UtentiDAO;

import java.util.List;
import java.util.Optional;

@Service
public class UtentiService {
    @Autowired
    private UtentiDAO utentiDAO;

    public void save(Utente utente) {
        utentiDAO.save(utente);
        System.out.println("Utente salvato correttamente!");
    }

    public Utente findById(long id) {
        Optional<Utente> utenteOptional = utentiDAO.findById(id);
        if (utenteOptional.isPresent()) {
            return utenteOptional.get();
        } else {
            throw new RuntimeException("Utente con id " + id + " non presente");
        }
    }

    public void findByIdAndDelete(long id) {
        Utente utente = this.findById(id);
        utentiDAO.delete(utente);
        System.out.println("Utente eliminato correttamente!");
    }

    public List<Utente> findAllUtenti() {
        return utentiDAO.findAll();
    }

}
