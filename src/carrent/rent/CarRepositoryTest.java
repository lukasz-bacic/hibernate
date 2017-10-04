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
        
        car.rentCar(new Customer(), ZonedDateTime.now().plusDays(5), ZonedDateTime.now().plusDays(8));

        List<Car> avaibleCar = CarRepository.findAvaibleCar(ZonedDateTime.now(), ZonedDateTime.now().plusDays(3));

        Assert.assertEquals(car, CarRepository.findCar(car.getId()).get());
        Assert.assertFalse("rent car is not longer avaible", avaibleCar.contains(car));

        car.rentCar(new Customer(), ZonedDateTime.now().plusDays(2), ZonedDateTime.now().plusDays(5));

        List<Car> avaibleCar2 = CarRepository.findAvaibleCar(ZonedDateTime.now(), ZonedDateTime.now().plusDays(3));

        Assert.assertFalse("rent car is not longer avaible", avaibleCar2.contains(car));


    }

}