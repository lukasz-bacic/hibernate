package ogloszenia.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Lukasz on 27.09.2017.
 */
@Entity
@Table(name = "zabawki")
public class Zabawka implements Serializable{

    public Zabawka(){

    }

    public Zabawka(String nazwa, Cena cena, Kolor kolor, int stanMagazynu, boolean czyPromocja, LocalDate dataProdukcji, Material material) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.kolor = kolor;
        this.stanMagazynu = stanMagazynu;
        this.czyPromocja = czyPromocja;
        this.dataProdukcji = dataProdukcji;
        this.material = material;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nazwa;
    @Embedded
    private Cena cena;
    @Enumerated
    private Kolor kolor;
    private int stanMagazynu;
    private boolean czyPromocja;
    private LocalDate dataProdukcji;
    @Enumerated
    private Material material;

    @OneToOne
    private Atest atest;
    @Transient
    private Cena cenaProdukcji;

    @OneToMany(mappedBy = "zabawka")
    Set<PozycjaZamowienia> pozycjaZamowieniaSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Cena getCena() {
        return cena;
    }

    public void setCena(Cena cena) {
        this.cena = cena;
    }

    public Kolor getKolor() {
        return kolor;
    }

    public void setKolor(Kolor kolor) {
        this.kolor = kolor;
    }

    public int getStanMagazynu() {
        return stanMagazynu;
    }

    public void setStanMagazynu(int stanMagazynu) {
        this.stanMagazynu = stanMagazynu;
    }

    public boolean isCzyPromocja() {
        return czyPromocja;
    }

    public void setCzyPromocja(boolean czyPromocja) {
        this.czyPromocja = czyPromocja;
    }

    public LocalDate getDataProdukcji() {
        return dataProdukcji;
    }

    public void setDataProdukcji(LocalDate dataProdukcji) {
        this.dataProdukcji = dataProdukcji;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Cena getCenaProdukcji() {
        return cenaProdukcji;
    }

    public void setCenaProdukcji(Cena cenaProdukcji) {
        this.cenaProdukcji = cenaProdukcji;
    }

    public Set<PozycjaZamowienia> getPozycjaZamowieniaSet() {
        return pozycjaZamowieniaSet;
    }

    public void setPozycjaZamowieniaSet(Set<PozycjaZamowienia> pozycjaZamowieniaSet) {
        this.pozycjaZamowieniaSet = pozycjaZamowieniaSet;
    }

    public Atest getAtest() {
        return atest;
    }

    public void setAtest(Atest atest) {
        this.atest = atest;
    }
}
