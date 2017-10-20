package ogloszenia;

import ogloszenia.model.*;
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
import java.util.Optional;
import java.util.stream.IntStream;

import static java.io.File.createTempFile;

/**
 * Created by Lukasz on 27.09.2017.
 */
public class App {

    public static void main(String[] args) throws Exception {
        HibernateUtil.openSession();

//        Path path = Paths.get("C://lalka.jpg");
//        byte[] image = Files.readAllBytes(path);
//
//        Cena cenaLalki = new Cena(new BigDecimal(10), "PLN");
//        Zabawka lalka = new Zabawka("Lalka",
//                cenaLalki,
//                Kolor.BIALY,
//                5,
//                false,
//                LocalDate.now(),
//                Material.DREWNO,
//                image
//                );
//        ZabawkaRepository.saveOrUpdate(lalka);

//
//        List<Zabawka> zabawkiWitgPriceLessThan11 =
//        ZabawkaRepository.findAllWithPriceLessThanParameter(new BigDecimal(11));
//
//        zabawkiWitgPriceLessThan11.forEach( x -> System.out.println(x.getNazwa()));
//
//        List<Zabawka> zabawkiWithPriceLessThan5 =
//                ZabawkaRepository.findAllWithPriceLessThanParameter(new BigDecimal(5));
//
//        System.out.println("Zabawki za mniej niz 5 !");
//        zabawkiWithPriceLessThan5.forEach( x -> System.out.println(x.getNazwa()));
//
//
//        Long count = ZabawkaRepository.countAll();
//        System.out.println("Liczba zabawek: " +count);
//
//        HashMap<Zabawka, Integer> listaZakupow = new HashMap<>();
//        listaZakupow.put(zabawkiWitgPriceLessThan11.stream().findAny().orElse(lalka),
//                new Integer(5));
//
//        // TWORZENIE ZAMOWIENIA
//        ZamowienieRepository.createOrder(listaZakupow, "test@gmail.com");
//
//        Optional<Zabawka> zabawka = ZabawkaRepository.findZabawka(14);
//
//        if(  zabawka.isPresent()){
//          zabawka.get().getId();
//        }
//
//        zabawka.ifPresent( x-> System.out.print("z IfPresent "+ x.getId()));
//
//        //usuwanie zabawki
//        if(  zabawka.isPresent()) {
//
//            Zabawka z = zabawka.get();
//            z.setStanMagazynu(60);
//            z.setNazwa("Auto");
//            ZabawkaRepository.update(z);
//
//            System.out.println(ZamowienieRepository.findAllZamowieniaByZabawka(z).size());
//
//
//        }
//
//        System.out.println(ZabawkaRepository.findZabawkaWithStanAndName().get(0).toString());
//
//
//       ZabawkaRepository.updatePrice( new BigDecimal("55.50"));
//
//        System.out.println(ZabawkaRepository.findZabawkaByNameLike("UT").size());

    }
}
