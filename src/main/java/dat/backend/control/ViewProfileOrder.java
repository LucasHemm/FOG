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
import dat.backend.model.services.CarportSVG;
import dat.backend.model.services.SVG;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

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

        ArrayList<Integer> listOfAmounts = Calculator.listOfPartAmounts(partList.getLength(), partList.getWidth(),connectionPool,partList.isHasShed(),partList.getShedlength(),partList.getShedwidth() );




        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Locale.setDefault(new Locale("US"));

        int length = order.getPartlist().getLength();
        int width = order.getPartlist().getWidth();
        boolean hasShed = order.getPartlist().isHasShed();


        SVG svg = new SVG(0,0,100,100,"0 0 855 690   ");

        svg.addRect(40,0,width,length);
        CarportSVG.addBeams(svg,length,width);
        CarportSVG.addRafters(svg,length,width);
        CarportSVG.addPosts(svg,length,width,hasShed);
        CarportSVG.addHollowBand(svg,length,width);
        CarportSVG.addArrows(svg,length,width);
        request.setAttribute("svg",svg);

        request.setAttribute("listOfAmounts",listOfAmounts);
        request.setAttribute("order",order);
        request.setAttribute("partList",partList);
        request.setAttribute("partsArrayList",partsArrayList);




        request.getRequestDispatcher("WEB-INF/orderpage.jsp").forward(request, response);
    }
}
