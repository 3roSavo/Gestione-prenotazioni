package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import savogineros.Gestioneprenotazioni.entities.Utente;

public interface UtentiDAO extends JpaRepository<Utente, Long> {
}
