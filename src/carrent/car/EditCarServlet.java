package carrent.car;

import carrent.option.OptionRepository;
import carrent.rent.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        String basePrice = req.getParameter("basePrice");
        String segment = req.getParameter("segment");
        String color = req.getParameter("color");
        String gearBox = req.getParameter("gearBox");
        String engineType = req.getParameter("engineType");
        String torque = req.getParameter("torque");
        String horsePower = req.getParameter("horsePower");
        String fuelConsumption = req.getParameter("fuelConsumption");
        String engineCapacity = req.getParameter("engineCapacity");
        String make = req.getParameter("make");

        List<Integer> optionList = new ArrayList<>();
        Set<String> values = req.getParameterMap().keySet();
        Iterator<String> optionIterator = values.stream().filter(x -> x.contains("option_")).iterator();
        while (optionIterator.hasNext()){
            String option = optionIterator.next();
            optionList.add(Integer.valueOf(option.replace("option_", "")));
        }




        boolean isValid =true;

        if(model == null || model.trim().isEmpty() ){
            isValid = false;
        }
        if(insuranceCost == null || insuranceCost.trim().isEmpty() ){
            isValid = false;
        }
        if(capacity == null || capacity.trim().isEmpty() ){
            isValid = false;
        }
        if(basePrice == null || basePrice.trim().isEmpty()){
            isValid = false;
        }
        if(segment == null || segment.trim().isEmpty()){
            isValid = false;
        }
        if(color == null || color.trim().isEmpty()){
            isValid = false;
        }
        if(gearBox == null || gearBox.trim().isEmpty()){
            isValid = false;
        }
        if(engineType == null || engineType.trim().isEmpty()){
            isValid = false;
        }
        if(torque == null || torque.trim().isEmpty()){
            isValid = false;
        }
        if(horsePower == null || horsePower.trim().isEmpty()){
            isValid = false;
        }
        if(fuelConsumption == null || fuelConsumption.trim().isEmpty()){
            isValid = false;
        }
        if(engineCapacity == null || engineCapacity.trim().isEmpty()){
            isValid = false;
        }
        if(make == null || make.trim().isEmpty()){
            isValid = false;
        }

        Integer _carId = null;
        Make _make = null;
        Integer _capacity = null;
        CarSegment _carSegment= null;
        Color _color= null;
        BigDecimal _basePrice= null;
        BigDecimal _insuranceCost= null;
        BigDecimal _engineCapacity= null;
        EngineType _engineType= null;
        BigDecimal _fuelConsumption= null;
        GearBox _gearBox= null;
        Integer _horsePower= null;
        Integer _torque= null;
        try {
            _carId = Integer.valueOf(id);
        }catch (Exception ex){
        }
        try {
            _make = Make.valueOf(make);
            _capacity = Integer.valueOf(capacity);
            _carSegment = CarSegment.valueOf(segment);
            _color = Color.valueOf(color);
            _basePrice = new BigDecimal(basePrice);
            _insuranceCost = new BigDecimal(insuranceCost);
            _engineCapacity = new BigDecimal(engineCapacity);
            _engineType = EngineType.valueOf(engineType);
            _fuelConsumption = new BigDecimal(fuelConsumption);
            _gearBox = GearBox.valueOf(gearBox);
            _horsePower = Integer.valueOf(horsePower);
            _torque = Integer.valueOf(torque);

        }catch (Exception ex){
            ex.printStackTrace();
            isValid = false;

        }
        if(isValid){
            Engine engine = new Engine(_engineCapacity,_engineType,_fuelConsumption,
                    _gearBox,_horsePower,_torque);
            Car car = new Car(model, _make, _capacity,engine,_carSegment,_color,_basePrice,_insuranceCost);
            OptionRepository.findAllByIdList(optionList);
            if(_carId != null){
                car.setId(_carId);
            }
            for(Option o :OptionRepository.findAllByIdList(optionList)){
                o.getCarSet().add(car);
                OptionRepository.save(o);
            }

            CarRepository.saveOrUpdate(car);
            resp.sendRedirect("adminPanelCarList.jsp");
        }

    }
}
