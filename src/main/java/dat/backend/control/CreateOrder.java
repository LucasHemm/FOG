package dat.backend.control;

import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.services.Calculator;
import org.graalvm.compiler.core.GraalCompiler;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CreateOrder", value = "/CreateOrder")
public class CreateOrder extends HttpServlet {
    ConnectionPool connectionPool = new ConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userid = user.getUserid();
        int length = Integer.parseInt(request.getParameter("length"));
        int width = Integer.parseInt(request.getParameter("width"));
        //Price after tax
        int price = (int) Math.round(Calculator.totalPrice(length, width, connectionPool));
        int costPrice = (int) Math.round(Calculator.totalCostPrice(length, width, connectionPool));
        ArrayList<Integer> listOfIDs = Calculator.listOfIDs(length, width, connectionPool);

        OrderFacade.createOrder(userid,length,width,price,costPrice,listOfIDs,connectionPool);

    }
}
