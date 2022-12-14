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

@WebServlet(name = "vieweditPage", value = "/vieweditPage")
public class ViewEditPage extends HttpServlet {
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
        request.getRequestDispatcher("WEB-INF/editpage.jsp").forward(request, response);
    }
}
