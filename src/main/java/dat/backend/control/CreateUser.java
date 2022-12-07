package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.services.CheckString;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "createUser", value = "/createUser")
public class CreateUser extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/createUser.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");


        String password = request.getParameter("password1");
        String password2 = request.getParameter("password2");



        if (!password.equals(password2)) {
            String msg2 = "De angivne koder er forskellige, prøv igen";
            request.setAttribute("msg2", msg2);
            request.getRequestDispatcher("WEB-INF/createUser.jsp").forward(request, response);
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", null); // invalidating user object in session scope
        //int id = Integer.parseInt((request.getParameter("userid")));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int postalcode = CheckString.stringToInt(request.getParameter("postalcode"));
        String cityName = request.getParameter("city");
        String streetName = request.getParameter("address");


        try {
            User user = UserFacade.createUser(email, name, password, postalcode, cityName, streetName, false, connectionPool);
            session = request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
        } catch (DatabaseException e) {
            String msg1 = "Bruger med denne email findes allerede";
            request.setAttribute("msg1", msg1);
            request.getRequestDispatcher("WEB-INF/createUser.jsp").forward(request, response);
        }
    }
}
