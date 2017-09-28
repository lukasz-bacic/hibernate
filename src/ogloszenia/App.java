package ogloszenia;

import ogloszenia.model.Cena;
import ogloszenia.model.Kolor;
import ogloszenia.model.Material;
import ogloszenia.model.Zabawka;
import ogloszenia.repository.ZabawkaRepository;
import ogloszeniar.hibernate.util.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Lukasz on 27.09.2017.
 */
public class App {

    public static void main(String[] args) throws Exception {
        HibernateUtil.openSession();

        Cena cenaLalki = new Cena(new BigDecimal(10), "PLN");
        Zabawka lalka = new Zabawka("Lalka",
                cenaLalki,
                Kolor.BIALY,
                5,
                false,
                LocalDate.now(),
                Material.DREWNO
                );

        ZabawkaRepository.save(lalka);


    }
}
