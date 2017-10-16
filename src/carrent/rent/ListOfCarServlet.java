package carrent.rent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by Lukasz on 13.10.2017.
 */
@WebServlet("/test")
public class ListOfCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        List<Car> avaibleCar = CarRepository.findAvaibleCar(ZonedDateTime.now(),
                ZonedDateTime.now().plusDays(1));

        PrintWriter writer = resp.getWriter();
        writer.write("<html><head></head>" +
                "<body>" +
                "<h2>Strona wypozyczalni</h2>");

        writer.write("<table><tr><td>Nazwa</td><td>Cena</td><td>Zobacz</td></tr>");
        for(Car c :avaibleCar){
            writer.write("<tr><td>"+c.getModel()+"</td>" +
                    "<td>"+c.getBasePrice().toString()+"</td>" +
                    "<td><a href=\"carSpec?carId="+c.getId()+"\">Zobacz</a></td>" +
                    "</tr>");
        }

        writer.write("</table>");
        writer.write("</body></html>");


    }
}
