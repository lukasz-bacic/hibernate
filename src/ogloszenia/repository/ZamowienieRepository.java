package ogloszenia.repository;

import ogloszenia.model.PozycjaZamowienia;
import ogloszenia.model.Zabawka;
import ogloszenia.model.Zamowienie;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lukasz on 28.09.2017.
 */
public class ZamowienieRepository {


    public static boolean createOrder(HashMap<Zabawka, Integer> zabawki, String email){
        Session session= null;
        try {
            session = HibernateUtil.openSession();
            Zamowienie zamowienie = new Zamowienie(email, zabawki);

            Set<PozycjaZamowienia> pozycjaZamowieniaSet = new HashSet<>();

            zabawki.forEach((zabawka, liczbaSztuk) ->
                    pozycjaZamowieniaSet.add(new PozycjaZamowienia(liczbaSztuk, zabawka, zamowienie)));

            for(PozycjaZamowienia pz :pozycjaZamowieniaSet){
                session.save(pz);
            }


            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }



    }
}
