package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Parts;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.PartFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "editPart", value = "/editPart")
public class EditPart extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int partid = Integer.parseInt(request.getParameter("partid"));
        Parts part = null;
        try {
            part = PartFacade.getPartFromPartID(partid,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.setAttribute("part",part);

        request.setAttribute("partid",partid);

        String description = request.getParameter("description");
        String priceString = request.getParameter("price");
        if (priceString.equals("")) {
            String msg = "Der mangler en pris";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("WEB-INF/editpage.jsp").forward(request, response);
        }
        int price = Integer.parseInt(priceString);
        String costpriceString = request.getParameter("costprice");
        if (costpriceString.equals("")) {
            String msg = "Der mangler en pris";
            request.setAttribute("msg", msg);
            request.getRequestDispatcher("WEB-INF/editpage.jsp").forward(request, response);
        }
        int costprice = Integer.parseInt(costpriceString);
        String usage = request.getParameter("usage");


        try {
            PartFacade.updatePart(partid,description,price,costprice,usage,connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        Map<Integer, ArrayList<Integer>> lengthMap = new HashMap<>();
        ArrayList<Integer> partIDList = null;
        Map<Integer, Parts> partsMap = null;

        try {
            partsMap = PartFacade.getAllParts(connectionPool);
        } catch (DatabaseException e) {

            e.printStackTrace();
        }
        System.out.println(partsMap + " partsmap ");

        try {
            partIDList = PartFacade.getAllPartIDs(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        System.out.println(partIDList+ " partIDList ");



        for(int i: partIDList ){
            lengthMap.put(i,PartFacade.partsLengthFromPartid(i,connectionPool));
        }
        System.out.println(lengthMap + " lengthmap ");

        request.setAttribute("partsMap", partsMap);
        request.setAttribute("lengthMap", lengthMap);




        request.getRequestDispatcher("WEB-INF/storage.jsp").forward(request, response);


    }
}
