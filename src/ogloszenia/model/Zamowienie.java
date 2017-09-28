package ogloszenia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Lukasz on 27.09.2017.
 */
@Entity
public class Zamowienie implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long liczbaProduktow;
    private LocalDateTime dataZamowienia;
    private String email;

    @OneToMany(mappedBy = "zamowienie",cascade = CascadeType.ALL)
    Set<PozycjaZamowienia> pozycjaZamowieniaSet;

    public Zamowienie() {
    }

    public Zamowienie(String email, HashMap<Zabawka, Integer> pozycjeZamowienia) {
        this.email = email;
        this.dataZamowienia = LocalDateTime.now();
        this.liczbaProduktow =
                new Long(pozycjeZamowienia.values().stream().mapToInt(x -> x).sum());

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getLiczbaProduktow() {
        return liczbaProduktow;
    }

    public void setLiczbaProduktow(Long liczbaProduktow) {
        this.liczbaProduktow = liczbaProduktow;
    }

    public LocalDateTime getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(LocalDateTime dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<PozycjaZamowienia> getPozycjaZamowieniaSet() {
        return pozycjaZamowieniaSet;
    }

    public void setPozycjaZamowieniaSet(Set<PozycjaZamowienia> pozycjaZamowieniaSet) {
        this.pozycjaZamowieniaSet = pozycjaZamowieniaSet;
    }
}
