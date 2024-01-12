package savogineros.Gestioneprenotazioni.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Postazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private WorkStationType workStationType;

    private int maxOccupancy;

    @OneToMany( fetch = FetchType.EAGER, mappedBy = "postazione")
    private List<Prenotazione> listaPrenotazioni;

    @ManyToOne
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;

    // 2° COSTRUTTORE PER NON INCLUDERE L'ID

    public Postazione(String description, WorkStationType workStationType, int maxOccupancy, Edificio edificio) {
        this.description = description;
        this.workStationType = workStationType;
        this.maxOccupancy = maxOccupancy;
        this.edificio = edificio;
    }
}
