package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import savogineros.Gestioneprenotazioni.entities.Postazione;
import savogineros.Gestioneprenotazioni.entities.WorkStationType;

import java.util.List;

@Repository
public interface PostazioniDAO extends JpaRepository<Postazione,Long> {

    // FUNZIONALITA CUSTOM CON DERIVED QUERIES

    List<Postazione> findByMaxOccupancyGreaterThan(int occupancy);
    // qui prendiamo tutte le postazioni che hanno la capienza massima oltre un valore che passiamo come parametro
    // in JPQL sarebbe
    //@Query("SELECT p FROM Postazione p WHERE p.maxOccupancy > :occupancy")
    //List<Postazione> findByMaxOccupancyGreaterThan(@Param("occupancy") int occupancy);

    // RICHIESTA ESERCIZIO: Un utente può ricercare le postazioni indicando il tipo di postazione desiderato
    // e la città di interesse
    List<Postazione> findByWorkStationTypeAndEdificioCityIgnoreCase(WorkStationType workStationType, String city);
    // ATTENZIONE! Se la city si trova su un'altra componente separata da Postazione basta inserire il nome della classe
    // prima del nome dell'attributo e JPA Repository lo troverà! (Edificio-City)
    // In JPQL sarebbe
    //@Query("SELECT p FROM Postazione p JOIN p.edificio e WHERE p.workStationType = :workStationType AND LOWER(e.city) = LOWER(:city)")
    //List<Postazione> trovaPerTipoPostazioneEEdificioCittàIgnoreCase(@Param("workStationType") WorkStationType workStationType, @Param("city") String city);

    @Query("SELECT p FROM Postazione p WHERE p.maxOccupancy > 3")
    List<Postazione> filterByMaxOccupancyGreaterThan3();
    // Ecco un esempio in JPQL che filtra tutte le postazioni e torna una lista con quelle che hanno
    // maxOccupancy > 3

}
