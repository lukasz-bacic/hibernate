package carrent.rent;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Lukasz on 03.10.2017.
 */
public class CarRepositoryTest {

    @Test
    public void save(){
        Engine engine = new Engine(new BigDecimal(4200),EngineType.DIESEL,new BigDecimal(15),
                GearBox.AUTO,350,500 );

        Car car = new Car("Super Mercedes",Make.MERCEDES,5,engine,CarSegment.PREMIUM,
                Color.RED,new BigDecimal(300), new BigDecimal(100));

        CarRepository.save(car);

    }

    @Test
    public void findAvaibleCar() throws Exception {
        List<Car> avaibleMercedesCar = CarRepository.findAvaibleCar(ZonedDateTime.now(), ZonedDateTime.now(),
                5, CarSegment.PREMIUM, Make.MERCEDES);

        Assert.assertTrue("find car", avaibleMercedesCar.size() > 0);

        List<Car> avaibleKiaCar = CarRepository.findAvaibleCar(ZonedDateTime.now(), ZonedDateTime.now(),
                5, CarSegment.PREMIUM, Make.KIA);

        Assert.assertTrue("find car", avaibleKiaCar.size() == 0);


    }
    @Test
    public void testRentCar(){
        List<Car> avaibleMercedesCar = CarRepository.findAvaibleCar(ZonedDateTime.now(), ZonedDateTime.now(),
                5, CarSegment.PREMIUM, Make.MERCEDES);

        Optional<Car> car =avaibleMercedesCar.stream().findAny();
        if(car.isPresent()){
            car.get().rentCar(new Customer(), ZonedDateTime.now(), ZonedDateTime.now().plusDays(3));
        }
    }
    @Test
    public void testAvaibleCar(){
        Engine engine = new Engine(new BigDecimal(4200),EngineType.DIESEL,new BigDecimal(15),
                GearBox.AUTO,350,500 );

        Car car = new Car("Super Mercedes",Make.MERCEDES,5,engine,CarSegment.PREMIUM,
                Color.RED,new BigDecimal(300), new BigDecimal(100));

        CarRepository.save(car);

        // szukamy auto i powinno byc dostepne
        List<Car> newCars = CarRepository.findAvaibleCar(
                ZonedDateTime.now(), ZonedDateTime.now().plusDays(3));
        Assert.assertTrue("new cars should by available", newCars.contains(car));

        //rezerwujemy auto na 7 dni za 7 dni
        car.rentCar(new Customer(), ZonedDateTime.now().plusDays(7), ZonedDateTime.now().plusDays(14));

//        List<Car> avaibleCarInThisWeek = CarRepository.findAvaibleCar(
//                ZonedDateTime.now(), ZonedDateTime.now().plusDays(3));
//        Assert.assertTrue("car should by available in this week", newCars.contains(car));
//
//        List<Car> avaibleCarAfterRent = CarRepository.findAvaibleCar(
//                ZonedDateTime.now().plusDays(16), ZonedDateTime.now().plusDays(18));
//        Assert.assertTrue("car should by available after rent", newCars.contains(car));


        List<Car> avaibleCarInRentDuration = CarRepository.findAvaibleCar(
                ZonedDateTime.now().plusDays(9), ZonedDateTime.now().plusDays(10));
        Assert.assertFalse("should not by avaible ", newCars.contains(car));



    }

}