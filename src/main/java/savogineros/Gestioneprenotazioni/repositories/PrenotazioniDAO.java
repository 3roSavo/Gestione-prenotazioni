package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savogineros.Gestioneprenotazioni.entities.Prenotazione;
@Repository
public interface PrenotazioniDAO extends JpaRepository<Prenotazione, Long> {

}
