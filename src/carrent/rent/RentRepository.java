package carrent.rent;

import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

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
}
