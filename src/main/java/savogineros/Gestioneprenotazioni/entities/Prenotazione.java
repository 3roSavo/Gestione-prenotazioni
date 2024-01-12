package savogineros.Gestioneprenotazioni.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // ogni prenotazione DEVE avere uno user collegato
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;
    private LocalDate date;

    // 2° COSTRUTTORE PER NON INCLUDERE L'ID

    public Prenotazione(Utente utente, Postazione postazione, LocalDate date) {
        this.utente = utente;
        this.postazione = postazione;
        this.date = date;
    }
}
