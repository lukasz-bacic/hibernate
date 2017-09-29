package ogloszenia.repository;

import ogloszenia.model.Zabawka;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Created by Lukasz on 27.09.2017.
 */
public class ZabawkaRepository {

    public static int save(Zabawka zabawka) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.save(zabawka);

            return zabawka.getId();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return 0;
    }


    public static List<Zabawka> findAllWithPriceLessThanParameter(BigDecimal price) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT z FROM Zabawka z WHERE z.cena.wartosc < :price";
            Query query = session.createQuery(hql);
            query.setParameter("price", price);

            List<Zabawka> result = query.getResultList();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }

    public static Long countAll() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT COUNT(z) FROM Zabawka z ";
            Query query = session.createQuery(hql);
            Long count = (Long) query.getSingleResult();
            return count;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Long(-1);
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }

    public static Optional<Zabawka> findZabawka(int id){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            Zabawka z = session.find(Zabawka.class, id);
            return Optional.ofNullable(z);
        }catch(Exception ex){
            ex.printStackTrace();
            return Optional.empty();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }

    }

    public static boolean delete(Zabawka zabawka){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            session.beginTransaction();
            session.delete(zabawka);
            session.getTransaction().commit();;
            return true;
        }catch (Exception ex){
            if(session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return false;
        }finally {
            if(session != null && session.isOpen())
                session.close();
        }
    }


    public static boolean update(Zabawka zabawka){
        Session session= null;
        try{
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.update(zabawka);
            session.getTransaction().commit();
            return true;

        }catch (Exception ex){
            if(session != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static List<Zabawka> findZabawkaWithStanAndName(){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT new Zabawka(nazwa, stanMagazynu)  FROM Zabawka z ";
            Query query = session.createQuery(hql);
            return  query.getResultList();
        }catch (Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }

    }

    public static boolean updatePrice(BigDecimal price){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            String sql = "UPDATE zabawki SET wartosc = :price WHERE id > 0";
            Query query = session.createNativeQuery(sql);
            query.setParameter("price", price);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            if(session != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }

    }






}
