package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.services.Calculator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "deleteOrder", value = "/deleteOrder")
public class DeleteOrder extends HttpServlet {

    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int userid = Integer.parseInt(request.getParameter("userid"));
        int orderid = Integer.parseInt(request.getParameter("orderid"));

        ArrayList<Order> orderList= null;
        User user = null;

        try {
            OrderFacade.deleteOrderFromOrderId(orderid,connectionPool);
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
            boolean hasShed = o.getPartlist().isHasShed();
            int shedlength = o.getPartlist().getShedlength();
            int shedwidth = o.getPartlist().getShedwidth();
            o.setProposedPrice(Calculator.totalPrice(length,width,connectionPool,hasShed,shedlength,shedwidth));
        }

        request.setAttribute("statuslist",statusList);
        request.setAttribute("user",user);
        request.setAttribute("orderList",orderList);
        request.getRequestDispatcher("WEB-INF/customerpage.jsp").forward(request, response);


    }
}
