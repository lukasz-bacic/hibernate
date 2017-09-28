package ogloszenia.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Lukasz on 27.09.2017.
 */
@Entity
public class PozycjaZamowienia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ile")
    private int liczbaSztuk;
    @ManyToOne
    @JoinColumn
    private Zabawka zabawka;
    @ManyToOne
    @JoinColumn
    private Zamowienie zamowienie;

    public PozycjaZamowienia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLiczbaSztuk() {
        return liczbaSztuk;
    }

    public void setLiczbaSztuk(int liczbaSztuk) {
        this.liczbaSztuk = liczbaSztuk;
    }

    public Zabawka getZabawka() {
        return zabawka;
    }

    public void setZabawka(Zabawka zabawka) {
        this.zabawka = zabawka;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }
}
