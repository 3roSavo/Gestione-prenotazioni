package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import savogineros.Gestioneprenotazioni.entities.Prenotazione;

public interface PrenotazioniDAO extends JpaRepository<Prenotazione, Long> {

}
