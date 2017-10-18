package carrent.account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Lukasz on 17.10.2017.
 */
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<User> user = UserRepository.findUserByEmailAndPassword(email, password);
        user.ifPresent(user1 -> req.getSession().setAttribute("userId", user1.getId()));
        if(user.isPresent()) {
            resp.sendRedirect("index.jsp");
        }else{
            resp.sendRedirect("login.jsp");
        }
    }
}
