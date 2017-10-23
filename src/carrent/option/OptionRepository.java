package carrent.option;

import carrent.rent.Option;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lukasz on 19.10.2017.
 */
public class OptionRepository {

    public static List<Option> findAll(){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT o FROM Option o";
            Query query = session.createQuery(hql);
            return query.getResultList();
        }catch (Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static List<Option> findAllByIdList(List<Integer> idList){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT o FROM Option o WHERE o.id IN (:idList)";
            Query query = session.createQuery(hql);
            query.setParameter("idList", idList);
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

    public static boolean save(Option option){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            session.save(option);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static boolean delete(int optionId){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            Option o = session.find(Option.class, optionId);
            if(o != null) {
                session.delete(o);
            }
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            if(session != null && session.getTransaction().isActive() ){
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        }finally {
            if (session != null && session.isOpen()){
                session.close();
            }
        }

    }



}
