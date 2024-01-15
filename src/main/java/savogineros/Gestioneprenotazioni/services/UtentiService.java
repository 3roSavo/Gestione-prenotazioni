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

    public void saveUtente(Utente utente) {
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

    public void findByIdAndUpdate(long id, Utente utente) {
        Utente utenteTrovato = findById(id);
        String firstNameUtenteTrovato = utenteTrovato.getFirstName();
        utenteTrovato.setUserName(utente.getUserName());
        utenteTrovato.setFirstName(utente.getFirstName());
        utenteTrovato.setLastName(utente.getLastName());
        utenteTrovato.setEmail(utente.getEmail());
        saveUtente(utenteTrovato);
        System.out.println("Utente con id " + id + " di nome " + firstNameUtenteTrovato + " modificato correttamente con l'utente " + utente.getFirstName());
        // CONSIDERAZIONI METODO
        // Abbiamo quindi notato che la save() eseguita su un utente con id già presente nel database semplicemente aggiorna i dati per quell'utente
    }

    public List<Utente> findAllUtenti() {
        return utentiDAO.findAll();
    }

    public long countUtenti() {
        return utentiDAO.count();
    }

    // DERIVED QUERIES
    // se torno una lista, essa può anche essere vuota, quindi non devo gestire exceptions
    // mentre una findById() torna un optional dove il valore è presente o non è presente,
    // quindi mi costringe a gestire la situazione con if/else o try/catch

    public Optional<Utente> findFirstByLastNameContaining(String partialLastName) {
        return utentiDAO.findFirstByLastNameContainingIgnoreCase(partialLastName);
    }

    public List<Utente> filterByFirstName(String firstName) {
        return utentiDAO.findByFirstNameIgnoreCase(firstName);
    }
    public List<Utente> filterByFirstNameAndLastName(String firstName, String lastName) {
        return utentiDAO.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
    }
    public List<Utente> filterByFirstNameStartingWith(String partialFirstName) {
        return utentiDAO.findByFirstNameStartingWithIgnoreCase(partialFirstName);
    }
    public List<Utente> filterByFirstNameList(List<String> firstNameList) {
        return utentiDAO.findByFirstNameInIgnoreCase(firstNameList);
    }
    public List<Utente> filterByFirstNameNull() {
        return utentiDAO.findByFirstNameNull();
    }
    public boolean existsByUserName(String userName) {
        return utentiDAO.existsByUserName(userName);
    }

}
