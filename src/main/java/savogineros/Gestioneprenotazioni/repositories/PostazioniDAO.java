package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import savogineros.Gestioneprenotazioni.entities.Postazione;

public interface PostazioniDAO extends JpaRepository<Postazione,Long> {

}
