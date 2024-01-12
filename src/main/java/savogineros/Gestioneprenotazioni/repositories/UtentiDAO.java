package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savogineros.Gestioneprenotazioni.entities.Utente;
@Repository
public interface UtentiDAO extends JpaRepository<Utente, Long> {
}
