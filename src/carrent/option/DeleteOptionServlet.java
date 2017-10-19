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
public class DeleteOptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String optionId = req.getParameter("optionId");
        Integer id = null;

        try{
             id = Integer.valueOf(optionId);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        if(id != null) {
            OptionRepository.delete(id);
        }
        resp.sendRedirect("adminPanelOptionList.jsp");

    }
}
