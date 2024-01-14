package savogineros.Gestioneprenotazioni.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Utente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "utente")
    private List<Prenotazione> listaPrenotazioni;


    // 2° COSTRUTTORE PER NON INCLUDERE L'ID


    public Utente(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
