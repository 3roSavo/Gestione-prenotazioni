package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savogineros.Gestioneprenotazioni.entities.Postazione;
@Repository
public interface PostazioniDAO extends JpaRepository<Postazione,Long> {

}
