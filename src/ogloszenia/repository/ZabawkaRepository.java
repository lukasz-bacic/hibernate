package ogloszenia.repository;

import ogloszenia.model.Zabawka;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lukasz on 27.09.2017.
 */
public class ZabawkaRepository {

    public static int save(Zabawka zabawka){
        try {
            Session session = HibernateUtil.openSession();
            session.save(zabawka);
            return zabawka.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static List<Zabawka> findAllWithPriceLessThanParameter(BigDecimal price){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT z FROM Zabawka z WHERE z.cena.wartosc < :price";
            Query query = session.createQuery(hql);
            query.setParameter("price", price);

            List<Zabawka> result = query.getResultList();
            return result;
        }catch (Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }finally {
            if(session.isOpen()){
                session.close();
            }
        }

    }

    public static Long countAll(){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT COUNT(z) FROM Zabawka z ";
            Query query = session.createQuery(hql);
            Long count = (Long) query.getSingleResult();
            return count;
        }catch(Exception ex){
            ex.printStackTrace();
            return new Long(-1);
        }finally {
            if(session.isOpen()){
                session.close();
            }
        }

    }
}
