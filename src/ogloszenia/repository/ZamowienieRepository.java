package ogloszenia.repository;

import ogloszenia.model.PozycjaZamowienia;
import ogloszenia.model.Zabawka;
import ogloszenia.model.Zamowienie;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.*;

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


    public static List<Zamowienie> findAllZamowieniaByZabawka(Zabawka z){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT z FROM Zamowienie z " +
                    " LEFT JOIN z.pozycjaZamowieniaSet pz " +
                    " WHERE pz.zabawka.id = :id ";
            Query query = session.createQuery(hql, Zamowienie.class);
            query.setParameter("id", z.getId());
            return query.getResultList();
        }catch (Exception ex){
        ex.printStackTrace();
        return Collections.emptyList();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }


    }
}
