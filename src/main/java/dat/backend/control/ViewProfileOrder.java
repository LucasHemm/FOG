package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.PartList;
import dat.backend.model.entities.Parts;
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

        ArrayList<Parts> partsArrayList = null;
        PartList partList = (PartList) request.getAttribute("partlist");
        partsArrayList = partList.getPartsArrayList();
        request.setAttribute("partsArrayList",partsArrayList);


        request.getRequestDispatcher("WEB-INF/orderpage.jsp").forward(request, response);
    }
}
