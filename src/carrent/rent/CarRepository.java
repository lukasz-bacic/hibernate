package carrent.rent;

import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Lukasz on 02.10.2017.
 */
public class CarRepository {

    public static Optional<Car> findCar(int id){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            return Optional.ofNullable(session.find(Car.class, id));
        }catch (Exception ex){
            ex.printStackTrace();
            return Optional.empty();
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static List<Car> findAvaibleCar(ZonedDateTime startDate,
                                           ZonedDateTime endDate){
        return findAvaibleCar(startDate, endDate, null);
    }

    public static List<Car> findAvaibleCar(ZonedDateTime startDate,
                                           ZonedDateTime endDate,
                                           Integer capacity){
        return findAvaibleCar(startDate, endDate, capacity, null);
    }

    public static List<Car> findAvaibleCar(ZonedDateTime startDate,
                                           ZonedDateTime endDate,
                                           Integer capacity,
                                           CarSegment carSegment){
        return findAvaibleCar(startDate, endDate, capacity, carSegment, null);
    }

    public static List<Car> findAvaibleCar(ZonedDateTime startDate,
                                           ZonedDateTime endDate,
                                           Integer capacity,
                                           CarSegment carSegment,
                                           Make make){

        return Collections.emptyList();

    }

}
