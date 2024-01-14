package savogineros.Gestioneprenotazioni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import savogineros.Gestioneprenotazioni.entities.Edificio;
import savogineros.Gestioneprenotazioni.entities.Utente;
import savogineros.Gestioneprenotazioni.repositories.EdificiDAO;

import java.util.List;
import java.util.Optional;

@Service
public class EdificiService {
    @Autowired
    EdificiDAO edificiDAO;

    public void save(Edificio edificio) {
        edificiDAO.save(edificio);
        System.out.println("Edificio salvato correttamente!");
    }

    public Edificio findById(long id) {
        Optional<Edificio> edificioOptional = edificiDAO.findById(id);
        if (edificioOptional.isPresent()) {
            return edificioOptional.get();
        } else {
            throw new RuntimeException("Edificio con id " + id + " non presente");
        }
    }

    public void findByIdAndDelete(long id) {
        Edificio edificio = this.findById(id);
        edificiDAO.delete(edificio);
        System.out.println("Edificio eliminato correttamente!");
    }

    public List<Edificio> findAllEdifici() {
        return edificiDAO.findAll();
    }


}
