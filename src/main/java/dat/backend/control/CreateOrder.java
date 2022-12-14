package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
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

@WebServlet(name = "createOrder", value = "/createOrder")
public class CreateOrder extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String msg = "Din forespørgsel er nu blevet oprette - se status her på din profil side.";
        request.setAttribute("msg", msg);

        ArrayList<Order> orderList = null;


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userid = user.getUserid();
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        int shedchoice = Integer.parseInt(request.getParameter("hasShed"));
        boolean hasShed = false;
        int shedlength = 0;
        int shedwidth = 0;

        if (shedchoice == 1) {
            hasShed = true;
            int shedLengthDenominator = Integer.parseInt(request.getParameter("shedlength"));
            shedlength = length / shedLengthDenominator;
            int shedWidthDenominator = Integer.parseInt(request.getParameter("shedwidth"));
            shedwidth = (width-70) / shedWidthDenominator;
        }

        //Price after tax
        int price = (int) Math.round(Calculator.totalPrice(length, width, connectionPool, hasShed, shedlength, shedwidth));
        int costPrice = (int) Math.round(Calculator.totalCostPrice(length, width, connectionPool, hasShed, shedlength, shedwidth));
        ArrayList<Integer> listOfIDs = Calculator.listOfIDs(length, width, connectionPool, hasShed, shedlength, shedwidth);

        OrderFacade.createOrder(userid, length, width, price, costPrice, listOfIDs, hasShed, shedlength, shedwidth, connectionPool);


//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
        try {
            orderList = OrderFacade.getOrdersFromUserId(user.getUserid(), connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);

    }
}
