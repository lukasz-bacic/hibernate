package carrent.rent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Created by Lukasz on 13.10.2017.
 */

public class CarSpecServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("carId");

        Optional<Car> car = CarRepository.findCar(Integer.valueOf(id));

        PrintWriter writer = resp.getWriter();
        writer.write("<html><head></head><body>");
        writer.write("ok");
        if(car.isPresent()) {
            writer.write("<div><p>Model :" + car.get().getModel()+ "</p></div>");
            writer.write("<div><p>Producent: " +car.get().getMake().name()+"</p></div>");
            writer.write("<div><p>Cena: "+car.get().getBasePrice().toString()+"</p></div>");
            writer.write("<div>Parametry: <ul>");
            for(Option o :car.get().getOptionSet()){
                writer.write("<li>"+o.getName()+"</li>");
            }
            writer.write("</ul></div>");
            writer.write("<div><form action=\"carSpec?carId="+car.get().getId()+"\" method=\"POST\" >");
            writer.write("<input type=\"submit\" value=\"Dodaj\" />");
            writer.write("</form></div>");

        }
        writer.write("<br/>" +id);

        writer.write("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("carId");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        Optional<Car> car = CarRepository.findCar(Integer.valueOf(id));
        Customer nowak = new Customer("Jan", "Nowak",
                LocalDateTime.now(),LocalDateTime.now(),
                true,"0700600400"
                );

        car.ifPresent(x ->  x.rentCar(nowak, ProjectUtil.parseDateFromCalendar(startDate),
                ProjectUtil.parseDateFromCalendar(endDate)));

        PrintWriter writer = resp.getWriter();
        writer.write("dodano");
    }

}
