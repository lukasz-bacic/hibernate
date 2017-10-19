package carrent.option;

import carrent.rent.Option;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lukasz on 19.10.2017.
 */
public class AddOptionServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String optionName = req.getParameter("optionName");
        if(optionName != null && ! optionName.trim().isEmpty()){
            Option option = new Option(optionName);
            OptionRepository.save(option);
        }
        resp.sendRedirect("adminPanelOptionList.jsp");
    }
}
