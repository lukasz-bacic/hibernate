package ogloszenia.repository;

import ogloszenia.model.Zabawka;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

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
}
