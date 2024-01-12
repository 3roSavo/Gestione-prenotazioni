package savogineros.Gestioneprenotazioni.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> listaPrenotazioni = new ArrayList<>();


    // 2° COSTRUTTORE PER NON INCLUDERE L'ID


    public Utente(String userName, String firstName, String lastName, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
