package carrent.account;

import carrent.rent.Option;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

/**
 * Created by Lukasz on 02.10.2017.
 */
public class UserRepository {

    public static Optional<User> findUserByEmailAndPassword(String email, String password){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT u FROM User u WHERE u.email = :email " +
                    " AND u.password = :password ";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("password", password);

            return Optional.ofNullable((User) query.getSingleResult());
        }catch (NoResultException nre){
            return  Optional.empty();
        }catch (Exception ex){
            ex.printStackTrace();
            return Optional.empty();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static boolean save(User user){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.save(user);
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
