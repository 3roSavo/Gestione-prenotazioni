package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savogineros.Gestioneprenotazioni.entities.Postazione;

import java.util.List;

@Repository
public interface PostazioniDAO extends JpaRepository<Postazione,Long> {

    // FUNZIONALITA CUSTOM CON DERIVED QUERIES

    List<Postazione> findByMaxOccupancyGreaterThan(int occupancy);
    // qui prendiamo tutte le postazioni che hanno la capienza massima oltre un valore che passiamo come parametro
    // in JPQL sarebbe
    //@Query("SELECT p FROM Postazione p WHERE p.maxOccupancy > :occupancy")
    //List<Postazione> findByMaxOccupancyGreaterThan(@Param("occupancy") int occupancy);


}
