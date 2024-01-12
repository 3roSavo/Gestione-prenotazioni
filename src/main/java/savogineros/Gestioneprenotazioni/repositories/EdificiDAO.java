package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import savogineros.Gestioneprenotazioni.entities.Edificio;

public interface EdificiDAO extends JpaRepository<Edificio, Long> {

}
