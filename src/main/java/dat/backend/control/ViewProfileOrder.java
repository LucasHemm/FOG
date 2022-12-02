package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.PartList;
import dat.backend.model.entities.Parts;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "viewProfileOrder", value = "/viewProfileOrder")
public class ViewProfileOrder extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/orderpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Order> ordersArrayList = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            ordersArrayList = OrderFacade.getOrdersFromUserId(user.getUserid(), connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        int number = Integer.parseInt(request.getParameter("number"));
        Order order = ordersArrayList.get(number);
        PartList partList = order.getPartlist();
        ArrayList<Parts> partsArrayList = partList.getPartsArrayList();


        request.setAttribute("partsArrayList",partsArrayList);


        request.getRequestDispatcher("WEB-INF/orderpage.jsp").forward(request, response);
    }
}
