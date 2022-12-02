package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "viewProfile", value = "/viewProfile")
public class ViewProfile extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Order> orderList= null;


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            orderList = OrderFacade.getOrdersFromUserId(user.getUserid(),connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);

    }
}
