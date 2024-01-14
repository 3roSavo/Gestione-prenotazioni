package savogineros.Gestioneprenotazioni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import savogineros.Gestioneprenotazioni.entities.Utente;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtentiDAO extends JpaRepository<Utente, Long> {

    // FUNZIONALITA CUSTOM CON DERIVED QUERIES
    // Qui ritornano tutte liste MA possono tornare anche valori singoli, in quel caso tornano OPTIONAL e dovrai gestirle
    // ESEMPIO
    Optional<Utente> findFirstByLastNameContainingIgnoreCase(String partialLastName);

    // qui cerco e metto in una lista tutti gli utenti con firstName uguale alla stringa che passo come parametro, ignorando il maiuscolo e minuscolo
    List<Utente> findByFirstNameIgnoreCase(String firstName); // equivalente = SELECT u FROM Utente u WHERE u.firstName= :firstName
    // qui usiamo findBy e IgnoreCase
    // in JPQL sarebbe
    //@Query("SELECT u FROM Utente u WHERE LOWER(u.firstName) = LOWER(:firstName)")
    //List<Utente> trovaPerNomeIgnoreCase(@Param("firstName") String firstName);


    List<Utente> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
    // qui proviamo anche l'operatore And. Attenzione che l'IgnoreCase deve essere messo doo entrambi gli attributi
    // in JPQL sarebbe
    //@Query("SELECT u FROM Utente u WHERE LOWER(u.firstName) = LOWER(:firstName) AND LOWER(u.lastName) = LOWER(:lastName)")
    //List<Utente> trovaPerNomeECognomeIgnoreCase(@Param("firstName") String firstName, @Param("lastName") String lastName);

    List<Utente> findByFirstNameStartingWithIgnoreCase(String partialFirstName);
    // qui proviamo StartingWith, che controlla se il firstName di un utente comincia con una lettera o serie di lettere
    // in JPQL sarebbe
    //@Query("SELECT u FROM Utente u WHERE LOWER(u.firstName) LIKE LOWER(:partialFirstName)%")
    //List<Utente> trovaPerNomeIniziaConIgnoreCase(@Param("partialFirstName") String partialFirstName);

    List<Utente> findByFirstNameInIgnoreCase(List<String> firstNameList);
    // qui proviamo In, in pratica cerca corrispondenze tra tutti i firstName degli user data una lista di stringhe che
    // gli passiamo tramite parametro!
    // in JPQL sarebbe
    //@Query("SELECT u FROM Utente u WHERE LOWER(u.firstName) IN (:firstNameList)")
    //List<Utente> trovaPerNomiInIgnoreCase(@Param("firstNameList") List<String> firstNameList);

    List<Utente> findByFirstNameNull();
    // qui proviamo Null









}
