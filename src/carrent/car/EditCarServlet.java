package carrent.car;

import carrent.rent.Car;
import carrent.rent.CarRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lukasz on 19.10.2017.
 */
public class EditCarServlet  extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String model = req.getParameter("model");
        String insuranceCost = req.getParameter("insuranceCost");
        String capacity = req.getParameter("capacity");
        boolean isValid =true;
        if(id == null || id.trim().isEmpty()){
            isValid = false;
        }
        if(model == null || model.trim().isEmpty() ){
            isValid = false;
        }
        if(insuranceCost == null || insuranceCost.trim().isEmpty() ){
            isValid = false;
        }
        if(capacity == null || capacity.trim().isEmpty() ){
            isValid = false;
        }
        Integer carId = null;
        try {
            carId = Integer.valueOf(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        Car newCar = new Car();
        newCar.setModel(model);
        if(carId != null) {
            newCar.setId(carId);
        }
        
        CarRepository.save(newCar);


    }
}
