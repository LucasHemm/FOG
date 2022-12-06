package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "viewAllOrders", value = "/viewAllOrders")
public class ViewAllOrders extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Order> allOrdersList= null;


        HttpSession session = request.getSession();

        try {
            allOrdersList = OrderFacade.getAllOrders(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        session.setAttribute("allOrdersList",allOrdersList);
        request.getRequestDispatcher("WEB-INF/allOrders.jsp").forward(request, response);


    }
}
