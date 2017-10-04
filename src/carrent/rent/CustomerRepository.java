package carrent.rent;

import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

/**
 * Created by Lukasz on 04.10.2017.
 */
public class CustomerRepository {

    public static Optional<Customer> findByEmail(String email){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT c FROM Customer c WHERE c.id = (SELECT u.id FROM User u WHERE u.email = :email)";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            return Optional.ofNullable((Customer) query.getSingleResult());
        }catch (Exception ex){
            ex.printStackTrace();
            return Optional.empty();
        }     finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static boolean update(Customer customer){

        return false;
    }

}
