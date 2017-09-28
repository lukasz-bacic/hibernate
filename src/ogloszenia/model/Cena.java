package ogloszenia.model;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Lukasz on 27.09.2017.
 */
@Embeddable
public class Cena implements Serializable{

    public Cena(){}

    public Cena(BigDecimal wartosc, String symbolWaluty) {
        this.wartosc = wartosc;
        this.symbolWaluty = symbolWaluty;
    }

    private BigDecimal wartosc;

    private String symbolWaluty;

    public BigDecimal getWartosc() {
        return wartosc;
    }

    public void setWartosc(BigDecimal wartosc) {
        this.wartosc = wartosc;
    }

    public String getSymbolWaluty() {
        return symbolWaluty;
    }

    public void setSymbolWaluty(String symbolWaluty) {
        this.symbolWaluty = symbolWaluty;
    }
}
