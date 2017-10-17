package carrent.rent;

import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Lukasz on 02.10.2017.
 */
public class CarRepository {

    public static List<Car> findAll(){
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT c FROM Car c";
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
    public static boolean save(Car car){
    Session session = null;
    try{
        session = HibernateUtil.openSession();
        session.save(car);
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
        Session session = null;
        try{
            session = HibernateUtil.openSession();

            String hql = "SELECT c FROM Car c WHERE c.id" +
                    " NOT IN (SELECT r.car.id FROM Rent r WHERE" +
                    " (r.startDate < :startDate AND r.endDate > :endDate  ) " + //
                    "OR (  r.endDate < :startDate  ) " + //
                    "OR ( r.startDate < :endDate ) ) "; //

            Query query = session.createQuery(hql);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.getResultList();

        }catch (Exception ex){
            ex.printStackTrace();
            return  Collections.emptyList();
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }

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
        // TODO dodac warunki z datami

        Session session = null;
        try{
            session = HibernateUtil.openSession();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);

            Root<Car> car = query.from(Car.class);
            query.select(car);

            Predicate makePredicate = criteriaBuilder.equal(car.get("make"), make);
            Predicate carSegmentPredicate = criteriaBuilder.equal(car.get("carSegment"), carSegment);
            Predicate capacityPredicate = criteriaBuilder.equal(car.get("capacity"), capacity);

            Predicate and = criteriaBuilder.and(makePredicate, carSegmentPredicate, capacityPredicate);

            query.where(and);

            return session.createQuery(query).getResultList();
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
