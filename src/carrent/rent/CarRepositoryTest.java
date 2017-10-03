package carrent.rent;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

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

}