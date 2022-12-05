package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.PartList;
import dat.backend.model.entities.Parts;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.services.Calculator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "viewOrderFromAllOrders", value = "/viewOrderFromAllOrders")
public class ViewOrderFromAllOrders extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ArrayList<Order> ordersArrayList = null;
        HttpSession session = request.getSession();

        ordersArrayList = (ArrayList<Order>) session.getAttribute("allOrdersList");
        int orderIndex = Integer.parseInt(request.getParameter("orderindex"));
        System.out.println("Dette er order index på linje 39 i servlet med langt navn" + orderIndex);
        Order order = ordersArrayList.get(orderIndex);
        PartList partList = order.getPartlist();
        ArrayList<Parts> partsArrayList = partList.getPartsArrayList();

        ArrayList<Integer> listOfAmounts = Calculator.listOfPartAmounts(partList.getLength(), partList.getWidth(), connectionPool);

        request.setAttribute("listOfAmounts", listOfAmounts);
        request.setAttribute("order", order);
        request.setAttribute("partList", partList);
        request.setAttribute("partsArrayList", partsArrayList);


        request.getRequestDispatcher("WEB-INF/orderpageadmin.jsp").forward(request, response);

    }
}
