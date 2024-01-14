package savogineros.Gestioneprenotazioni.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Edificio {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;

    private String name;

    private String address;

    private String city;

    @OneToMany(mappedBy = "edificio")
    private List<Postazione> listaPostazioni = new ArrayList<>();

    // 2° COSTRUTTORE PER NON INCLUDERE L'ID
    public Edificio(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
