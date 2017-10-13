package carrent.rent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lukasz on 13.10.2017.
 */
public class ListOfCarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.getWriter().write("<html><head></head>" +
                "<body>" +
                "<h2>Strona wypozyczalni</h2>" +
                "</body>" +
                "</html>");
    }
}
