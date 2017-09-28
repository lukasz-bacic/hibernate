package ogloszenia;

import ogloszenia.model.Cena;
import ogloszenia.model.Kolor;
import ogloszenia.model.Material;
import ogloszenia.model.Zabawka;
import ogloszenia.repository.ZabawkaRepository;
import ogloszenia.repository.ZamowienieRepository;
import ogloszeniar.hibernate.util.HibernateUtil;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static java.io.File.createTempFile;

/**
 * Created by Lukasz on 27.09.2017.
 */
public class App {

    public static void main(String[] args) throws Exception {
        HibernateUtil.openSession();

        Path path = Paths.get("C://lalka.jpg");
        byte[] image = Files.readAllBytes(path);

        Cena cenaLalki = new Cena(new BigDecimal(10), "PLN");
        Zabawka lalka = new Zabawka("Lalka",
                cenaLalki,
                Kolor.BIALY,
                5,
                false,
                LocalDate.now(),
                Material.DREWNO,
                image
                );

        ZabawkaRepository.save(lalka);

        List<Zabawka> zabawkiWitgPriceLessThan11 =
        ZabawkaRepository.findAllWithPriceLessThanParameter(new BigDecimal(11));

        zabawkiWitgPriceLessThan11.forEach( x -> System.out.println(x.getNazwa()));

        List<Zabawka> zabawkiWithPriceLessThan5 =
                ZabawkaRepository.findAllWithPriceLessThanParameter(new BigDecimal(5));

        System.out.println("Zabawki za mniej niz 5 !");
        zabawkiWithPriceLessThan5.forEach( x -> System.out.println(x.getNazwa()));


        Long count = ZabawkaRepository.countAll();
        System.out.println("Liczba zabawek: " +count);

        HashMap<Zabawka, Integer> listaZakupow = new HashMap<>();
        listaZakupow.put(zabawkiWitgPriceLessThan11.stream().findAny().orElse(lalka),
                new Integer(5));

        // TWORZENIE ZAMOWIENIA
        ZamowienieRepository.createOrder(listaZakupow, "test@gmail.com");







    }
}
