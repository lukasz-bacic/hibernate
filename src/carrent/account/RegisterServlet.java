package carrent.account;

import carrent.rent.ProjectUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Lukasz on 18.10.2017.
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("passwordRepeat");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String phoneNumber = req.getParameter("phoneNumber");
        String dayOfBirth = req.getParameter("dayOfBirth");
        String licenseCarDay = req.getParameter("licenseCarDay");

        HashMap<String, String> errors = new HashMap<>();
        Boolean isValid = true;
        if(email == null || email.isEmpty()){
         isValid = false;
         errors.put("email", "Zly adres email");
        }
        if(password == null || password.isEmpty() || password.length() <6 ){
            isValid = false;
            errors.put("password", "Haslo jest za krotkie, 6 znakow minimum");
        }
        if(passwordRepeat == null || passwordRepeat.isEmpty() ||
                ! password.equals(passwordRepeat))
        {
            isValid = false;
            errors.put("passwordRepeat", "Hasla musza byc identyczne");
        }
        if(firstName == null || firstName.isEmpty()){
            isValid = false;
            errors.put("firstName", "Prosze podac imie");
        }
        if(lastName == null || lastName.isEmpty()){
            isValid = false;
            errors.put("lastName", "Prosze podac nazwisko");
        }
        if(phoneNumber == null || phoneNumber.isEmpty()){
            isValid = false;
            errors.put("phoneNumber", "Prosze podac numer telefonu");
        }
        if(dayOfBirth == null || dayOfBirth.isEmpty() || ProjectUtil.parseDateFromCalendar(dayOfBirth).){
            isValid = false;
            errors.put("dayOfBirth", "Prosze podac date urodzin");
        }
        if(licenseCarDay == null || licenseCarDay.isEmpty()){
            isValid = false;
            errors.put("licenseCarDay","Prosze podac date wydania prawajazdy");
        }

    }
}
