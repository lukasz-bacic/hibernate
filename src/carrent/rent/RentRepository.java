package carrent.rent;

import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lukasz on 03.10.2017.
 */
public class RentRepository {

    public static boolean save(Rent rent){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            session.save(rent);
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

    public static boolean remove(Rent rent){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.remove(rent);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            if (session != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            return false;
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static List<Rent> findAll(){
        Session session= null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT r FROM Rent r ORDER BY r.startDate DESC ";
            Query query = session.createQuery(hql);
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


    public static List<Rent> findByUserId(int userId) {
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT r FROM Rent r WHERE r.customer.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", userId);
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
