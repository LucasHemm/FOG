package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.services.Calculator;
import dat.backend.model.services.CheckString;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "updatePrice", value = "/updatePrice")
public class UpdatePrice extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        int userid = Integer.parseInt(request.getParameter("userid2"));
        int orderid = Integer.parseInt(request.getParameter("orderid2"));
        int newprice= CheckString.stringToInt(request.getParameter("newprice"));

        Order order = null;
        try {
            order = OrderFacade.getOrderFromOrderId(orderid,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        int partlistid = order.getPartlist().getPartlistid();

        try {
            OrderFacade.updatePrice(partlistid,newprice,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }


        ArrayList<Order> orderList= null;
        User user = null;

        try {
            orderList = OrderFacade.getOrdersFromUserId(userid,connectionPool);
            user = UserFacade.getUserFromUserId(userid,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        ArrayList<String> statusList = new ArrayList<>();
        statusList.add("Skal slettes");
        statusList.add("Ikke godkendt");
        statusList.add("Godkendt");
        statusList.add("Betalt");


        for(Order o: orderList){
            int length = o.getPartlist().getLength();
            int width = o.getPartlist().getWidth();
            o.setProposedPrice(Calculator.totalPrice(length,width,connectionPool));
        }


        request.setAttribute("statuslist",statusList);
        request.setAttribute("user",user);
        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("WEB-INF/customerpage.jsp").forward(request, response);

    }
}
