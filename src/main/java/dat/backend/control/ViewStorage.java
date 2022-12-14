package dat.backend.control;

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

@WebServlet(name = "viewStorage", value = "/viewStorage")
public class ViewStorage extends HttpServlet {
    ConnectionPool connectionPool = new ConnectionPool();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
