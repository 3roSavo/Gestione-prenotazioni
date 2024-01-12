package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savogineros.Gestioneprenotazioni.entities.Edificio;
@Repository
public interface EdificiDAO extends JpaRepository<Edificio, Long> {

}
